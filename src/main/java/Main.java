/**
 * This is the Main class, the starting point for running the program.
 *
 * @author Armand Altiveros
 * @version 1.0
 */

public class Main {

    /**
     * The starting point for the program.
     *
     * @param args arguments, probably none.
     */

    public static void main(String[] args) {
        System.out.println("Spinning up world.");
        World.getWorld().letWorldRun(8);

    }
}
