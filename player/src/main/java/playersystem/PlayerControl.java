package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import data.EntityType;
import javafx.scene.input.KeyCode;
import services.IInputService;

public class PlayerControl implements IInputService {
    private Entity getPlayer() {
        return FXGL.getGameWorld().getSingleton(EntityType.PLAYER);
    }

    private PlayerComponent getPlayerComponent() {
        return getPlayer().getComponent(PlayerComponent.class);
    }

    public void setInput() {

        try {
            FXGL.onKey(KeyCode.A, () -> getPlayerComponent().steerLeft());
            FXGL.onKey(KeyCode.D, () -> getPlayerComponent().steerRight());
            FXGL.onKey(KeyCode.W, () -> getPlayerComponent().accelerate());
            FXGL.onKeyDown(KeyCode.SPACE, () -> getPlayerComponent().shoot());
        } catch (Exception e) {
            System.out.println("Input already has been set");
        }
    }
}
