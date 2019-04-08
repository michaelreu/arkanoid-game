package animation;

import biuoop.DrawSurface;
import java.awt.Image;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ImageBackground implements Sprite {

    private Image image;

    /**
     * constructor.
     */
    public ImageBackground() {
        this.image = null;
    }

    /**
     * drawon.
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, image);
    }

    /**
     * timepassed.
     *
     * @param dt dt.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * set image.
     *
     * @param images image.
     */
    public void setImage(Image images) {
        this.image = images;
    }

    /**
     * get image.
     *
     * @return image.
     */
    public Image getImage() {
        return this.image;
    }
}
