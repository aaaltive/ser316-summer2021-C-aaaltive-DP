public class Cycle {
    private Environment environment;

    Cycle(int weatherNumber){
        environment = new Environment(Environment.Weather.values()[weatherNumber]);
    }

    public Environment getEnvironment(){
        return environment;
    }

    public void runCycle(Trainer[] trainers) {
        for (int i = 0; i < trainers.length; i++) {
            new Action(trainers[i]);
        }
    }
}
