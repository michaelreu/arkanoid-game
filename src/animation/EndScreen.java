package animation;

import biuoop.DrawSurface;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class EndScreen implements Animation {

    private boolean stop;
    private int numOfLives;
    private int score;

    /**
     * constructor.
     *
     * @param numOfLives num of livs
     * @param score score
     */
    public EndScreen(int numOfLives, int score) {
        this.stop = false;
        this.numOfLives = numOfLives;
        this.score = score;
    }

    @Override
    /**
     * do one frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (numOfLives == 0) {
            d.drawText(d.getWidth() / 4, d.getHeight() / 3, "Game Over!", 80);
            d.drawText(d.getWidth() / 3, d.getHeight() / 3 + 85, "your score is: " + this.score, 32);
        } else {
            d.drawText(d.getWidth() / 4, d.getHeight() / 3, "Winner!!!", 80);
            d.drawText(d.getWidth() / 3, d.getHeight() / 3 + 85, "your score is: " + this.score, 32);
        }
        d.drawText(500, 570, "press (space) to continue", 25);
    }

    @Override
    /**
     * when it should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * get number of second per frame.
     *
     * @return num of second
     */
    @Override
    public double getNumOfSecond() {
        return 60;
    }
}
