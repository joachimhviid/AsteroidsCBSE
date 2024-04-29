package enemysystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.net.URL;

import static com.almasb.fxgl.dsl.FXGL.spawn;

public class EnemyComponent extends Component {
    private Vec2 direction = new Vec2(0, 0);

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
        entity.getViewComponent().addChild(FXGL.texture(url("textures/enemy_ship.png")));
    }

    @Override
    public void onUpdate(double tpf) {
        direction = Vec2.fromAngle(entity.getRotation() - 90).mulLocal(1.1);
        entity.translate(direction);

        if (Math.random() < 0.5) {
            entity.rotateBy(1);
        } else {
            entity.rotateBy(-1);
        }

        if (Math.random() < 0.01) {
            shoot();
        }
    }

    public void shoot() {
        Point2D spawnPoint = entity.getCenter().add(Vec2.fromAngle(entity.getRotation() - 90).mul(40).toPoint2D());
        try {
            spawn("bullet", new SpawnData(spawnPoint)
                .put("direction", Vec2.fromAngle(entity.getRotation() - 90).toPoint2D()));
        } catch (Exception e) {
            System.out.println("Bullet plugin not loaded");
        }
    }

    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }
}
