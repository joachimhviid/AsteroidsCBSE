package components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class WrapAroundComponent extends Component {
    @Override
    public void onUpdate(double tpf) {
        double screenWidth = FXGL.getAppWidth();
        double screenHeight = FXGL.getAppHeight();

        if (entity.getX() > screenWidth) {
            entity.setX(0);
        } else if (entity.getX() < 0) {
            entity.setX(screenWidth);
        }

        if (entity.getY() > screenHeight) {
            entity.setY(0);
        } else if (entity.getY() < 0) {
            entity.setY(screenHeight);
        }
    }
}
