package gameflow;

import animation.HighScoresTable;
import animation.AnimationRunner;
import task.QuitTask;
import animation.MenuAnimation;
import task.ShowHiScoresTask;
import task.StartGameTask;
import task.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import task.BackTask;
import task.SubMenuTask;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class MainGame {

    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final File highScoreFile;
    private Task<Void> status;
    private final HighScoresTable table;

    /**
     * main game.
     */
    public MainGame() {
        this.gui = new GUI("my game", 800, 600);
        this.animationRunner = new AnimationRunner(gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.table = new HighScoresTable(5);
        this.highScoreFile = new File("highscores.txt");
        this.status = null;
    }

    /**
     * main screen.
     *
     * @param levels levels.
     */
    public void mainScreen(StageInfo levels) {

        if (this.highScoreFile.exists()) {
            try {
                table.load(this.highScoreFile);
            } catch (IOException ex) {
                Logger.getLogger(Ass6Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.highScoreFile.createNewFile();
                this.table.save(highScoreFile);
            } catch (IOException ex) {
                Logger.getLogger(Ass6Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (true) {
            Menu<Task<Void>> menu = new MenuAnimation<>(this.keyboardSensor);
            menu.addSelection("s", "Start game", new SubMenuTask(levels, this));
            menu.addSelection("h", "Hige score", new ShowHiScoresTask(this.animationRunner,
                    this.keyboardSensor, this.table));
            menu.addSelection("e", "Exit", new QuitTask(this.gui));
            // the parameters to addSelection are:
            // key to wait for, line to print, what to return
            this.animationRunner.run(menu);
            this.status = menu.getStatus();
            if (this.status != null) {
                this.status.run();
            }
        }
    }

    /**
     * sub menu.
     *
     * @param levels levels
     * @return task
     */
    public Task<Void> makeSubMenu(StageInfo levels) {
        String symbol;
        this.status = null;
        GameFlow game = new GameFlow(this.animationRunner, this.keyboardSensor, gui, table, this.highScoreFile);
        while (true) {

            Menu<Task<Void>> subMenu = new MenuAnimation<>(this.keyboardSensor);
            for (int i = 0; i < levels.getStageName().size(); i++) {
                symbol = levels.getStageSymbol().get(i);
                subMenu.addSelection(symbol, levels.getStageName().get(i),
                        new StartGameTask(levels.getStages().get(symbol), game));
            }
            subMenu.addSelection("b", "Back", new BackTask());
            // the parameters to addSelection are:
            // key to wait for, line to print, what to return
            this.animationRunner.run(subMenu);
            this.status = subMenu.getStatus();
            if (this.status != null) {
                this.status.run();
                break;
            }
        }
        return null;
    }
}
