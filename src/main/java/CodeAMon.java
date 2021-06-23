public interface CodeAMon {

    Attack attack();
    void setWeatherBonus(Environment.Weather weather);
    void setTypeBonus(CodeAMon opponent);
    String getName();
    double getDefense();
    void heal();
    void resetBonus();
    void setXP();
    void evolve();

}
