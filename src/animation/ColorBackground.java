package animation;

import biuoop.DrawSurface;
import java.awt.Color;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ColorBackground implements Sprite {

    private Color color;

    /**
     *
     */
    public ColorBackground() {
        this.color = null;
    }

    /**
     * drawon.
     *
     * @param d drawsurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, 800, 600);
    }

    /**
     *
     * @param dt dt.
     */
    @Override
    public void timePassed(double dt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * set color.
     *
     * @param colors color.
     */
    public void setColor(Color colors) {
        this.color = colors;
    }

    /**
     * getcolor.
     *
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }

}
