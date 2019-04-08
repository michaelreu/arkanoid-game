package animation;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class HighScoresAnimation implements Animation {

    private final boolean stop;
    private final HighScoresTable scores;

    /**
     *
     * @param scores scores.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;

    }
    // ...

    /**
     * do one frame.
     *
     * @param d DrawSurface
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(Color.black);
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.drawText(180, 75, "High score:", 75);
            d.drawText(50, 200 + (60 * i), (i + 1) + ") " + this.scores.getHighScores().get(i).getName()
                    + " ---------------------- "
                    + this.scores.getHighScores().get(i).getScore(), 50);
            d.drawText(220, 570, "press space to menu", 30);
        }
    }

    /**
     * true or false.
     *
     * @return if should stop or not
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * num of second per frame.
     *
     * @return num of second per frame
     */
    @Override
    public double getNumOfSecond() {
        return 60;
    }
}
