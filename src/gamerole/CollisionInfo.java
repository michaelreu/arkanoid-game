package gamerole;

import geomtry.Point;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 03.
 */
public class CollisionInfo {
    // constructors.

    private final Point collision;
    private final Collidable object;

    /**
     *
     * @param collision point
     * @param object type of block
     */
    public CollisionInfo(Point collision, Collidable object) {
        this.collision = collision;
        this.object = object;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * collision object.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}
