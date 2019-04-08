package levelsreader;

import geomtry.Block;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public interface BlockCreator {
    // Create a block at the specified location.

    /**
     * create.
     *
     * @param xpos pos
     * @param ypos ypos
     * @return block
     */
    Block create(int xpos, int ypos);
}
