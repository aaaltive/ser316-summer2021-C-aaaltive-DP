/**
 * Class World will be a singlton.  There will be one world that is created at the beginning of the program that will
 * have cycles that will have weather, trainers, a wilderness, and code-a-mon monsters.
 */

import java.util.Random;

public class World {

    private Trainer[] trainers;
    private Cycle time;
    private Cycle day;
    private Cycle night;
    private Random weatherChooser;
    private static World world = new World();

    private World() {
        weatherChooser = new Random();
        trainers = new Trainer[12];
        //TODO: add 1 codeAMon to each trainer
        trainers[0] = new Trainer("Erich Gamma");
        trainers[1] = new Trainer("Richard Helm");
        trainers[2] = new Trainer("Ralph Johnson");
        trainers[3] = new Trainer("John Vlissides");
        day = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length), Cycle.Time.DAY);
        night = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length), Cycle.Time.NIGHT);
        time = day;
    }

    public static World getWorld() {
        return world;
    }

    public Trainer[] getTrainers() {
        return trainers;
    }

    public Cycle getCycle() {
        return time;
    }

    private void nextDay(){
        day = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length), Cycle.Time.DAY);
        night = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length), Cycle.Time.NIGHT);
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
