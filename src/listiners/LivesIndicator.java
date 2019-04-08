package listiners;

import sprites.Sprite;
import gameflow.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class LivesIndicator implements Sprite {

    private final Counter lives;

    /**
     * constructor.
     *
     * @param lives lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * draw the sprite.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(100, 15, String.valueOf("lives: " + this.lives.getValue()), 15);
    }

    /**
     * time passed.
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
