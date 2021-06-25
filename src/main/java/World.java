/**
 * Class World will be a singlton.  There will be one world that is created at the beginning of the program that will
 * have cycles that will have language, trainers, a wilderness, and code-a-mon monsters.
 */

import java.util.ArrayList;
import java.util.Random;

public class World {

    private ArrayList<Trainer> trainers;
    private Cycle time;
    private Cycle day;
    private Cycle night;
    private Random languageChooser;
    private static World world = new World();
    private CodeAMonFactory factory;

    private World() {
        languageChooser = new Random();
        factory = new CodeAMonFactory();
        trainers = new ArrayList<Trainer>();
        //TODO: add 1 codeAMon to each trainer
        trainers.add(new Trainer("Erich Gamma", factory));
        trainers.add(new Trainer("Richard Helm", factory));
        trainers.add(new Trainer("Ralph Johnson", factory));
        trainers.add(new Trainer("John Vlissides", factory));
        day = new Cycle(languageChooser.nextInt(Environment.Language.values().length), Cycle.Time.DAY);
        night = new Cycle(languageChooser.nextInt(Environment.Language.values().length), Cycle.Time.NIGHT);
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

    public Cycle getCycle() {
        return time;
    }

    private void nextDay(){
        day = new Cycle(languageChooser.nextInt(Environment.Language.values().length), Cycle.Time.DAY);
        night = new Cycle(languageChooser.nextInt(Environment.Language.values().length), Cycle.Time.NIGHT);
        //todo: add the healing of codeamon that did not battle during night.
    }

    public void letWorldRun(int numberCycles){
        for (int i = 0; i < numberCycles; i++) {
            day.runCycle(world.getTrainers());
            night.runCycle(world.getTrainers());
            nextDay();
        }
    }
}
