package components;

import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrapAroundComponentTest {

    @Test
    public void testWrapsAround() {
        WrapAroundComponent wrapAroundComponent = new WrapAroundComponent();
        Entity entity = new Entity();
        entity.setPosition(801, 601);

        Point2D entityPosition = wrapAroundComponent.wrapAround(entity, 800, 600);
        assertEquals(0, entityPosition.getX());
        assertEquals(0, entityPosition.getY());
    }
}
