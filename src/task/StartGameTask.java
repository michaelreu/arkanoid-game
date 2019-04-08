package task;

import gameflow.GameFlow;
import java.util.List;
import levels.LevelInformation;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class StartGameTask implements Task<Void> {

    private final List<LevelInformation> levels;
    private final GameFlow game;

    /**
     * constructor.
     *
     * @param levels levels
     * @param game game
     */
    public StartGameTask(List<LevelInformation> levels, GameFlow game) {
        this.levels = levels;
        this.game = game;

    }

    /**
     * run the task.
     *
     * @return null
     */
    @Override
    public Void run() {
        game.runLevels(levels);
        return null;
    }
}
