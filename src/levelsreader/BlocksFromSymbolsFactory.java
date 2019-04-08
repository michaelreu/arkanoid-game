package levelsreader;

import geomtry.Block;
import java.util.Map;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * factory.
     *
     * @param spacerWidths space
     * @param blockCreators creator
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.blockCreators = blockCreators;
        this.spacerWidths = spacerWidths;

    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s string
     * @return if is space
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);

    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s string
     * @return is block
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    /**
     * get block.
     *
     * @param s stirng
     * @param xpos int
     * @param ypos int
     * @return block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    /**
     * get space width.
     *
     * @param s string
     * @return int
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

}
