package gameflow;

import animation.Animation;
import gamerole.GameEnvironment;
import gamerole.BallRemover;
import gamerole.BlockRemover;
import listiners.Counter;
import animation.CountdownAnimation;
import animation.AnimationRunner;
import geomtry.Block;
import geomtry.Ball;
import listiners.LivesIndicator;
import listiners.ScoreTrackingListener;
import sprites.SpriteCollection;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import geomtry.Point;
import geomtry.Paddle;
import geomtry.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamerole.Collidable;
import geomtry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import levels.LevelInformation;
import levels.LevelName;
import sprites.Sprite;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class GameLevel implements Animation {

    private final int hightOfPaddle = 15;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter numOfBlocks;
    private final Counter numOfBalls;
    private final Counter score;
    private final Counter numOfLives;
    private final AnimationRunner runner;
    private boolean running;
    private final biuoop.KeyboardSensor keyboard;
    private final LevelInformation currentLevel;
    private final String levelName;
    private final Sprite background;

    /**
     * constructor.
     *
     * @param currentLevel level
     * @param keyboardSensor kayboard
     * @param animationRunner animation
     * @param gui gui
     * @param score score
     * @param numOfLives num of lives
     */
    public GameLevel(LevelInformation currentLevel, KeyboardSensor keyboardSensor,
            AnimationRunner animationRunner, GUI gui, Counter score, Counter numOfLives) {
        this.gui = gui;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.numOfBlocks = new Counter(0);
        this.numOfBalls = new Counter(0);
        this.score = score;
        this.numOfLives = numOfLives;
        this.runner = animationRunner;
        this.running = true;
        this.keyboard = keyboardSensor;
        this.currentLevel = currentLevel;
        this.levelName = this.currentLevel.levelName();
        this.background = this.currentLevel.getBackground();
    }

    /**
     * add collidable.
     *
     * @param c add a new Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite.
     *
     * @param s add a new Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collidble.
     *
     * @param c add a new Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite.
     *
     * @param s add a new Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    public void initialize() {
        BlockRemover removeBlocks = new BlockRemover(this, numOfBlocks);
        BallRemover removeBalls = new BallRemover(this, numOfBalls);
        ScoreTrackingListener scoreUpdate = new ScoreTrackingListener(score);
        scoreUpdate.addToGame(this);
        LivesIndicator livesUpdate = new LivesIndicator(numOfLives);
        livesUpdate.addToGame(this);
        // make the frame
        Block upperFrame = new Block(new Rectangle(new Point(0, 20), 780, 20), Color.black);
        upperFrame.addToHashMapColor(-1, Color.gray);
        upperFrame.addToGame(this);

        Block leftFrame = new Block(new Rectangle(new Point(0, 20), 20, 580), Color.black);
        leftFrame.addToHashMapColor(-1, Color.gray);
        leftFrame.addToGame(this);

        Block rightFrame = new Block(new Rectangle(new Point(780, 20), 20, 600), Color.black);
        rightFrame.addToHashMapColor(-1, Color.gray);
        rightFrame.addToGame(this);

        // deathRegion
        Block deathRegion = new Block(new Rectangle(new Point(0, 615), 820, 2), Color.black);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(removeBalls);

        // make the inside blocks
        for (int i = 0; i < currentLevel.numberOfBlocksToRemove(); i++) {
            List<Block> blocks;
            blocks = this.currentLevel.getBlocks();
            this.numOfBlocks.increase(1);
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(removeBlocks);
            blocks.get(i).addHitListener(scoreUpdate);
        }
        // make a new balls
        for (int i = 0; i < currentLevel.numberOfBalls(); i++) {
            List<Velocity> balls = this.currentLevel.initialBallVelocities();
            List<Point> ballsLocation = initballsLocation();
            Ball ball = new Ball(ballsLocation.get(i), 6, java.awt.Color.white, this.environment);
            ball.setVelocity(balls.get(i));
            ball.addToGame(this);
            this.numOfBalls.increase(1);
            LevelName newLevelName = new LevelName(this.levelName);
            newLevelName.addToGame(this);
        }

    }

    /**
     * init ball location.
     *
     * @return list of points.
     */
    public List<Point> initballsLocation() {
        List<Point> ballLocation = new ArrayList<>();
        for (int i = 0; i < this.currentLevel.numberOfBalls(); i++) {
            ballLocation.add(new Point(400, 579));
        }
        return ballLocation;
    }

    /**
     * create paddle.
     *
     * @return new paddle
     */
    public Paddle createNewPaddle() {
        // location for the paddle to be in the middle
        int paddleLocation = ((800 / 2) - (this.currentLevel.paddleWidth() / 2));
        // make a new paddle
        biuoop.KeyboardSensor myKeyboard = gui.getKeyboardSensor();
        // 560 is 600 - bottom hight - paddle hight
        Rectangle newRecForPaddle = new Rectangle(new Point(paddleLocation, 585),
                this.currentLevel.paddleWidth(), this.hightOfPaddle);
        Block newBlock = new Block(newRecForPaddle, java.awt.Color.orange);
        Paddle paddle = new Paddle(newBlock, myKeyboard, this.currentLevel.paddleSpeed(),
                this.currentLevel.paddleWidth());
        paddle.addToGame(this);
        return paddle;
    }

    /**
     * put balls on paddle.
     */
    public void createBallsOnTopOfPaddle() {
        // make a new balls
        for (int i = 0; i < currentLevel.numberOfBalls(); i++) {
            List<Velocity> balls = this.currentLevel.initialBallVelocities();
            Ball ball = new Ball(new Point(400, 579), 6, java.awt.Color.white, this.environment);
            ball.setVelocity(balls.get(i));
            ball.addToGame(this);
            this.numOfBalls.increase(1);
        }
    }

    @Override
    /**
     * do one frame.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.background.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(this.runner.getDt());
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
            this.runner.run(new CountdownAnimation(5, 3, this.sprites, this.background));
        }
        if ((this.numOfBlocks.getValue() == 0) || (this.numOfBalls.getValue() == 0)) {
            if (this.numOfBalls.getValue() != 0) {
                this.score.increase(100);
            }
            this.running = false;
        }
    }

    @Override
    /**
     * when stop the animation.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        Paddle paddle = this.createNewPaddle();
        this.runner.run(new CountdownAnimation(3, 3, this.sprites, this.background));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.numOfBlocks.getValue() != 0) {
            this.numOfLives.decrease(1);
        }
        paddle.removeFromGame(this);
        this.createBallsOnTopOfPaddle();
    }

    /**
     * num of second per frame.
     *
     * @return num of second per frame
     */
    @Override
    public double getNumOfSecond() {
        return this.runner.getFframesPerSecond();
    }

    /**
     * get number of remanded blocks.
     *
     * @return num of blocks
     */
    public double getNumOfBlocks() {
        return this.numOfBlocks.getValue();
    }
}
