package gameflow;

import animation.HighScoresTable;
import listiners.Counter;
import animation.AnimationRunner;
import listiners.ScoreInfo;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import levels.LevelInformation;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class GameFlow {

    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter score;
    private final Counter numOfLives;
    private final HighScoresTable higeScore;
    private final File fileName;

    /**
     * constructor.
     *
     * @param ar animationRunner
     * @param ks keyboardSensor
     * @param gui gui
     * @param higeScore high score
     * @param fileName file
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable higeScore, File fileName) {
        this.animationRunner = ar;
        this.gui = gui;
        this.keyboardSensor = ks;
        this.score = new Counter(0);
        this.numOfLives = new Counter(7);
        this.higeScore = higeScore;
        this.fileName = fileName;

    }

    /**
     * play all the level one by one.
     *
     * @param levels levels to play
     */
    public void runLevels(List<LevelInformation> levels) {
        this.score.setValue(0);
        this.numOfLives.setValue(7);
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui,
                    this.score, this.numOfLives);

            level.initialize();

            while ((level.getNumOfBlocks() > 0) && (this.numOfLives.getValue() > 0)) {
                level.playOneTurn();
            }
            if (this.numOfLives.getValue() == 0) {
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.numOfLives.getValue(),
                        this.score.getValue())));
        int place = this.higeScore.getRank(this.score.getValue());
        if (place != -1) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            if (this.higeScore.getHighScores().size() != this.higeScore.size()) {
                this.higeScore.getHighScores().add(place, new ScoreInfo(name, this.score.getValue()));
            } else {
                this.higeScore.getHighScores().set(place, new ScoreInfo(name, this.score.getValue()));
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.higeScore)));
        try {
            this.higeScore.save(fileName);
        } catch (IOException ex) {
            Logger.getLogger(GameFlow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
