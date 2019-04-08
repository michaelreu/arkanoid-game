package animation;

import sprites.Sprite;
import sprites.SpriteCollection;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class CountdownAnimation implements Animation {

    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private final Sprite background;

    /**
     * constructor.
     *
     * @param numOfSeconds per frame
     * @param countFrom to 0
     * @param gameScreen game screen
     * @param background background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.background = background;
    }

    @Override
    /**
     * do one frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        background.drawOn(d);
        gameScreen.drawAllOn(d);
        d.setColor(Color.white);
        if (countFrom == 0) {
            d.drawText(d.getWidth() / 3, d.getHeight() / 2, "     GO!", 70);
        } else {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2, "Game start in: " + String.valueOf(this.countFrom), 70);
        }
        this.countFrom--;
    }

    @Override
    /**
     * when it should stop.
     */
    public boolean shouldStop() {
        return this.countFrom < 0;
    }

    /**
     * get number of second per frame.
     *
     * @return num of second
     */
    @Override
    public double getNumOfSecond() {
        return this.numOfSeconds;
    }
}
