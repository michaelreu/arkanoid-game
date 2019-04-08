package task;

import gameflow.MainGame;
import gameflow.StageInfo;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class SubMenuTask implements Task<Void> {

    private final StageInfo levels;
    private final MainGame menu;

    /**
     * constructor.
     *
     * @param levels levels
     * @param menu menu
     */
    public SubMenuTask(StageInfo levels, MainGame menu) {
        this.menu = menu;
        this.levels = levels;
    }

    /**
     * run the task.
     *
     * @return null
     */
    @Override
    public Void run() {
        this.menu.makeSubMenu(levels);
        return null;
    }
}
