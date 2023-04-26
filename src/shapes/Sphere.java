package shapes;

import shapes.base.BaseShape;

import java.awt.*;

public class Sphere extends BaseShape {
	public Sphere(int x, int y, int rotation, int diameter, int layer, Color colour) {
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
	 * Determines if this Sphere is overlapping a given sphere.
	 * If the two spheres overlap, they have collided.
	 * 
	 * @param b the sphere to test for collision
	 * @return true of this sphere is overlapping the sphere b, false otherwise.
	 */
	@Override
	public <T extends BaseShape> boolean collides(T b) {
		double dx = b.x - x;
		double dy = b.y - y;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance <= (double) Math.abs(width/2 + b.width/2);

//		100, 0     100, 100,
//
//		0, 0         0, 100
//		// y + w/2 !>
//		// y - w/2 !<
//
//		// x + w/2 !>
//		// x - w/2 !<
	}

	/**
	 * Function to check that the current sphere object is within a square 
	 * @param r
	 * @return
	 * @param <T>
	 */
	@Override
	//TODO: Change type to rectangle once implemented
	//TODO: Look into using rotation for weird maps. (if can be bothered to add unique maps as extra)
	public <T extends BaseShape> boolean within(T r) {
		// Y collision parameters:
		// y_sphere -/+ sphere_radius > y_rectangle -/+ rec width/2

		// Rectangle calculations
		int minRX = r.x - r.width/2;
		int maxRX = r.x + r.width/2;

		int minRY = r.y - r.height/2;
		int maxRY = r.y + r.height/2;

		// Sphere calculations
		//width and height are the same. (Diameter)
		int minBX = x - width/2;
		int maxBX = x + width/2;

		int minBY = y - height/2;
		int maxBY = y + height/2;

		if(minRX >= minBX) return false;
		if(minRY >= minBY) return false;

		if(maxRX <= maxBX) return false;
		if(maxRY <= maxBY) return false;

		//
		return true;
	}

}