package bulletsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.*;
import data.EntityType;
import javafx.scene.shape.Circle;
import services.BulletSPI;
import services.IGamePluginService;

public class BulletFactory implements EntityFactory, IGamePluginService, BulletSPI {
    @Override
    @Spawns("bullet")
    public Entity newBullet(SpawnData data) {
        return FXGL.entityBuilder(data)
            .type(EntityType.BULLET)
            .viewWithBBox(new Circle(2, javafx.scene.paint.Color.RED))
            .with(new BulletComponent())
            .with(new ProjectileComponent(data.get("direction"), 400))
            .with(new OffscreenCleanComponent())
            .collidable()
            .build();
    }

    @Override
    public void start(GameWorld world) {
        world.addEntityFactory(this);
    }

    @Override
    public void stop(GameWorld world) {
        world.removeEntityFactory(this);
        world.removeEntities(world.getEntitiesByType(EntityType.BULLET));
    }
}
