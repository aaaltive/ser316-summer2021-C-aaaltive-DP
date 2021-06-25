import java.util.concurrent.ThreadLocalRandom;

public abstract class CodeAMon implements Constants {

    protected int hp;
    protected int xp;
    protected int defaultDamage;
    protected double defaultDefense;
    protected int level;
    protected Types type;
    protected Types debuffType;
    protected Types buffType;
    protected double typeBuffer;
    protected double envBuffer;
    protected boolean didBattle;

    public CodeAMon(){
        hp = MAX_HP;
        xp = 0;
        level = 0;
        didBattle = false;
    }

    public double getAttack(){
        int attackNumber = ThreadLocalRandom.current().nextInt(0, 4);
        switch (attackNumber) {
            case ATTACK_ID_CONSTRUCT:
                return (defaultDamage + (level * LEVEL_BONUS)) * ATTACK_BONUS * typeBuffer * envBuffer;
            default:
                return (defaultDamage  + (level * LEVEL_BONUS)) * typeBuffer * envBuffer;
        }
    }

    public double getDefense(){
        return (defaultDefense + (level * LEVEL_BONUS)) * typeBuffer * envBuffer;
    }

    protected void setLanguageBonus(Environment.Language language){
        if(type.toString().compareTo(new Environment(language).getBuffedType().toString()) == 0){
            envBuffer = BONUS;
        } else if(type.toString().compareTo(new Environment(language).getDebuffedType().toString()) == 0){
            envBuffer = BONUS;
        } else {
            envBuffer = 1;
        }
    }

    protected void setTypeBonus(CodeAMon opponent) {
        if(opponent.getType().equals(debuffType)) {
            typeBuffer = DEBUFF;
        } else if(opponent.getType().equals(buffType)) {
            typeBuffer = BONUS;
        } else {
            typeBuffer = 1;
        }
    }

    public Types getType() {
        return type;
    }

    private void resetBonus() {
        typeBuffer = 0.0;
        envBuffer = 0.0;
    }

    public void heal(){
        if (!didBattle) {
            hp = hp + (int)(HEAL_HP + (level * LEVEL_BONUS));
            if (hp > (int)(HEAL_HP + (level * LEVEL_BONUS))) {
                hp = (int)(HEAL_HP + (level * LEVEL_BONUS));
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
        hp = (int) (hp - percentChange);
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

    public enum Attack {WRAP, CONSTRUCT, ENCAPSULATE, RESTRICT}
    //restrict-singleton
    // wrap-adapter
    //construct-builder
    //encapsulate-Command
}
