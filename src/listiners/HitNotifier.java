package listiners;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener
     */
    void removeHitListener(HitListener hl);
}
