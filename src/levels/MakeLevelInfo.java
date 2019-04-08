package levels;

import geomtry.Block;

import geomtry.Velocity;
import java.util.List;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class MakeLevelInfo implements LevelInformation {

    private int numOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     *
     * @param num num
     */
    @Override
    public void setNumOfBalls(int num) {
        this.numOfBalls = num;
    }

    /**
     *
     * @return list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     *
     * @param velocity velocity
     */
    @Override
    public void setVelocities(List<Velocity> velocity) {
        this.velocities = velocity;
    }

    /**
     * paddle speed.
     *
     * @return int
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * set speed.
     *
     * @param speed speed
     */
    @Override
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * width.
     *
     * @return int
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * set paddle.
     *
     * @param width paddle
     */
    @Override
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * level name.
     *
     * @return string
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * set name.
     *
     * @param name string
     */
    @Override
    public void setLevelName(String name) {
        this.levelName = name;
    }

    /**
     * background.
     *
     * @return sprite
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * set newBackground.
     *
     * @param newBackground sprite
     */
    @Override
    public void setBackground(Sprite newBackground) {
        this.background = newBackground;
    }

    /**
     * get block.
     *
     * @return list
     */
    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * set block.
     *
     * @param block list
     */
    @Override
    public void setBlocksList(List<Block> block) {
        this.blocks = block;
    }

    /**
     * num of blocks.
     *
     * @return list
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * set num of blocks.
     *
     * @param num num
     */
    @Override
    public void setNumOfBlocks(int num) {
        this.numberOfBlocksToRemove = num;
    }

}
