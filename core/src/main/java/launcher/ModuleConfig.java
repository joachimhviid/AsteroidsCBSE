package launcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.IGamePluginService;
import services.IInputService;

import java.util.List;
import java.util.ServiceLoader;

@Configuration
public class ModuleConfig {
    @Bean
    public GameLauncher gameLauncher() {
        return new GameLauncher();
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();
    }

    @Bean
    public List<IInputService> gameInputs() {
        return ServiceLoader.load(IInputService.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .toList();
    }
}
