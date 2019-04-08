package animation;

import biuoop.DrawSurface;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class PauseScreen implements Animation {

    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * do one frame.
     *
     * @param d DrawSurface
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
