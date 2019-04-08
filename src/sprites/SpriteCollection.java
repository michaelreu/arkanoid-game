package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class SpriteCollection {

    private final List<Sprite> sprites;

    /**
     * make new sprite list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * add new sprite to the list.
     *
     * @param s new sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * add new sprite to the list.
     *
     * @param s new sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     *
     * @param dt dt
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface of the game
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}
