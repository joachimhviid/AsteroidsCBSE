package playersystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import data.EntityType;
import services.IGamePluginService;

public class PlayerFactory implements EntityFactory, IGamePluginService {
    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder(data)
            .type(EntityType.PLAYER)
            .bbox(new HitBox(BoundingShape.box(40, 70)))
            .with(new PlayerComponent())
            .collidable()
            .build();
    }

    @Override
    public void start(GameWorld world) {
        world.addEntityFactory(this);
        world.spawn("player", FXGL.getAppCenter());
    }
}
