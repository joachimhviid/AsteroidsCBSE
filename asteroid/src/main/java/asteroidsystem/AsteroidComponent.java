package asteroidsystem;

import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.component.Component;

public class AsteroidComponent extends Component {
    ProjectileComponent projectileComponent;

    public AsteroidComponent() {
        /* TODO: Get random direction
        * Set speed based on HP
        * Add split logic
        * When leaving screen, wrap around
         */
        projectileComponent = new ProjectileComponent();
    }
}
