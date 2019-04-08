package listiners;

import java.io.Serializable;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 05.
 */
@SuppressWarnings("serial")
public class ScoreInfo implements Serializable {

    private final String name;
    private final int score;

    /**
     * constructor.
     *
     * @param name name
     * @param score score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * get name.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * get score.
     *
     * @return score
     */
    public int getScore() {
        return this.score;
    }
}
