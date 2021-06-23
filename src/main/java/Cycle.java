public class Cycle {
    private Environment environment;

    Cycle(int weatherNumber){
        environment = new Environment(Environment.Weather.values()[weatherNumber]);
    }

    public Environment getEnvironment(){
        return environment;
    }
}
