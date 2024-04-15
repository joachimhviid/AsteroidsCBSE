package playersystem;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

import java.net.URL;

public class PlayerComponent extends Component {
    private final double speed = 2;
    private Vec2 direction = new Vec2(0, 0);

    public PlayerComponent() {
        Input input = FXGL.getInput();

        input.addAction(new UserAction("Steer Left") {
            protected void onAction() {
                steerLeft();
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Steer Right") {
            protected void onAction() {
                steerRight();
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Accelerate") {
            protected void onAction() {
                accelerate();
            }
        }, KeyCode.W);
        input.addAction(new UserAction("Shoot") {
            protected void onAction() {
                System.out.println("shoot");
            }
        }, KeyCode.SPACE);
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

        //if (direction.length() < 1) {
        //    direction = direction.mul(0);
        //}
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
        System.out.println("shoot NYI");
    }

    private String getUrlPrefixForAssets() {
        return '/' + getClass().getModule().getName() + "/assets/";
    }

    private URL url(String path) {
        return getClass().getResource(getUrlPrefixForAssets() + path);
    }
}
