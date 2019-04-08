package listiners;

import geomtry.Block;
import geomtry.Ball;
import gameflow.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class ScoreTrackingListener implements HitListener, Sprite {

    private final Counter score;

    /**
     * constructor.
     *
     * @param score score
     */
    public ScoreTrackingListener(Counter score) {
        this.score = score;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     *
     * @param beingHit being hit
     * @param hitter hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.score.increase(5);
        if (beingHit.getNumOfHits() <= 0) {
            this.score.increase(5);
        }
    }

    /**
     * draw the sprite.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawRectangle(0, 0, 800, 20);
        d.drawText(600, 15, String.valueOf("Score: " + this.score.getValue()), 15);
    }

    /**
     * notify the sprite that time has passed.
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
        g.addSprite(this);
    }
}
