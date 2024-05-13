package collisionsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import data.EntityType;
import data.ScoreClient;
import services.IGamePluginService;

import java.io.IOException;

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
            try {
                FXGL.set("score", new ScoreClient().updateScore(1));
            } catch (IOException | InterruptedException e) {
                System.out.println("Failed to update score");
            }
        });

        FXGL.onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
            try {
                FXGL.set("score", new ScoreClient().updateScore(5));
            } catch (IOException | InterruptedException e) {
                System.out.println("Failed to update score");
            }
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
            try {
                FXGL.set("score", new ScoreClient().updateScore(1));
            } catch (IOException | InterruptedException e) {
                System.out.println("Failed to update score");
            }
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
