package playersystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.net.URL;

import static com.almasb.fxgl.dsl.FXGL.*;

public class PlayerComponent extends Component {
    private final double speed = 2;
    private Vec2 direction = new Vec2(0, 0);

    public PlayerComponent() {
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
        entity.getViewComponent().addChild(FXGL.texture(url("textures/player_ship.png")));
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translate(direction);

        direction = direction.mul(0.99);
    }

    public void steerRight() {
        entity.rotateBy(3);
    }

    public void steerLeft() {
        entity.rotateBy(-3);
    }

    public void accelerate() {
        direction = Vec2.fromAngle(entity.getRotation() - 90).mulLocal(speed);
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
