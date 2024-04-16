package services;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

public interface BulletSPI {
    @Spawns("bullet")
    Entity newBullet(SpawnData data);
}
