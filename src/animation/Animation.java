package animation;

import biuoop.DrawSurface;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public interface Animation {

    /**
     * do one frame.
     *
     * @param d drawsurface
     * @param dt dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * if its should stop.
     *
     * @return yes or no
     */
    boolean shouldStop();

    /**
     * get number of second per frame.
     *
     * @return num of second
     */
    double getNumOfSecond();
}
