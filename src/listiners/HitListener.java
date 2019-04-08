package listiners;

import geomtry.Block;
import geomtry.Ball;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit bieng hit
     * @param hitter hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
