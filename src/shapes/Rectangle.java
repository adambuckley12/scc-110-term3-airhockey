package shapes;

import shapes.base.BaseShape;

import java.awt.*;

/**
 * Models a simple, solid rectangle.
 * This class represents a Rectangle object. When combined with the GameArena class,
 * instances of the Rectangle class can be displayed on the screen.
 */
public class Rectangle extends BaseShape {
    public Rectangle(int x, int y, int rotation, int width, int height, int layer, Color colour) {
        if (x < 0 || y < 0 || rotation < 0 || width < 0 || height < 0 || layer < 0) {
            System.out.println(this.getClass().getName() + " needs to find a father figure");
            return;
        }

        super.x = x;
        super.y = y;

        super.rotation = rotation;

        super.width = width;
        super.height = height;

        super.layer = layer;
        super.colour = colour;

    }

}
