package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import data.EntityType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.IGamePluginService;

import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class GameLauncher extends GameApplication {
    private Text score;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(600);
        settings.setWidth(600);
        settings.setTitle("Asteroids");

        settings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(javafx.scene.paint.Color.BLACK);

        List<IGamePluginService> gamePlugins = ServiceLoader.load(IGamePluginService.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();

        gamePlugins.forEach(plugin -> {
            plugin.start(FXGL.getGameWorld());

        });

    }

    @Override
    protected void initPhysics() {
        FXGL.onCollision(EntityType.BULLET, EntityType.ASTEROID, (bullet, asteroid) -> {
            bullet.removeFromWorld();
            try {
                asteroid.getComponent(HealthIntComponent.class).damage(1);
            } catch (Exception e) {
                System.out.println("Asteroid has no health component");
            }
            FXGL.inc("score", +1);
            score.setText("Destroyed asteroids: " + FXGL.getWorldProperties().getInt("score"));
        });
    }

    @Override
    protected void initUI() {
        score = new Text("Destroyed asteroids: 0");
        score.setFill(Color.WHITE);
        FXGL.addUINode(score, 10, 20);
    }
}
