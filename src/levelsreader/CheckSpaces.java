package levelsreader;

import java.util.HashMap;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class CheckSpaces {

    private final HashMap<String, Integer> spaceCreators;

    /**
     * check space.
     */
    public CheckSpaces() {
        this.spaceCreators = new HashMap<>();

    }

    /**
     * insert space.
     *
     * @param line line
     * @return hashmap
     */
    public HashMap<String, Integer> insertSpace(List<String> line) {
        String symbol = "";
        int width;
        for (int j = 0; j < line.size(); j++) {
            String[] arr = line.get(j).split(" ");
            String[] keyAndVal;
            for (String arr1 : arr) {
                if (arr1.contains("symbol")) {
                    keyAndVal = arr1.split(":");
                    symbol = keyAndVal[1];
                } else if (arr1.contains("width")) {
                    keyAndVal = arr1.split(":");
                    width = checkIfPos(keyAndVal[1]);
                    if (width != -1) {
                        this.spaceCreators.put(symbol, width);
                        break;
                    }
                }
            }
        }
        return this.spaceCreators;
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
}
