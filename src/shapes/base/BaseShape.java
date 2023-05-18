package shapes.base;

import shapes.Rectangle;
import shapes.Sphere;

import java.awt.*;

public abstract class BaseShape {
    // position:
    // x,y
        // This the centre/

    // rotation
        // default 0 - to 359

    // height,width
        // >0


    // Restrictions:
        // Sphere will be a true circle
        // ?

    // Coordinates
    public int x;
    public int y;

    // Rotation
    public int rotation;

    // Size
    public int width;
    public int height;

    // Config
    public int layer;
    public Color colour;

    public double xVelocity;
    public double yVelocity;

    public String text;

}
