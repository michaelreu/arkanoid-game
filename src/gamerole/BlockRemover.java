package gamerole;

import listiners.Counter;
import geomtry.Block;
import geomtry.Ball;
import listiners.HitListener;
import gameflow.GameLevel;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public class BlockRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game game
     * @param removedBlocks remove the block
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
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
        if (beingHit.getNumOfHits() <= 0) {
            this.game.removeCollidable(beingHit);
            this.game.removeSprite(beingHit);
            this.remainingBlocks.decrease(1);
        }
    }
}
