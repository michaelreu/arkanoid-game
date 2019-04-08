package gamerole;

import listiners.Counter;
import geomtry.Block;
import geomtry.Ball;
import listiners.HitListener;
import gameflow.GameLevel;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class BallRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game game
     * @param removedBlocks remove block
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     *
     * @param beingHit being hit
     * @param hitter hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.game.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
