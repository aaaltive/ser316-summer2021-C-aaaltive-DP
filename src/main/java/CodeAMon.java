public abstract class CodeAMon implements Constants {

    protected int hp;
    protected int xp;
    protected int level;
    protected String name;
    protected double typeBuffer;
    protected double envBuffer;

    //todo: implement class
    public double getAttack() {
        return 0;
    }

    protected void setWeatherBonus(Environment.Weather weather) {

    }

    protected void setTypeBonus(CodeAMon opponent) {

    }

    public String getName() {
        return null;
    }

    public double getDefense() {
        return 0;
    }

    public void resetBonus() {

    }

    public void heal(){
        hp = hp + HEAL_HP;
        if (hp > MAX_HP) {
            hp = MAX_HP;
        }
    }

    public void setXP() {

    }

    public void takeHp(double percentChange){
        hp = (int) (hp - (hp * percentChange));
    }

    public void evolve() {

    }

    public int getHp() {
        return hp;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public double getTypeBuffer() {
        return typeBuffer;
    }

    public double getEnvBuffer() {
        return envBuffer;
    }
}
