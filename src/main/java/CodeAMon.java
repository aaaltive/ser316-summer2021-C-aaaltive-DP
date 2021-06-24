public abstract class CodeAMon implements Constants {

    protected int hp;
    protected int xp;
    protected int level;
    protected Types type;
    protected double typeBuffer;
    protected double envBuffer;
    private boolean didBattle;

    public abstract double getAttack();

    protected abstract void setWeatherBonus(Environment.Weather weather);

    protected abstract void setTypeBonus(CodeAMon opponent);

    public abstract double getDefense();

    public Types getType() {
        return type;
    }

    private void resetBonus() {
        typeBuffer = 0.0;
        envBuffer = 0.0;
    }

    public void heal(){
        if (!didBattle) {
            hp = hp + HEAL_HP;
            if (hp > MAX_HP) {
                hp = MAX_HP;
            }
        }
    }

    public void markDidBattle(){
        didBattle = true;
    }

    public void awardXP(int opponentLevel) {
        level += opponentLevel;
    }

    public void takeHp(double percentChange){
        hp = (int) (hp - (hp * percentChange));
    }

    public void evolve() {
        if(xp == level * 2){
            level++;
            xp = 0;
        }
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

    public void endDay(){
        evolve();
        heal();
        didBattle = false;
    }

    public enum Types {ADAPTOR, BUILDER, COMMAND, SINGLTON}
}
