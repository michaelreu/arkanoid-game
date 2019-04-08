package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class AnimationRunner {

    private final GUI gui;
    private final double framesPerSecond;
    private final double dt;

    /**
     * constructor.
     *
     * @param gui gui
     * @param framesPerSecond frame per second
     */
    public AnimationRunner(GUI gui, double framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.dt = 1.0 / 60.0;
    }

    /**
     * run the animation.
     *
     * @param animation animation
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = (int) (1000.0 / animation.getNumOfSecond());
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, this.framesPerSecond);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * how fast the animation wiil run.
     *
     * @return frame per second.
     */
    public double getFframesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     *
     * @return dt.
     */
    public double getDt() {
        return this.dt;
    }
}
