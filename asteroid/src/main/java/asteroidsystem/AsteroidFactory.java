package asteroidsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.*;
import components.WrapAroundComponent;
import data.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import services.IGamePluginService;


public class AsteroidFactory implements EntityFactory, IGamePluginService {
    @Spawns("asteroid")
    public Entity newAsteroid(SpawnData data) {
        // Come in 3 stages of health
        // 1. Large, 3 HP, slow movement, splits in 2 when hit
        // 2. Medium, 2 HP, medium movement, splits in 2 when hit
        // 3. Small, 1 HP, fast movement, destroyed when hit
        int hp = (data.hasKey("hp")) ? data.get("hp") : 3;
        int radius = hp * 15;
        return FXGL.entityBuilder(data)
            .type(EntityType.ASTEROID)
            .viewWithBBox(new Circle(radius, radius, radius, Color.SADDLEBROWN))
            .with(new HealthIntComponent(hp))
            .with(new WrapAroundComponent())
            .with(new ProjectileComponent())
            .with(new AsteroidComponent())
            .collidable()
            .build();
    }

    @Override
    public void start(GameWorld world) {
        world.addEntityFactory(this);
        FXGL.run(() -> {
            // Spawn an asteroid every 2 seconds with a maximum of 3 asteroids
            if (world.getEntitiesByType(EntityType.ASTEROID).size() < 3) {
                // Randomize the spawn location
                double x = Math.random() * FXGL.getAppWidth();
                double y = Math.random() * FXGL.getAppHeight();
                FXGL.spawn("asteroid", new SpawnData(x, y));
            }
        }, Duration.seconds(2));
    }

    @Override
    public void stop(GameWorld world) {
        world.removeEntityFactory(this);
        world.removeEntities(world.getEntitiesByType(EntityType.ASTEROID));
    }
}
