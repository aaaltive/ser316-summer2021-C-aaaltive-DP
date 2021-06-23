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
        trainers[0] = new Trainer();
        trainers[1] = new Trainer();
        day = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length));
        night = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length));
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
        day = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length));
        night = new Cycle(weatherChooser.nextInt(Environment.Weather.values().length));
    }

    private void changeTime(){
        if (time.equals(day)) {
            time = night;
        } else {
            time = null;
            nextDay();
            time = day;
        }
    }

    public void letWorldRun(int numberCycles){
        //todo: implement this
    }
}