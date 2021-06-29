/**
 * Class World will be a singlton.  There will be one world that is created at the beginning
 * of the program that will have cycles that will have language, trainers, a wilderness, and
 * code-a-mon monsters.
 */

import java.util.ArrayList;
import java.util.Random;

public class World {

    private static final World world = new World();
    private final ArrayList<Trainer> trainers;
    private Cycle time;
    private Cycle day;
    private Cycle night;
    private final Random languageChooser;
    private final CodeAMonFactory factory;

    private World() {
        languageChooser = new Random();
        factory = new CodeAMonFactory();
        trainers = new ArrayList<>();
        trainers.add(new Trainer("Erich Gamma", factory));
        trainers.add(new Trainer("Richard Helm", factory));
        trainers.add(new Trainer("Ralph Johnson", factory));
        trainers.add(new Trainer("John Vlissides", factory));
        day = new Cycle(languageChooser.nextInt(Environment.Language.values().length),
                Cycle.Time.DAY);
        night = new Cycle(languageChooser.nextInt(Environment.Language.values().length),
                Cycle.Time.NIGHT);
        time = day;
    }

    public static World getWorld() {
        return world;
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public CodeAMonFactory getFactory() {
        return factory;
    }

    public Cycle getCurrCycle() {
        return time;
    }

    private void nextDay() {
        for (Trainer trainer : trainers) {
            for (int i = 0; i < trainer.getNumMons(); i++) {
                trainer.restMons();
                trainer.evolveCodeAMons();
            }
        }
        day = new Cycle(languageChooser.nextInt(Environment.Language.values().length),
                Cycle.Time.DAY);
        night = new Cycle(languageChooser.nextInt(Environment.Language.values().length),
                Cycle.Time.NIGHT);
        time = day;
    }

    /**
     * Method runs the simulation for the specified number of days.
     * @param numberCycles the number of days to run the simulation
     */
    public void letWorldRun(int numberCycles) {
        for (int i = 0; i < numberCycles; i++) {
            System.out.println("~~~~~~~~DAY " + (i + 1)
                    + " of simulation~~~~~~~\nThe environment of"
                    + " this cycle is "
                    + time.getEnvironment().getLanguage().toString().toLowerCase()
                    + " and it is day time.");
            time.runCycle(world.getTrainers());
            time = night;
            System.out.println("\n~~~~It is now night time, and the environment of this cycle is "
                    + time.getEnvironment().getLanguage().toString().toLowerCase());
            night.runCycle(world.getTrainers());
            nextDay();
        }
        System.out.println("~~~~The simulation is over, calculating trainer stats~~~~");
        for (Trainer trainer : trainers) {
            trainer.printDetails();
        }
    }

}
