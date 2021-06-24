public class CodeAMonFactory {

    int monsBalancer = 0;

    public CodeAMon getWildCodeAMon (){
        CodeAMon wildMon = getCodeAMon(CodeAMon.Types.values()[monsBalancer++]);
        wildMon.takeHp(Constants.WILD_MON_HEALTH_DEBUFF);
        return wildMon;
    }

    public CodeAMon getCodeAMon (CodeAMon.Types type){
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
