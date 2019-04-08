package levels;

import gameflow.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class LevelName implements Sprite {

    private final String name;

    /**
     * constructor.
     *
     * @param name name
     */
    public LevelName(String name) {
        this.name = name;
    }

    /**
     * draw the sprite.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(300, 15, String.valueOf("level name: " + this.name), 15);
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
