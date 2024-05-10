package collisionsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import data.EntityType;
import services.IGamePluginService;

public class CollisionPlugin implements IGamePluginService {
    @Override
    public void start(GameWorld world) {
        initBulletCollision();
        initEnemyCollision();
        initAsteroidCollision();
    }

    @Override
    public void stop(GameWorld world) {

    }

    private void initBulletCollision() {
        FXGL.onCollisionBegin(EntityType.BULLET, EntityType.ASTEROID, (bullet, asteroid) -> {
            bullet.removeFromWorld();
            splitAsteroid(asteroid);
            FXGL.inc("score", +1);
        });

        FXGL.onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
            FXGL.inc("score", +5);
        });

        FXGL.onCollisionBegin(EntityType.BULLET, EntityType.PLAYER, (bullet, player) -> FXGL.getGameController().startNewGame());
    }

    private void initEnemyCollision() {
        FXGL.onCollisionBegin(EntityType.ENEMY, EntityType.PLAYER, (enemy, player) -> FXGL.getGameController().startNewGame());
    }

    private void initAsteroidCollision() {
        FXGL.onCollisionBegin(EntityType.ASTEROID, EntityType.PLAYER, (asteroid, player) -> FXGL.getGameController().startNewGame());
        FXGL.onCollisionBegin(EntityType.ASTEROID, EntityType.ENEMY, (asteroid, enemy) -> {
            enemy.removeFromWorld();
            splitAsteroid(asteroid);
            FXGL.inc("score", +1);
        });
    }

    private void splitAsteroid(Entity asteroid) {
        try {
            HealthIntComponent healthComponent = asteroid.getComponent(HealthIntComponent.class);
            healthComponent.damage(1);
        } catch (Exception e) {
            System.out.println("Asteroid has no health component");
        }
        asteroid.removeFromWorld();
    }
}
