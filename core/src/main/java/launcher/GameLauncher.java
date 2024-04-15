package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.text.Text;
import services.IGamePluginService;

import java.util.List;
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
    protected void initUI() {
        score = new Text("Destroyed asteroids: 0");
        FXGL.addUINode(score, 10, 20);
    }
}
