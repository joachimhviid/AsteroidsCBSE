package asteroidsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class AsteroidComponent extends Component {
    ProjectileComponent projectileComponent;
    HealthIntComponent healthIntComponent;

    Point2D direction = new Point2D(Math.random(), Math.random());

    @Override
    public void onAdded() {
        healthIntComponent = entity.getComponent(HealthIntComponent.class);
        projectileComponent = entity.getComponent(ProjectileComponent.class);

        double speed = Math.random() * (100 - 20) + 20 + ((double) 50 / healthIntComponent.getValue());

        projectileComponent.setDirection(direction);
        projectileComponent.setSpeed(speed);
    }

    @Override
    public void onRemoved() {
        if (healthIntComponent.getValue() == 0) return;
        FXGL.spawn("asteroid", new SpawnData(entity.getPosition().add(10, 10)).put("hp", healthIntComponent.getValue()));
        FXGL.spawn("asteroid", new SpawnData(entity.getPosition().add(-10, -10)).put("hp", healthIntComponent.getValue()));
    }
}
