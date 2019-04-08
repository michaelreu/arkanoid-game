package gamerole;

import geomtry.Point;
import geomtry.Line;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class GameEnvironment {

    private final List<Collidable> collidableList;

    /**
     * new collidableList.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * @param c remove the given collidable from the environment.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * @param c add the given collidable to the environment.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     *
     * @param trajectory line.start() to line.end()
     * @return If this object will not collide with any of the collidables in
     * this collection, return null. Else, return the information about the
     * closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closetPoint = null;
        CollisionInfo collision = null;
        // run allover the collidable list
        for (int i = 0; i < this.collidableList.size(); i++) {
            List<Point> intersection = collidableList.get(i).getCollisionRectangle().intersectionPoints(trajectory);
            // if there is a collision
            if (!intersection.isEmpty()) {
                // make new point with the collision point
                Point collisionPoint = collidableList.get(i).getCollisionRectangle().
                        closetIntersectionPoints(trajectory.start(), intersection);
                // if the new collisionPoint is closet to the ball from the other collisionPoints
                if ((closetPoint == null)
                        || (collisionPoint.distance(trajectory.start()) < closetPoint.distance(trajectory.start()))) {
                    collision = new CollisionInfo(collisionPoint, this.collidableList.get(i));
                }
            }
        }
        return collision;
    }
}
