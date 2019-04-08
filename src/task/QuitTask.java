package task;

import biuoop.GUI;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class QuitTask implements Task<Void> {

    private final GUI gui;

    /**
     * quit.
     *
     * @param gui gui
     */
    public QuitTask(GUI gui) {
        this.gui = gui;
    }

    /**
     * close.
     *
     * @return void
     */
    @Override
    public Void run() {
        gui.close();
        return null;
    }
}
