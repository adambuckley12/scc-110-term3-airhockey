package shapes;

import shapes.base.BaseShape;

import java.awt.*;

public class Sphere extends BaseShape {
	public Sphere(int x, int y, int rotation, int diameter, int layer, Color colour, int xVelocity, int yVelocity) {
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

		super.xVelocity = xVelocity;
		super.yVelocity = yVelocity;

	}

	/**
	 * Moves this Sphere by the given amount.
	 * 
	 * @param dx the distance to move on the x-axis (in pixels)
	 * @param dy the distance to move on the y-axis (in pixels)
	 */
	public void move(int dx, int dy) {
		super.x += dx;
		super.y += dy;
	}

	/**
	 * Determines if this Sphere is overlapping a given sphere.
	 * If the two spheres overlap, they have collided.
	 * 
	 * @param sphere the sphere to test for collision
	 * @return true if this sphere is overlapping the given sphere, false otherwise.
	 */
	@Override
	public boolean collides(Sphere sphere) {
		double dx = sphere.x - x;
		double dy = sphere.y - y;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance <= (double) Math.abs(width/2 + sphere.width/2);

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
	 * @param rectangle Rectangle to check
	 * @return true if this sphere is fully within the given rectangle, false otherwise.
	 */
	@Override
	//TODO: Look into using rotation for weird maps. (if can be bothered to add unique maps as extra)
	public int within(Rectangle rectangle) {
		// Y collision parameters:
		// y_sphere -/+ sphere_radius > y_rectangle -/+ rec width/2

		// Rectangle calculations
		int minRX = rectangle.x - rectangle.width/2;
		int maxRX = rectangle.x + rectangle.width/2;

		int minRY = rectangle.y - rectangle.height/2;
		int maxRY = rectangle.y + rectangle.height/2;

		// Sphere calculations
		//width and height are the same. (Diameter)
		int minBX = x - width/2;
		int maxBX = x + width/2;
		if(minRX >= minBX || maxRX <= maxBX) return 1; // 1 if x is out of the box

		int minBY = y - height/2;
		int maxBY = y + height/2;
		if(minRY >= minBY || maxRY <= maxBY) return 2; // 2 if y is out of the box

		// if none of the above conditions are true, then our sphere is still within
		return 0; //0 if in the box
	}

}