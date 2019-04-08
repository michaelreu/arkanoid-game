/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameflow;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import levels.LevelInformation;
import levelsreader.ReadLevelsFromFile;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class LevelSpecificationReader {

    private List<LevelInformation> levels;

    /**
     * LevelSpecificationReader.
     */
    public LevelSpecificationReader() {
        this.levels = new ArrayList<>();
    }

    /**
     *
     * @param fileName file.
     * @return level information list.
     */
    public List<LevelInformation> fromReader(String fileName) {
        Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName));
        ReadLevelsFromFile read = new ReadLevelsFromFile();
        this.levels = (read.loadAndRead(reader));
        return this.levels;
    }
}
