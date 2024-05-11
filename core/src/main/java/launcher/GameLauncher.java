package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import services.IGamePluginService;
import services.IInputService;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

public class GameLauncher extends GameApplication {
    private static ModuleLayer moduleLayer;

    public static void main(String[] args) {
        Path pluginsDir = Paths.get("plugins");
        ModuleFinder pluginFinder = ModuleFinder.of(pluginsDir);
        List<String> plugins = pluginFinder
            .findAll()
            .stream()
            .map(moduleReference -> moduleReference.descriptor().name())
            .toList();

        System.out.println("Plugins: " + plugins);

        Configuration configuration = ModuleLayer
            .boot()
            .configuration()
            .resolve(pluginFinder, ModuleFinder.of(), plugins);

        moduleLayer = ModuleLayer.boot().defineModulesWithOneLoader(configuration, ClassLoader.getSystemClassLoader());

        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setWidth(800);
        settings.setTitle("Asteroids");

        settings.setDeveloperMenuEnabled(true);
    }

    @Override
    protected void initInput() {
        System.out.println("Setting game inputs");
        List<IInputService> gameInputs = ServiceLoader.load(moduleLayer, IInputService.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();

        gameInputs.forEach(IInputService::setInput);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
    }

    @Override
    protected void initGame() {
        FXGL.getGameScene().setBackgroundColor(javafx.scene.paint.Color.BLACK);

        List<IGamePluginService> gamePlugins = ServiceLoader.load(moduleLayer, IGamePluginService.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();

        gamePlugins.forEach(plugin -> plugin.start(FXGL.getGameWorld()));

    }

    @Override
    protected void initUI() {
        Text score = new Text("Score: 0");
        score.setFill(Color.WHITE);
        score.textProperty().bind(FXGL.getWorldProperties().intProperty("score").asString("Score: %d"));
        FXGL.addUINode(score, 10, 20);
    }
}
