package components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

public class WrapAroundComponent extends Component {
    @Override
    public void onUpdate(double tpf) {
        double screenWidth = FXGL.getAppWidth();
        double screenHeight = FXGL.getAppHeight();

        wrapAround(entity, screenWidth, screenHeight);
    }

    public Point2D wrapAround(Entity e, double screenWidth, double screenHeight) {
        if (e.getX() > screenWidth) {
            e.setX(0);
        } else if (e.getX() < 0) {
            e.setX(screenWidth);
        }

        if (e.getY() > screenHeight) {
            e.setY(0);
        } else if (e.getY() < 0) {
            e.setY(screenHeight);
        }

        return e.getPosition();
    }
}
