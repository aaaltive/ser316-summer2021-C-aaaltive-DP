import java.util.ArrayList;

public class Cycle {
    private final Environment environment;
    private final Time time;

    public Cycle(int languageNumber, Time time) {
        environment = new Environment(Environment.Language.values()[languageNumber]);
        this.time = time;
    }

    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Method performs an action for each of the trainers in the list.
     * @param trainers a list of Trainer objects
     */
    public void runCycle(ArrayList<Trainer> trainers) {
        for (int i = 0; i < trainers.size() - 1; i++) {
            new Action(trainers.get(i), time);
        }
    }

    public Time getTime() {
        return time;
    }

    public enum Time { DAY, NIGHT }
}
