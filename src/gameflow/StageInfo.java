package gameflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import levels.LevelInformation;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class StageInfo {

    private final List<String> stageName;
    private final List<String> stageSymbol;
    private final HashMap<String, List<LevelInformation>> stages;

    /**
     * stage info.
     */
    public StageInfo() {
        this.stages = new HashMap<>();
        this.stageName = new ArrayList<>();
        this.stageSymbol = new ArrayList<>();
    }

    /**
     * get tag name.
     *
     * @return tag name.
     */
    public List<String> getStageName() {
        return this.stageName;
    }

    /**
     * set symbol.
     *
     * @return list of symbol
     */
    public List<String> getStageSymbol() {
        return this.stageSymbol;
    }

    /**
     * level information.
     *
     * @return hash map
     */
    public HashMap<String, List<LevelInformation>> getStages() {
        return this.stages;
    }

    /**
     * add stage.
     *
     * @param symbol symbol
     * @param name name
     * @param stage stage
     */
    public void addStage(String symbol, String name, List<LevelInformation> stage) {
        this.stageName.add(name);
        this.stageSymbol.add(symbol);
        this.stages.put(symbol, stage);
    }

}
