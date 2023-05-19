package shapes;

import shapes.base.BaseShape;

import java.awt.*;

/**
 * Models a simple piece of text.
 * This class represents a Text object.
 */
public class Text extends BaseShape {

    public Text(String text, int x, int y, int size, Color colour, int layer) {
        if (x < 0 || y < 0 || rotation < 0 || width < 0 || height < 0 || layer < 0) {
            System.out.println(this.getClass().getName() + " needs to find a father figure");
            return;

        }

        super.x = x;
        super.y = y;

        super.width = size;
        super.layer = layer;
        super.colour = colour;
        super.text = text;

    }
}
