package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameflow.Menu;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 *
 * @param <T> animation
 */
public class MenuAnimation<T> implements Menu<T> {

    private final ArrayList<T> v;
    private final ArrayList<String> k;
    private final ArrayList<String> m;
    private boolean isClicked;
    private final KeyboardSensor keyboardSensor;

    /**
     * constructor.
     *
     * @param keyboardSensor ks
     */
    public MenuAnimation(KeyboardSensor keyboardSensor) {
        this.v = new ArrayList<>();
        this.k = new ArrayList<>();
        this.m = new ArrayList<>();
        this.isClicked = false;
        this.keyboardSensor = keyboardSensor;

    }

    /**
     * add selection.
     *
     * @param key key
     * @param message massage
     * @param returnVal val
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.k.add(key);
        this.m.add(message);
        this.v.add(returnVal);
    }

    /**
     * get status.
     *
     * @return status
     */
    @Override
    public T getStatus() {
        for (int i = 0; i < this.v.size(); i++) {
            if (this.keyboardSensor.isPressed(this.k.get(i))) {
                this.isClicked = true;
                return this.v.get(i);
            }
        }
        return null;
    }

    /**
     * do one frame.
     *
     * @param d drawsurface
     * @param dt dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(Color.black);
        for (int i = 0; i < this.v.size(); i++) {
            d.drawText(180, 75, "arkoniad game", 85);
            d.drawText(50, 200 + (60 * i), "(" + this.k.get(i) + ") - " + this.m.get(i), 40);
        }
        this.getStatus();
    }

    /**
     * if its should stop.
     *
     * @return yes or no
     */
    @Override
    public boolean shouldStop() {
        return this.isClicked;
    }

    /**
     * get number of second per frame.
     *
     * @return num of second
     */
    @Override
    public double getNumOfSecond() {
        return 60;
    }

    /**
     * add sub menu.
     *
     * @param key key
     * @param message massage
     * @param subMenu submenu
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
