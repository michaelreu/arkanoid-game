package gameflow;

import animation.Animation;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 *
 * @param <T> t
 */
public interface Menu<T> extends Animation {

    /**
     * add selection.
     *
     * @param key key.
     * @param message messege
     * @param returnVal value
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get status.
     *
     * @return task
     */
    T getStatus();

    /**
     * sub menu.
     *
     * @param key key
     * @param message message
     * @param subMenu sub menu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
