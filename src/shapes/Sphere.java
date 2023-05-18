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
	 * Determines if this Sphere is overlapping a given sphere.
	 * If the two spheres overlap, they have collided.
	 * 
	 * @param sphere the sphere to test for collision
	 * @return true if this sphere is overlapping the given sphere, false otherwise.
	 */

	public boolean collides(Sphere sphere) {
		double dx = sphere.x - x;
		double dy = sphere.y - y;
		double distance = Math.sqrt(dx*dx+dy*dy);

		return distance <= (double) Math.abs(width/2 + sphere.width/2);

	}

}