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
    protected boolean attackPotion;
    protected double typeBuffer;
    protected double envBuffer;
    protected boolean didBattle;

    /**
     * Constructor for a CodeAMon.
     */

    public CodeAMon() {
        hp = MAX_HP;
        xp = 0;
        level = 0;
        didBattle = false;
    }

    /**
     * Gets the attack and calculates the base damage value.
     * @return double representing the base damage value
     */

    public double getAttack() {
        int attackNumber = ThreadLocalRandom.current().nextInt(0, 4);
        if (attackNumber == ATTACK_ID_CONSTRUCT) {
            if (attackPotion) {
                return (defaultDamage + (level * LEVEL_BONUS)) * ATTACK_BONUS * typeBuffer
                        * envBuffer * ATTACK_BOOSTER;
            }
            return (defaultDamage + (level * LEVEL_BONUS)) * ATTACK_BONUS * typeBuffer * envBuffer;
        }
        if (attackPotion) {
            return (defaultDamage + (level * LEVEL_BONUS)) * typeBuffer * envBuffer
                    * ATTACK_BOOSTER;
        }
        return (defaultDamage + (level * LEVEL_BONUS)) * typeBuffer * envBuffer;
    }

    public double getDefense() {
        return (defaultDefense + (level * LEVEL_BONUS)) * typeBuffer * envBuffer;
    }

    protected void setLanguageBonus(Environment.Language language) {
        if (type.toString().compareTo(new Environment(language).getBuffedType().toString()) == 0) {
            envBuffer = BONUS;
        } else if (type.toString().compareTo(new Environment(language).getDebuffedType()
                .toString()) == 0) {
            envBuffer = BONUS;
        } else {
            envBuffer = 1;
        }
    }

    protected void setTypeBonus(CodeAMon opponent) {
        if (opponent.getType().equals(debuffType)) {
            typeBuffer = DEBUFF;
        } else if (opponent.getType().equals(buffType)) {
            typeBuffer = BONUS;
        } else {
            typeBuffer = 1;
        }
    }

    public Types getType() {
        return type;
    }

    /**
     * Method resets the bonuses for the CodeAMon after a battle.
     */

    public void resetBonus() {
        typeBuffer = 0.0;
        envBuffer = 0.0;
        attackPotion = false;
    }

    /**
     * Allows codeAMon to heal if they have not done battle that night.
     */

    public void heal() {
        hp = hp + (int) (HEAL_HP + (level * LEVEL_BONUS));
        if (hp > (int) (HEAL_HP + (level * LEVEL_BONUS))) {
            hp = (int) (HEAL_HP + (level * LEVEL_BONUS));
        }
    }

    public void markDidBattle() {
        didBattle = true;
    }

    public void awardXp(int opponentLevel) {
        level += (opponentLevel + 1);
    }

    public void takeHp(double percentChange) {
        hp = (int) (hp - percentChange);
    }

    /**
     * Method allows the CodeAMon to evolve is they have enough XP.
     */

    public void evolve() {
        if (xp > level && level < MAX_LEVEL) {
            level++;
            hp = (int) (hp * LEVEL_BONUS);
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

    public void evolveLevel() {
        level++;
    }

    /**
     * method perfoms the functions for the CodeAMon for the end of each day.
     */

    public void endDay() {
        evolve();
        if (!didBattle) {
            heal();
        }
        didBattle = false;
    }

    /**
     * method prints all the details for the CodeAMon.
     */

    public void printDetails() {
        System.out.println(
                "-" + type.toString().toLowerCase() + ": \n\t"
                        + "Level: " + this.level);
    }

    public void useAttackPotion() {
        attackPotion = true;
    }

    public enum Types { ADAPTOR, BUILDER, COMMAND, SINGLTON }

    public enum Attack { WRAP, CONSTRUCT, ENCAPSULATE, RESTRICT }
}
