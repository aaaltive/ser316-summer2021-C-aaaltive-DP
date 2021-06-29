/**
 * Class creates an environment object that describes the language and the buffed and
 * debuffed types, and is held by a mascotmon.
 *
 * @author Armand Altiveros, from code provided in SER316 shell
 * @version 1.0
 */

public class Environment {

    private final Language language;
    private final CodeAMon.Types buffedType;
    private final CodeAMon.Types debuffedType;

    /**
     * Default constructor for an environment.
     */

    public Environment() {
        this(Language.LOLCODE);
    }

    /**
     * creates a language type for a battle scenario and sets up the buffed and
     * debuffed types.
     *
     * @param language the language type for the battle
     */

    public Environment(Language language) {
        this.language = language;
        switch (language) {
            case CPLUS:
                this.buffedType = CodeAMon.Types.SINGLTON;
                this.debuffedType = CodeAMon.Types.BUILDER;
                break;
            case JAVA:
                this.buffedType = CodeAMon.Types.BUILDER;
                this.debuffedType = CodeAMon.Types.COMMAND;
                break;
            case PYTHON:
                this.buffedType = CodeAMon.Types.ADAPTOR;
                this.debuffedType = CodeAMon.Types.SINGLTON;
                break;
            default:
                this.buffedType = CodeAMon.Types.COMMAND;
                this.debuffedType = CodeAMon.Types.ADAPTOR;
                break;
        }
    }

    /**
     * Getter method for buffedType.
     *
     * @return a string representing the buffedType
     */

    public CodeAMon.Types getBuffedType() {
        return buffedType;
    }

    /**
     * Getter method for debuffedType.
     *
     * @return a string representing the debuffedType
     */

    public CodeAMon.Types getDebuffedType() {
        return debuffedType;
    }

    /**
     * getter method for the language variable.
     *
     * @return this value for language, an enum.
     */

    public Language getLanguage() {
        return language;
    }

    public enum Language {
        CPLUS, JAVA, PYTHON, LOLCODE
    }
}
