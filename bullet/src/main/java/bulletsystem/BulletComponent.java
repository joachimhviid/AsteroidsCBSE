package bulletsystem;

import com.almasb.fxgl.entity.component.Component;

public class BulletComponent extends Component {
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }
}
