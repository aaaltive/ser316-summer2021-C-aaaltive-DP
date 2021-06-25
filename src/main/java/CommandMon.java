import java.util.concurrent.ThreadLocalRandom;

public class CommandMon extends CodeAMon{

    public CommandMon() {
        super();
        defaultDamage = 40;
        defaultDefense = .20;
        type = Types.COMMAND;
        debuffType = Types.ADAPTOR;
        buffType = Types.SINGLTON;
    }

}
