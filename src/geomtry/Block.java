package geomtry;

import listiners.HitNotifier;
import listiners.HitListener;
import sprites.Sprite;
import gameflow.GameLevel;
import biuoop.DrawSurface;
import gamerole.Collidable;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // constructor.
    private final Rectangle rec;
    private final java.awt.Color frameColor;
    private int hits;
    private final List<HitListener> hitListeners;
    private final HashMap<Integer, Color> blockColors;
    private final HashMap<Integer, Image> blockImage;

    /**
     * Create a new block with rectangle and frameColor.
     *
     * @param shape of the block
     * @param color of the block
     */
    public Block(Rectangle shape, java.awt.Color color) {
        this.rec = shape;
        this.frameColor = color;
        this.hits = -1;
        this.hitListeners = new ArrayList<>();
        this.blockColors = new HashMap<>();
        this.blockImage = new HashMap<>();
    }

    /**
     * Create a new block with rectangle and frameColor.
     *
     * @param shape of the block
     * @param color of the block
     * @param hits of the ball
     */
    public Block(Rectangle shape, java.awt.Color color, int hits) {
        this.rec = shape;
        this.frameColor = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
        this.blockColors = new HashMap<>();
        this.blockImage = new HashMap<>();
    }

    /**
     * constructor.
     *
     * @param shape shape
     * @param color color
     * @param hits num of hits
     * @param blockColors colors
     * @param blockImage image
     */
    public Block(Rectangle shape, java.awt.Color color, int hits,
            HashMap<Integer, Color> blockColors, HashMap<Integer, Image> blockImage) {
        this.rec = shape;
        this.frameColor = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<>();
        this.blockColors = blockColors;
        this.blockImage = blockImage;
    }

    @Override
    /**
     * Return the "collision shape" of the object.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     *
     * @return the frameColor of the block
     */
    public java.awt.Color getColor() {
        return this.frameColor;
    }

    @Override
    //
    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     *
     * @param collisionPoint of the block and the ball
     * @param currentVelocity of the ball
     * @return the new velocity expected after the hit (based on the force the
     * object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // line with the same start and end
        Line collision = new Line(collisionPoint, collisionPoint);
        double yChangeAngle = currentVelocity.getDx() + (180 - currentVelocity.getDx()) * 2;
        double xChangeAngle = currentVelocity.getDx() + (90 - currentVelocity.getDx()) * 2;
        // if the collision is from buttom or top
        if ((this.getCollisionRectangle().getUpperLine().isIntersecting(collision))
                || (this.getCollisionRectangle().getDowntLine().isIntersecting(collision))) {
            currentVelocity.setDx(yChangeAngle);
        }
        // if the collision if from right or left
        if (this.getCollisionRectangle().getLeftLine().isIntersecting(new Line(collisionPoint, collisionPoint))
                || (this.getCollisionRectangle().getRightLine().isIntersecting(
                        new Line(collisionPoint, collisionPoint)))) {
            currentVelocity.setDx(xChangeAngle);
        }
        // count the number of the hits
        if (hits >= 1) {
            this.hits--;
        }
        this.notifyHit(hitter);
        return (currentVelocity);
    }

    /**
     * draw the block.
     *
     * @param d drawsurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        int xPus = (int) this.rec.getUpperLeft().getX();
        int yPus = (int) this.rec.getUpperLeft().getY();
        // draw the block
        if (this.blockImage.get(this.hits) != null) {
            d.drawImage(xPus, yPus, this.blockImage.get(this.hits));
        } else if (this.blockColors.get(this.hits) != null) {
            d.setColor(this.blockColors.get(this.hits));
            d.fillRectangle(xPus, yPus, (int) rec.getWidth(), (int) rec.getHeight());
        } else if (this.blockColors.containsKey(1)) {
            d.setColor(this.blockColors.get(1));
            d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                    (int) rec.getWidth(), (int) rec.getHeight());
        } else if (this.blockImage.containsKey(1)) {
            d.drawImage(hits, hits, this.blockImage.get(1));
        }
        // make a frame for it
        if (this.frameColor != null) {
            d.setColor(this.frameColor);
            d.drawRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                    (int) rec.getWidth(), (int) rec.getHeight());
        }
    }

    /**
     *
     * @param dt dt
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the block to the game.
     *
     * @param g game environment
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove the block to the game.
     *
     * @param game game environment
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     *
     * @return the center of the block
     */
    public Point getCenter() {
        Line slant = new Line(this.rec.getUpperLeft(), this.rec.getRightLine().start());
        return slant.middle();
    }

    /**
     * num of hits.
     *
     * @return num of hits
     */
    public int getNumOfHits() {
        return this.hits;
    }

    /**
     * notify.
     *
     * @param hitter hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        listeners.forEach(new Consumer<HitListener>() {
            @Override
            public void accept(HitListener hl) {
                hl.hitEvent(Block.this, hitter);
            }
        });
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     *
     * @param k int
     * @param color color
     */
    public void addToHashMapColor(Integer k, Color color) {
        this.blockColors.put(k, color);
    }

    /**
     *
     * @param k int
     * @param image image
     */
    public void addToHashMapImage(Integer k, Image image) {
        this.blockImage.put(k, image);
    }

    /**
     * block width.
     *
     * @return Returns the width in pixels associated.
     */
    public double getBlockWidth() {
        return this.rec.getWidth();
    }
}
