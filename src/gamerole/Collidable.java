package gamerole;

import geomtry.Ball;
import geomtry.Point;
import geomtry.Rectangle;
import geomtry.Velocity;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public interface Collidable {

    /**
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     *
     * @param hitter hitter
     * @param collisionPoint point of collision
     * @param currentVelocity of the ball
     * @return the new velocity expected after the hit (based on the force the
     * object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
