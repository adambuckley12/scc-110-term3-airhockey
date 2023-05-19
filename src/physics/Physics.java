package physics;

import shapes.Sphere;
import shapes.base.BaseShape;

public class Physics {

    public static void deflect(BaseShape puck, BaseShape paddle) {

        // Calculate initial momentum of the spheres... We assume unit mass here. //TODO Allow for mass of spheres as a setting and implement here maybe???
        double puckInitialMomentum = Math.sqrt(puck.xVelocity * puck.xVelocity + puck.yVelocity * puck.yVelocity);
        double paddleInitialMomentum = Math.sqrt((paddle.xVelocity * paddle.xVelocity + paddle.yVelocity * paddle.yVelocity) * 8); //ASSUMING PUCK HAS MUCH LARGER MASS AS WOULD HAVE A HAND IN IT AND FORCE APPLIED
        // calculate motion vectors
        double[] puckTrajectory = {puck.xVelocity, puck.yVelocity};
        double[] paddleTrajectory = {paddle.xVelocity, paddle.yVelocity};
        // Calculate Impact Vector
        double[] impactVector = {paddle.x - puck.x, paddle.y - puck.y};
        double[] impactVectorNorm = normalizeVector(impactVector);
        // Calculate scalar product of each trajectory and impact vector
        double puckDotImpact = Math.abs(puckTrajectory[0] * impactVectorNorm[0] + puckTrajectory[1] * impactVectorNorm[1]);
        double paddleDotImpact = Math.abs(paddleTrajectory[0] * impactVectorNorm[0] + paddleTrajectory[1] * impactVectorNorm[1]);
        // Calculate the deflection vectors - the amount of energy transferred from one ball to the other in each axis
        double[] puckDeflect = {-impactVectorNorm[0] * paddleDotImpact, -impactVectorNorm[1] * paddleDotImpact};
        double[] paddleDeflect = {impactVectorNorm[0] * puckDotImpact, impactVectorNorm[1] * puckDotImpact};
        // Calculate the final trajectories
        double[] puckFinalTrajectory = {puckTrajectory[0] + puckDeflect[0] - paddleDeflect[0], puckTrajectory[1] + puckDeflect[1] - paddleDeflect[1]};
        double[] paddleFinalTrajectory = {paddleTrajectory[0] + paddleDeflect[0] - puckDeflect[0], paddleTrajectory[1] + paddleDeflect[1] - puckDeflect[1]};
        // Calculate the final energy in the system.
        double puckFinalMomentum = Math.sqrt(puckFinalTrajectory[0] * puckFinalTrajectory[0] + puckFinalTrajectory[1] * puckFinalTrajectory[1]);
        double paddleFinalMomentum = Math.sqrt((paddleFinalTrajectory[0] * paddleFinalTrajectory[0] + paddleFinalTrajectory[1] * paddleFinalTrajectory[1]) * 5);
        // Scale the resultant trajectories if we've accidentally broken the laws of Physics.
        double mag = (puckInitialMomentum + paddleInitialMomentum) / (puckFinalMomentum + paddleFinalMomentum);
        // Calculate the final x and y speed settings for the two balls after collision.
        puck.xVelocity = puckFinalTrajectory[0] * mag;
        puck.yVelocity = puckFinalTrajectory[1] * mag;

        //Decrease velocity of paddle to account force applied.
        // MOMENTUM IS NOT CONSERVED HERE AS WOULD HAVE A HAND IN IT AND FORCE APPLIED. so is physics accurate ish as cant simulate a hand force easily.
        // this is just rly to prevent clipping where bounces multiple times in same place as move at same speed so stays inside puck, makes it smoother
        paddle.xVelocity *= 0.9;
        paddle.yVelocity *= 0.9;

        //Making sure puck is not inside paddle (e.g. pressed against a wall so moves through paddle)
        //makes bounces more realistic, smoother and stops weird clipping where it keeps bouncing in same place

        if (((Sphere) puck).collides((Sphere) paddle)) {
            //calc direction of puck from paddle
            double[] impactVector2 = {paddle.x - puck.x, paddle.y - puck.y};

            //check distance between puck and paddle is less than radius of paddle and puck
            double distance = Math.sqrt(Math.pow(paddle.x - puck.x, 2) + Math.pow(paddle.y - puck.y, 2));
            double radius = (double) paddle.width / 2 + (double) puck.width / 2;
            if (distance > radius) {
                return;
            }
            //move paddle enough so that edge of paddle is touching puck but not overlapping
            paddle.x += impactVector2[0] * (radius - distance) / distance;
            paddle.y += impactVector2[1] * (radius - distance) / distance;
        }

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
