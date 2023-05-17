package physics;

import shapes.Sphere;
import shapes.base.BaseShape;

public class Physics {

    public static void deflect(BaseShape puck, BaseShape paddle) {
// The position and speed of each of the two balls in the x-axis and y-axis before collision.
// YOU NEED TO FILL THESE VALUES IN AS APPROPRIATE...

// Calculate initial momentum of the spheres... We assume unit mass here. //TODO Allow for mass of spheres as a setting and implement here maybe???
        double p1InitialMomentum = Math.sqrt(puck.xVelocity * puck.xVelocity + puck.yVelocity * puck.yVelocity);
        double p2InitialMomentum = Math.sqrt((paddle.xVelocity * paddle.xVelocity + paddle.yVelocity * paddle.yVelocity) * 40); //ASSUMING PUCK HAS MUCH LARGER MASS AS WOULD HAVE A HAND IN IT AND FORCE APPLIED
// calculate motion vectors
        double[] p1Trajectory = {puck.xVelocity, puck.yVelocity};
        double[] p2Trajectory = {paddle.xVelocity, paddle.yVelocity};
// Calculate Impact Vector
        double[] impactVector = {paddle.x - puck.x, paddle.y - puck.y};
        double[] impactVectorNorm = normalizeVector(impactVector);
// Calculate scalar product of each trajectory and impact vector
        double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
        double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);
// Calculate the deflection vectors - the amount of energy transferred from one ball to the other in each axis
        double[] p1Deflect = {-impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact};
        double[] p2Deflect = {impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact};
// Calculate the final trajectories
        double[] p1FinalTrajectory = {p1Trajectory[0] + p1Deflect[0] - p2Deflect[0], p1Trajectory[1] + p1Deflect[1] - p2Deflect[1]};
        double[] p2FinalTrajectory = {p2Trajectory[0] + p2Deflect[0] - p1Deflect[0], p2Trajectory[1] + p2Deflect[1] - p1Deflect[1]};
// Calculate the final energy in the system.
        double p1FinalMomentum = Math.sqrt(p1FinalTrajectory[0] * p1FinalTrajectory[0] + p1FinalTrajectory[1] * p1FinalTrajectory[1]);
        double p2FinalMomentum = Math.sqrt((p2FinalTrajectory[0] * p2FinalTrajectory[0] + p2FinalTrajectory[1] * p2FinalTrajectory[1]) * 30);
// Scale the resultant trajectories if we've accidentally broken the laws of Physics.
        double mag = (p1InitialMomentum + p2InitialMomentum) / (p1FinalMomentum + p2FinalMomentum);
// Calculate the final x and y speed settings for the two balls after collision.
        puck.xVelocity = p1FinalTrajectory[0] * mag;
        puck.yVelocity = p1FinalTrajectory[1] * mag;
        // ONLY CHANGE THE PUCK AS paddel IS PLAYER CONTROLLED ASSUME HUGE MASS AND NO CHANGE IN VELOCITY as would have hand in it controlling.
    }

    /**
     * Converts a vector into a unit vector.
     * Used by the deflect() method to calculate the resultant direction after a collision.
     */
    private static double[] normalizeVector(double[] vec) {
        double mag = 0.0;
        int dimensions = vec.length;
        double[] result = new double[dimensions];
        for (int i = 0; i < dimensions; i++)
            mag += vec[i] * vec[i];
        mag = Math.sqrt(mag);
        if (mag == 0.0) {
            result[0] = 1.0;
            for (int i = 1; i < dimensions; i++)
                result[i] = 0.0;
        } else {
            for (int i = 0; i < dimensions; i++)
                result[i] = vec[i] / mag;
        }
        return result;
    }


}