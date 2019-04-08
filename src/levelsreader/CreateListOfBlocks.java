/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelsreader;

import geomtry.Block;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class CreateListOfBlocks {

    private final int blockStartX;
    private final int blockStratY;
    private final int rowHieght;
    private final int numOfBlocks;
    private final BlocksFromSymbolsFactory blockFactory;
    private final List<String> blocksOrder;

    /**
     * constructor.
     *
     * @param blockStartX int
     * @param blockStratY int
     * @param rowHieght int
     * @param numOfBlocks int
     * @param blockFactory block from symbol
     * @param blocksOrder list
     */
    public CreateListOfBlocks(int blockStartX, int blockStratY, int rowHieght, int numOfBlocks,
            BlocksFromSymbolsFactory blockFactory, List<String> blocksOrder) {
        this.blockFactory = blockFactory;
        this.blockStartX = blockStartX;
        this.blockStratY = blockStratY;
        this.blocksOrder = blocksOrder;
        this.rowHieght = rowHieght;
        this.numOfBlocks = numOfBlocks;
    }

    /**
     * create block.
     *
     * @return list
     */
    public List<Block> creator() {
        List<Block> blocks = new ArrayList<>();
        Block tempBlock;
        int currentHieght = this.blockStratY;
        int currentWidth = this.blockStartX;
        for (int i = 0; i < this.blocksOrder.size(); i++) {
            for (int j = 0; j < this.blocksOrder.get(i).length(); j++) {
                if (this.blockFactory.isBlockSymbol(this.blocksOrder.get(i).substring(j, j + 1))) {
                    tempBlock = (this.blockFactory.getBlock(this.blocksOrder.get(i).substring(j, j + 1),
                            currentWidth, currentHieght));
                    blocks.add(tempBlock);
                    currentWidth += tempBlock.getBlockWidth();
                } else if (this.blockFactory.isSpaceSymbol(this.blocksOrder.get(i).substring(j, j + 1))) {
                    currentWidth += this.blockFactory.getSpaceWidth(this.blocksOrder.get(i).substring(j, j + 1));
                } else {
                    return null;
                }
            }
            currentHieght += this.rowHieght;
            currentWidth = this.blockStartX;
        }
        if (this.numOfBlocks == blocks.size()) {
            return blocks;
        } else {
            return null;
        }
    }
}
