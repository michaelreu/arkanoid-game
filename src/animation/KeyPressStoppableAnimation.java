package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class KeyPressStoppableAnimation implements Animation {

    private final Animation animation;
    private final String key;
    private final KeyboardSensor sensor;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * constructor.
     *
     * @param sensor sensor
     * @param key key
     * @param animation animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.isAlreadyPressed = true;
        this.stop = true;

    }

    /**
     * do one frame.
     *
     * @param d d
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = false;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.stop;
    }

    @Override
    public double getNumOfSecond() {
        return 60;
    }
}
