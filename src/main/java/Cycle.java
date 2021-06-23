public class Cycle {
    private Environment environment;
    private Time time;

    public Cycle(int weatherNumber, Time time) {
        environment = new Environment(Environment.Weather.values()[weatherNumber]);
        this.time = time;
    }

    public Environment getEnvironment(){
        return environment;
    }

    public void runCycle(Trainer[] trainers) {
        for (int i = 0; i < trainers.length; i++) {
            new Action(trainers[i], time);
        }
    }

    public enum Time {DAY, NIGHT};
}
