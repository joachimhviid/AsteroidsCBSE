package services;

import com.almasb.fxgl.entity.GameWorld;

/**
 * Interface for game plugin services
 * Precondition: The game world must be initialized
 * Postcondition: The game plugin service is started or stopped
 */
public interface IGamePluginService {
    void start(GameWorld world);
    void stop(GameWorld world);
}
