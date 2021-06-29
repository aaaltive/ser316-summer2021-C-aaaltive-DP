public class SingletonMon extends CodeAMon {

    /**
     * Constructor for a SingletonMon CodeAMon.
     */

    public SingletonMon() {
        super();
        defaultDamage = 50;
        defaultDefense = .10;
        type = Types.SINGLTON;
        debuffType = Types.BUILDER;
        buffType = Types.COMMAND;
    }

}
