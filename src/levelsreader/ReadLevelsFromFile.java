/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelsreader;

import animation.ColorBackground;
import animation.ImageBackground;
import geomtry.Block;
import geomtry.Velocity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import levels.LevelInformation;
import levels.MakeLevelInfo;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class ReadLevelsFromFile {

    private List<String> stringLevel;
    private final List<LevelInformation> levels;

    /**
     * constructor.
     */
    public ReadLevelsFromFile() {

        this.stringLevel = new ArrayList<>();
        this.levels = new ArrayList<>();

    }

    /**
     * load and read.
     *
     * @param reader reader
     * @return list
     */
    public List<LevelInformation> loadAndRead(java.io.Reader reader) {
        BufferedReader is = null;
        LevelInformation tempLevel;
        try {
            is = new BufferedReader(reader);
            String line;
            while ((line = is.readLine()) != null) {
                if (!(line.equalsIgnoreCase("END_LEVEL"))) {
                    this.stringLevel.add(line);
                } else {
                    //end level
                    is.readLine();
                    tempLevel = (makeLevelFromString(this.stringLevel));
                    if (tempLevel != null) {
                        this.levels.add(tempLevel);
                    }
                    this.stringLevel = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        return this.levels;

    }

    /**
     * level maker.
     *
     * @param stringOfLevel string level
     * @return level informatin
     */
    public LevelInformation makeLevelFromString(List<String> stringOfLevel) {
        int blockStartX = 0;
        int blockStratY = 0;
        int rowHieght = 0;
        int numOfBlocks = 0;
        List<String> blocksPlacesList = new ArrayList<>();
        List<Block> blocksArray;
        BlocksFromSymbolsFactory blockFactory = null;

        MakeLevelInfo level = new MakeLevelInfo();
        String[] temp;
        for (int i = 1; i < stringOfLevel.size(); i++) {
            temp = stringOfLevel.get(i).split(":");
            switch (temp[0]) {
                case "level_name":
                    level.setLevelName(temp[1]);
                    break;
                case "ball_velocities":
                    List<Velocity> ballsVelocityArr = new ArrayList<>();
                    String[] vilocity = temp[1].split(" ");
                    for (String vilocity1 : vilocity) {
                        String[] angleAndSpeed = vilocity1.split(",");
                        int angle = Integer.parseInt(angleAndSpeed[0]);
                        int speed = Integer.parseInt(angleAndSpeed[1]);
                        ballsVelocityArr.add(new Velocity(angle, speed));
                    }
                    level.setNumOfBalls(ballsVelocityArr.size());
                    level.setVelocities(ballsVelocityArr);
                    break;
                case "background":
                    if (temp[1].startsWith("color")) {
                        ColorBackground backgroundColor = new ColorBackground();
                        ColorsParser makeColor = new ColorsParser();
                        backgroundColor.setColor(makeColor.colorFromStr(temp[1]));
                        if (backgroundColor.getColor() != null) {
                            level.setBackground(backgroundColor);
                        } else {
                            return null;
                        }
                    } else if (temp[1].startsWith("image")) {
                        ImageBackground imageBackground = new ImageBackground();
                        ImageParser imageGetter = new ImageParser();
                        imageBackground.setImage(imageGetter.load(temp[1]));
                        if (imageBackground.getImage() != null) {
                            level.setBackground(imageBackground);
                        }
                    }
                    break;
                case "paddle_speed":
                    int paddleSpeed = checkIfPos(temp[1]);
                    if (paddleSpeed != -1) {
                        level.setPaddleSpeed(paddleSpeed);
                    } else {
                        return null;
                    }
                    break;
                case "paddle_width":
                    int paddleWidth = checkIfPos(temp[1]);
                    if (paddleWidth != -1) {
                        level.setPaddleWidth(paddleWidth);
                    } else {
                        return null;
                    }
                    break;
                case "block_definitions":
                    //                String[] text = temp[1].split("/");
                    Reader file = new InputStreamReader(ClassLoader.getSystemResourceAsStream(temp[1]));
                    blockFactory = BlocksDefinitionReader.fromReader(file);
                    if (blockFactory == null) {
                        return null;
                    }
                    break;
                case "blocks_start_x":
                    blockStartX = checkIfPos(temp[1]);
                    if (blockStartX == -1) {
                        return null;
                    }
                    break;
                case "blocks_start_y":
                    blockStratY = checkIfPos(temp[1]);
                    if (blockStratY == -1) {
                        return null;
                    }
                    break;
                case "row_height":
                    rowHieght = checkIfPos(temp[1]);
                    if (rowHieght == -1) {
                        return null;
                    }
                    break;
                case "num_blocks":
                    numOfBlocks = checkIfPos(temp[1]);
                    if (numOfBlocks != -1) {
                        level.setNumOfBlocks(numOfBlocks);
                    } else {
                        return null;
                    }
                    break;
                case "START_BLOCKS":
                    blocksPlacesList = getInfoBlocks(stringOfLevel, i + 1);
                    break;
                default:
                    break;
            }
        }
        CreateListOfBlocks create = new CreateListOfBlocks(blockStartX, blockStratY, rowHieght,
                numOfBlocks, blockFactory, blocksPlacesList);
        blocksArray = create.creator();
        if (blocksArray != null) {
            level.setBlocksList(blocksArray);
        } else {
            return null;
        }
        return level;
    }

    /**
     * check if pos.
     *
     * @param s String
     * @return int
     */
    public int checkIfPos(String s) {
        try {
            int num = Integer.parseInt(s);
            if (num > 0) {
                return num;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
        return -1;
    }

    /**
     * get info block.
     *
     * @param level level
     * @param i index
     * @return info
     */
    public List<String> getInfoBlocks(List<String> level, int i) {
        List<String> blockDetials = new ArrayList<>();
        while (!level.get(i).equals("END_BLOCKS")) {
            blockDetials.add(level.get(i));
            i++;
        }
        return blockDetials;
    }

}
