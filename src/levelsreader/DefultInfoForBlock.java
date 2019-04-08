package levelsreader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class DefultInfoForBlock {

    private HashMap<String, String> defultinfo;
    private final List<String> value;

    /**
     * defult info.
     */
    public DefultInfoForBlock() {
        this.defultinfo = new HashMap<>();
        this.value = new ArrayList<>();
    }

    /**
     * init defult info.
     */
    public void defultInitValue() {
        this.value.add("symbol");
        this.value.add("height");
        this.value.add("width");
        this.value.add("hit_points");
        this.value.add("fill");
        this.value.add("stroke");
        this.defultinfo = new HashMap<>();

    }

    /**
     * split the data.
     *
     * @param line line
     * @return hash map
     */
    public HashMap<String, String> splitDefultInfo(String line) {
        if (line == null) {
            return null;
        }
        String[] arr = line.split(" ");
        String[] keyAndVal;
        for (String arr1 : arr) {
            for (int i = 0; i < this.value.size(); i++) {
                if (arr1.contains(this.value.get(i))) {
                    keyAndVal = arr1.split(":");
                    if (!arr1.contains("fill")) {
                        this.defultinfo.put(keyAndVal[0], keyAndVal[1]);
                        break;
                    } else {
                        if (keyAndVal[0].equals("fill")) {
                            this.defultinfo.put("1", keyAndVal[1]);
                        } else {
                            this.defultinfo.put(keyAndVal[0].substring(keyAndVal[0].lastIndexOf("-") + 1),
                                    keyAndVal[1]);
                        }
                    }
                }
            }
        }
        return this.defultinfo;
    }

    /**
     * defult reset.
     */
    public void defultReset() {
        this.defultinfo = new HashMap<>();
    }
}
