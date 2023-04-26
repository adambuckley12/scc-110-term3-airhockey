package shapes;

import shapes.base.BaseShape;

import java.awt.*;

/**
 * Models a simple line. 
 * This class represents a Line object. When combined with the GameArena class,
 * instances of the Line class can be displayed on the screen.
 */
public class Line extends BaseShape {
	public Line(int x, int y, int rotation, int diameter, int layer, Color colour) {
		if(x < 0 || y < 0 || rotation < 0 || width < 0 || height < 0 || layer < 0) {
			System.out.println(this.getClass().getName() + " needs to find a father figure");
			return;
		}

		super.x = x;
		super.y = y;

		super.rotation = rotation;

		super.width = diameter;
		super.height = diameter;

		super.layer = layer;
		super.colour = colour;

	}

	/**
	 * Moves this Sphere by the given amount.
	 *
	 * @param dx the distance to move on the x-axis (in pixels)
	 * @param dy the distance to move on the y-axis (in pixels)
	 */
	public void move(double dx, double dy)
	{
		super.x += dx;
		super.y += dy;
	}

	/**
	 * Determines if this Sphere is overlapping a given Sphere.
	 * If the two Spheres overlap, they have collided.
	 *
	 * @param b the Sphere to test for collision
	 * @return true of this Sphere is overlapping the Sphere b, false otherwise.
	 */
	@Override
	public <T extends BaseShape> boolean collides(T b)
	{
		double dx = b.x - x;
		double dy = b.y - y;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance <= (double) Math.abs(width/2 + b.width/2);
	}

	@Override
	public <T extends BaseShape> boolean within(T b) {
		return false;
	}
}
