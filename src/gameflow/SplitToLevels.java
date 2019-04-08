package gameflow;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class SplitToLevels {

    /**
     * stage info maker.
     *
     * @param reader reader
     * @return stage info
     */
    public StageInfo readAndSplit(Reader reader) {
        StageInfo stages = new StageInfo();
        String[] symbolAndName;
        String symbolAndLevel;
        LevelSpecificationReader levelReader = new LevelSpecificationReader();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String temp;
            List<String> oddLine = new ArrayList<>();
            List<String> evenLine = new ArrayList<>();
            while ((temp = is.readLine()) != null) {
                oddLine.add(temp);
                evenLine.add(is.readLine());
            }
            for (int i = 0; i < oddLine.size(); i++) {
                symbolAndName = oddLine.get(i).split(":");
                symbolAndLevel = evenLine.get(i);
                stages.addStage(symbolAndName[0], symbolAndName[1], levelReader.fromReader(symbolAndLevel));
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

        return stages;
    }
}
