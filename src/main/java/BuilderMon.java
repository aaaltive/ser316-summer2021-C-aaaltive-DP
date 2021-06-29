public class BuilderMon extends CodeAMon {

    /**
     * Costructs a BuilderMon CodeAMon.
     */
    public BuilderMon() {
        super();
        defaultDamage = 25;
        defaultDefense = .50;
        type = Types.BUILDER;
        debuffType = Types.SINGLTON;
        buffType = Types.ADAPTOR;
    }

}
