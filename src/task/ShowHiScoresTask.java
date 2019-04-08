package task;

import animation.HighScoresTable;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ShowHiScoresTask implements Task<Void> {

    private final AnimationRunner runner;
    private final KeyboardSensor keyboardSensor;
    private final HighScoresTable higeScore;

    /**
     * high score task.
     *
     * @param runner animation
     * @param ks keyboard
     * @param higeScore table
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyboardSensor ks, HighScoresTable higeScore) {
        this.runner = runner;
        this.keyboardSensor = ks;
        this.higeScore = higeScore;
    }

    /**
     * run the task.
     *
     * @return void
     */
    @Override
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.higeScore)));
        return null;
    }
}
