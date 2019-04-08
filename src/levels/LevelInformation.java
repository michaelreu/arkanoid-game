package levels;

import geomtry.Block;
import sprites.Sprite;
import geomtry.Velocity;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
public interface LevelInformation {

    /**
     * num of balls.
     *
     * @return num of balls.
     */
    int numberOfBalls();

    /**
     * set num.
     *
     * @param num num
     */
    void setNumOfBalls(int num);

    /**
     * list of balls velocity.
     *
     * @return list of balls velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * list of balls velocity.
     *
     * @param velocity list
     */
    void setVelocities(List<Velocity> velocity);

    /**
     * paddle speed.
     *
     * @return paddle speed
     */
    int paddleSpeed();

    /**
     * paddle speed.
     *
     * @param speed speed
     */
    void setPaddleSpeed(int speed);

    /**
     * paddleWidth.
     *
     * @return paddleWidth
     */
    int paddleWidth();

    /**
     * paddle width.
     *
     * @param width int
     */
    void setPaddleWidth(int width);

    /**
     * levelName.
     *
     * @return levelName
     */
    String levelName();

    /**
     * level name.
     *
     * @param name string
     */
    void setLevelName(String name);

    /**
     * getBackground.
     *
     * @return background
     */
    Sprite getBackground();

    /**
     * set background.
     *
     * @param background sprite
     */
    void setBackground(Sprite background);

    /**
     * make the blocks.
     *
     * @return list of block
     */
    List<Block> getBlocks();

    /**
     * set blocks.
     *
     * @param blocks list
     */
    void setBlocksList(List<Block> blocks);

    /**
     * the number of blocks.
     *
     * @return numberOfBlocksToRemove
     */
    int numberOfBlocksToRemove();

    /**
     * set num of blocks.
     *
     * @param num int
     */
    void setNumOfBlocks(int num);

}
