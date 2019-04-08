package listiners;

import geomtry.Block;
import geomtry.Ball;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class PrintingHitListener implements HitListener {

    /**
     * hit event.
     *
     * @param beingHit being hit
     * @param hitter hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getCenter() + " points was hit.");
    }
}
