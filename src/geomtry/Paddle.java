package geomtry;

import gameflow.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamerole.Collidable;
import java.util.ArrayList;
import java.util.List;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class Paddle implements Sprite, Collidable {

    private final biuoop.KeyboardSensor keyboard;
    private final Block paddle;
    private final int paddleSpeed;
    private final int paddleWidth;

    /**
     * constructor.
     *
     * @param paddle the rectangle
     * @param keyboard left and right
     * @param paddleSpeed speed
     * @param paddleWidth width
     */
    public Paddle(Block paddle, biuoop.KeyboardSensor keyboard, int paddleSpeed, int paddleWidth) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
    }

    /**
     * move left the paddle.
     *
     * @param dt dt
     */
    public void moveLeft(double dt) {
        Point upperLeft = this.paddle.getCollisionRectangle().getUpperLeft();
        // if the padlle didnt over the frame
        if (upperLeft.getX() > 20) {
            upperLeft.setX(upperLeft.getX() - (paddleSpeed * dt));
            // move the paddle to the minimum point
        } else {
            upperLeft.setX(20);
        }
        this.paddle.getCollisionRectangle().rectangleLines();
    }

    /**
     * move right the paddle.
     *
     * @param dt dt
     */
    public void moveRight(double dt) {
        Point upperLeft = this.paddle.getCollisionRectangle().getUpperLeft();
        // if the padlle didnt over the frame
        if (upperLeft.getX() < (780 - paddleWidth)) {
            upperLeft.setX(upperLeft.getX() + (paddleSpeed * dt));
            // move the paddle to the maximum point
        } else {
            upperLeft.setX(780 - paddleWidth);
        }
        this.paddle.getCollisionRectangle().rectangleLines();
    }

    @Override
    /**
     * KeyboardSensor if any key clicked
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    @Override
    /**
     * draw the paddle
     */
    public void drawOn(DrawSurface d) {
        Rectangle rec = this.paddle.getCollisionRectangle();
        d.setColor(this.paddle.getColor());
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(java.awt.Color.black);
        d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    @Override
    /**
     * return the shape of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * Add this paddle to the game.
     *
     * @param g game object
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove this paddle from the game.
     *
     * @param g game object
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    /**
     * make the hit of the ball and the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle paddlelimit = this.paddle.getCollisionRectangle();
        // x and y of the upperleft point of the paddle
        double y = paddlelimit.getLeftLine().start().getY();
        double x = paddlelimit.getLeftLine().start().getX() - 5;
        // the regular change of the velocity
        double yChangeAngle = currentVelocity.getDx() + (180 - currentVelocity.getDx()) * 2;
        List<Line> region = new ArrayList<>();
        double partSize = this.paddle.getCollisionRectangle().getWidth() / 5;
        double xupperLeftOfPudlle = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        if (collisionPoint.getX() < partSize + xupperLeftOfPudlle) {
            currentVelocity.setDx(210);
        } else if (collisionPoint.getX() >= partSize + xupperLeftOfPudlle
                && collisionPoint.getX() < (partSize * 2) + xupperLeftOfPudlle) {
            currentVelocity.setDx(240);
        } else if (collisionPoint.getX() >= (partSize * 2) + xupperLeftOfPudlle
                && collisionPoint.getX() < (partSize * 3) + xupperLeftOfPudlle) {
            currentVelocity.setDx(yChangeAngle);
        } else if (collisionPoint.getX() >= (partSize * 3) + xupperLeftOfPudlle
                && collisionPoint.getX() < (partSize * 4) + xupperLeftOfPudlle) {
            currentVelocity.setDx(300);
        } else if (collisionPoint.getX() >= (partSize * 4) + xupperLeftOfPudlle
                && collisionPoint.getX() <= (partSize * 5) + xupperLeftOfPudlle) {
            currentVelocity.setDx(330);
            // hit from the sides
        } else {
            currentVelocity.setDx(yChangeAngle);
        }
        return currentVelocity;
    }
}
