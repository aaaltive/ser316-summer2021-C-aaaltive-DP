import java.util.concurrent.ThreadLocalRandom;

public class AdapterMon extends CodeAMon{

    public AdapterMon(){
        super();
        defaultDamage = 30;
        defaultDefense = .40;
        type = Types.ADAPTOR;
        debuffType = Types.COMMAND;
        buffType = Types.BUILDER;
    }

}
