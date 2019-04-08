package geomtry;

import gamerole.GameEnvironment;
import gamerole.CollisionInfo;
import listiners.HitNotifier;
import listiners.HitListener;
import sprites.Sprite;
import gameflow.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class Ball implements Sprite, HitNotifier {

    private Point center;
    private final double radious;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;
    private List<HitListener> hitListeners;
    private boolean frameChange;

    /**
     * constructor.
     *
     * @param center of ball
     * @param r radius of ball
     * @param color of ball
     * @param game the game
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radious = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = game;
        this.hitListeners = new ArrayList<>();
        this.frameChange = true;
    }

    /**
     * constructor.
     *
     * @param x of ball
     * @param y of ball
     * @param r radius of ball
     * @param color of ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radious = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameChange = true;
    }

    /**
     * constructor.
     *
     * @param p point of the center of the ball
     * @param r the size of the ball
     * @param color the color of the ball
     *
     */
    public Ball(Point p, int r, java.awt.Color color) {
        this.center = p;
        this.radious = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.frameChange = true;
    }

    // accessors
    /**
     * @return x value of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius of the circle.
     */
    public double getSize() {
        return this.radious;
    }

    /**
     * @return color of the circle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * set the velocity of the ball.
     *
     * @param v velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball.
     *
     * @param dx change of x
     * @param dy change of y
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface draw
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.radious);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), (int) this.radious);
        surface.setColor(Color.red);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), 1);
    }

    /**
     * move the ball one step forward and change his angle. if it reach to the
     * and of the screen
     *
     * @param dt dt
     */
    public void moveOneStep(double dt) {
        if (this.frameChange) {
            this.velocity = new Velocity(this.getVelocity().getDx(), this.getVelocity().getDy() * dt);
            this.frameChange = false;
        }

        //upper intercetion
        List<Point> centerOption;
        boolean flag = true;
        // list of 4 locations of the ball + raduis and each optinon with a diffrent
        centerOption = centerCheck();
        // check if the ball hit one of the line of the block
        for (int i = 0; i < centerOption.size(); i++) {
            Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(centerOption.get(i)));
            CollisionInfo collidable = this.game.getClosestCollision(trajectory);
            // if there is a collidable
            if (!(collidable == null)) {
                collidable.collisionObject().hit(this, collidable.collisionPoint(), velocity);
                this.center = this.getVelocity().applyToPoint(this.center);
                flag = false;
            }
        }
        // if there is no collidable
        if (flag) {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     *
     * @return list of 4 locations of the ball + radius and each option with a
     * different
     */
    public List<Point> centerCheck() {
        List<Point> centerOption = new ArrayList<>();
        centerOption.add(new Point((int) this.center.getX() + this.radious, this.center.getY()));
        centerOption.add(new Point((int) this.center.getX(), this.center.getY() - this.radious));
        centerOption.add(new Point((int) this.center.getX() - this.radious, this.center.getY()));
        centerOption.add(new Point((int) this.center.getX(), this.center.getY() + this.radious));
        return centerOption;
    }

    @Override
    /**
     * moving the ball
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * add the ball to the game.
     *
     * @param g the game environment
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    // Remove hl from the list of listeners to hit events.

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify.
     *
     * @param beingHit beinghit
     */
    private void notifyHit(Block beingHit) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(beingHit, this);
        }
    }

    /**
     * remove the block from the game.
     *
     * @param gameLevel game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
