package animation;

import listiners.ScoreInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
@SuppressWarnings("serial")
public class HighScoresTable implements Serializable {

    private int size;
    private List<ScoreInfo> table;

    /**
     * Create an empty high-scores table with the specified size. The size means
     * that the table holds up to size top scores.
     *
     * @param size size
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.table = new ArrayList<>();

    }

    /**
     * Add a high-score.
     *
     * @param score score
     */
    public void add(ScoreInfo score) {
        this.table.add(score);

    }

    /**
     * Return table size.
     *
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest
     * scores come first.
     *
     * @return size size.
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }

    /**
     * return the rank of the current score: where will it be on the list if
     * added? Rank 1 means the score will be highest on the list. Rank `size`
     * means the score will be lowest. Rank > `size` means the score is too low
     * and will not be added to the list.
     *
     * @param score score.
     * @return rank.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.table.size(); i++) {
            if (score > this.table.get(i).getScore()) {
                return i;
            }
        }
        if (this.table.size() < this.size) {
            return this.table.size();
        }
        return -1;

    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.table.clear();

    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename file
     * @throws IOException exeption
     */
    public void load(File filename) throws IOException {
        HighScoresTable newTable = HighScoresTable.loadFromFile(filename);
        this.size = newTable.size;
        this.table = newTable.table;
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename file
     * @throws IOException exeption
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or
     * there is a problem with reading it, an empty table is returned.
     *
     * @param filename file
     * @return HighScoresTable.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable newTable;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            newTable = (HighScoresTable) objectInputStream.readObject();
            return newTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
        return null;
    }

    /**
     * print.
     */
    public void printTable() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.table.get(i).getName() + ":   " + this.table.get(i).getScore());
        }
    }
}
