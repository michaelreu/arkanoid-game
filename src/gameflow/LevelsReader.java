/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameflow;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class LevelsReader {

    private StageInfo split;

    /**
     * constructor.
     */
    public LevelsReader() {
        this.split = new StageInfo();
    }

    /**
     * run game.
     *
     * @param path path
     */
    public void run(String path) {
        Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(path));
        SplitToLevels makeStages = new SplitToLevels();
        this.split = makeStages.readAndSplit(reader);
        MainGame main = new MainGame();
        main.mainScreen(this.split);
    }
}
