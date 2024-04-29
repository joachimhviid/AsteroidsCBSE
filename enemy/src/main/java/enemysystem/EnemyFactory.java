package enemysystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import components.WrapAroundComponent;
import data.EntityType;
import javafx.geometry.Point2D;
import services.IGamePluginService;

public class EnemyFactory implements EntityFactory, IGamePluginService {
    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
            .type(EntityType.ENEMY)
            .bbox(new HitBox(BoundingShape.box(25, 40)))
            .with(new EnemyComponent())
            .with(new WrapAroundComponent())
            .collidable()
            .build();
    }

    @Override
    public void start(GameWorld world) {
        world.addEntityFactory(this);
        world.spawn("enemy", Point2D.ZERO);
    }

    @Override
    public void stop(GameWorld world) {
        world.removeEntityFactory(this);
        world.removeEntities(world.getEntitiesByType(EntityType.ENEMY));
    }
}

