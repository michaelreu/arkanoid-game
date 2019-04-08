package sprites;

import biuoop.DrawSurface;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public interface Sprite {

    /**
     * draw the sprite.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt dt
     */
    void timePassed(double dt);
}
