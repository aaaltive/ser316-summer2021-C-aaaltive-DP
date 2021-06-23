/**
 * Class creates an environment object that describes the weather and the buffed and
 * debuffed types, and is held by a mascotmon.
 *
 * @author Armand Altiveros, from code provided in SER316 shell
 * @version 1.0
 */

public class Environment {

    private Weather weather;
    private String buffedType;
    private String debuffedType;

    /**
     * Default constructor for an environment.
     */

    public Environment(){
        this(Weather.NEUTRAL);
    }

    /**
     * creates a weather type for a battle scenario and sets up the buffed and
     * debuffed types.
     * @param weather the weather type for the battle
     */

    public Environment(Weather weather) {
        this.weather = weather;
        switch (weather) {
            case SUNNY:
                this.buffedType = "Fire";
                this.debuffedType = "Water";
                break;
            case RAINY:
                this.buffedType = "Water";
                this.debuffedType = "Fire";
                break;
            case DROUGHT:
                this.buffedType = "Ground";
                this.debuffedType = "Normal";
                break;
            default:
                this.buffedType = "";
                this.debuffedType = "";
                break;
        }
    }

    /**
     * Getter method for buffedType.
     * @return a string representing the buffedType
     */

    public String getBuffedType() {
        return buffedType;
    }

    /**
     * Getter method for debuffedType.
     * @return a string representing the debuffedType
     */

    public String getDebuffedType() {
        return debuffedType;
    }

    /**
     * getter method for the weather variable.
     * @return this value for weather, an enum.
     */

    public Weather getWeather() {
        return weather;
    }

    public enum Weather {
        SUNNY, RAINY, DROUGHT, NEUTRAL
    }
}
