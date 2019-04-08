/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levelsreader;

import geomtry.Block;
import geomtry.Point;
import geomtry.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class BlocksDefinitionReader {

    /**
     * block reader.
     *
     * @param reader file
     * @return factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        HashMap<String, BlockCreator> blockCreators = new HashMap<>();
        final List<HashMap<String, String>> blocks = new ArrayList<>();
        HashMap<String, Integer> spacerWidths;
        String defultinfo = null;
        List<String> blockDefinitions = new ArrayList<>();
        List<String> spacersDefinitions = new ArrayList<>();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line;
            while ((line = is.readLine()) != null) {
                if (line.startsWith("default")) {
                    defultinfo = line;
                } else if (line.startsWith("bdef")) {
                    blockDefinitions.add(line);
                } else if (line.startsWith("sdef")) {
                    spacersDefinitions.add(line);
                }
            }

            // new check for sting of block info and defult block
            DefultInfoForBlock defultCheck = new DefultInfoForBlock();
            defultCheck.defultInitValue();
            // if there is a defult line in the file
            ColorsParser getColor = new ColorsParser();
            ImageParser getImage = new ImageParser();
            // build list of hashmap each hashmap include block info
            for (int i = 0; i < blockDefinitions.size(); i++) {
                defultCheck.defultReset();
                defultCheck.splitDefultInfo(defultinfo);
                blocks.add(defultCheck.splitDefultInfo(blockDefinitions.get(i)));
                final Integer width = makeIntFromString(blocks.get(i).get("width"));
                final Integer height = makeIntFromString(blocks.get(i).get("height"));
                final Integer hitPoints = makeIntFromString(blocks.get(i).get("hit_points"));
                final ColorsParser color = new ColorsParser();
                final Color stroke = color.colorFromStr(blocks.get(i).get("stroke"));
                final String symbol = blocks.get(i).get("symbol");
                HashMap<Integer, Color> tempblockscolor = new HashMap<>();
                HashMap<Integer, Image> tempblocksImage = new HashMap<>();
                for (int j = 1; j < blocks.get(i).size() - 1; j++) {

                    if (blocks.get(i).containsKey(String.valueOf(j))) {
                        // color type
                        if (blocks.get(i).get(String.valueOf(j)).startsWith("color")) {
                            tempblockscolor.put(j, getColor.colorFromStr(blocks.get(i).get(String.valueOf(j))));
                            //Image type
                        } else if ((blocks.get(i).get(String.valueOf(j)).startsWith("image"))) {
                            tempblocksImage.put(j, getImage.load(blocks.get(i).get(String.valueOf(j))));
                        }
                    }

                }
                final HashMap<Integer, Color> colorBlocks = tempblockscolor;
                final HashMap<Integer, Image> blocksImage = tempblocksImage;
                BlockCreator bc;
                bc = new BlockCreator() {
                    @Override
                    public Block create(int xpos, int ypos) {
                        Rectangle rec = new Rectangle(new Point(xpos, ypos), width, height);
                        Block block = new Block(rec, stroke, hitPoints, colorBlocks, blocksImage);
                        return block;
                    }
                };

                blockCreators.put(symbol, bc);
            }
            CheckSpaces space = new CheckSpaces();
            spacerWidths = space.insertSpace(spacersDefinitions);
            return (new BlocksFromSymbolsFactory(spacerWidths, blockCreators));
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
        return null;
    }

    /**
     * make int.
     *
     * @param s s
     * @return int
     */
    public static Integer makeIntFromString(String s) {
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
}
