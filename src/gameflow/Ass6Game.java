package gameflow;

/**
 * Name: Michael Reubinof. ID: 204176085. Exercise: 06.
 */
public class Ass6Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LevelsReader game = new LevelsReader();
        if (args.length > 0) {
            game.run(args[0]);
        } else {
            game.run("level_sets.txt");
        }

    }
}
