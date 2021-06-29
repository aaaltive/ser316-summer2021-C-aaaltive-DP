public class CodeAMonFactory {

    private int monsBalancer;

    public CodeAMonFactory() {
        monsBalancer = 0;
    }

    /**
     * Getter method for a new wilde CodeAMon.
     * @return a new wilde CodeAMon
     */

    public CodeAMon getWildCodeAMon() {
        CodeAMon wildMon = getCodeAMon(CodeAMon.Types.values()[monsBalancer++ % 4]);
        wildMon.takeHp(Constants.WILD_MON_HEALTH_DEBUFF);
        return wildMon;
    }

    public CodeAMon getCodeAMon() {
        return getCodeAMon(CodeAMon.Types.values()[monsBalancer++ % 4]);
    }

    /**
     * Public method for creating a CodeAMon of a certain type.
     * @param type the CodeAMon type that is being asked for
     * @return a new CodeAMon
     */
    
    public CodeAMon getCodeAMon(CodeAMon.Types type) {
        switch (type) {
            case ADAPTOR:
                return new AdapterMon();
            case BUILDER:
                return new BuilderMon();
            case COMMAND:
                return new CommandMon();
            case SINGLTON:
                return new SingletonMon();
            default:
                return getWildCodeAMon();
        }
    }

}
