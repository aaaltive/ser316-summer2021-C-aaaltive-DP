public class Battle implements Constants {

    private final CodeAMon attacker;
    private final CodeAMon defender;
    private final Trainer attTrainer;
    private final Trainer defTrainer;

    /**
     * Constructor for a battle.
     * @param attTrainer the trainer of the CodeAmon attacking
     * @param attacker the CodeAMon attacking
     * @param defTrainer the trainer of the CodeAMon defending
     * @param defender the CodeAMon defending
     */

    public Battle(Trainer attTrainer, CodeAMon attacker, Trainer defTrainer, CodeAMon defender) {
        this.attTrainer = attTrainer;
        this.defTrainer = defTrainer;
        this.attacker = attacker;
        this.defender = defender;
        attacker.setTypeBonus(defender);
        defender.setTypeBonus(attacker);
        attacker.setLanguageBonus(World.getWorld().getCurrCycle().getEnvironment().getLanguage());
        defender.setLanguageBonus(World.getWorld().getCurrCycle().getEnvironment().getLanguage());
    }

    /**
     * Method gets an attack and a defense from each monster, as well as allowing the use of
     * potions.
     * @param attacker CodeAMon that is attacking
     * @param defender CodeAMon that is defending
     * @return the CodeAMon that knocked out the loser, or null if no K.O.
     */

    public CodeAMon fightRound(CodeAMon attacker, CodeAMon defender) {
        for (Item item : attTrainer.getInventory()) {
            if (item.toString().compareTo("ATTACK_BOOSTER") == 0) {
                item = new Item(Item.ItemTypes.EMPTY);
                attacker.useAttackPotion();
                System.out.println(attacker.getType().toString().toLowerCase()
                        + " used an attack booster potion.");
            }
        }
        double damage = attacker.getAttack() * defender.getDefense();
        defender.takeHp(damage);
        System.out.println(defender.getType().toString().toLowerCase() + " took "
                + damage + " damage.");
        if (defender.getHp() <= K_O) {
            System.out.println(defender.getType().toString().toLowerCase()
                    + " has been knocked out! "
                    + attacker.getType().toString().toLowerCase() + " has gained "
                    + defender.getLevel()
                    + " XP!");
            endBattle();
            attacker.awardXp(defender.getLevel());
            return attacker;
        } else if (defender.getHp() < HEALING_POTION_THRESHOLD && !(defTrainer.getName()
                .compareTo("wild") == 0)) {
            for (Item item : defTrainer.getInventory()) {
                if (item.toString().compareTo("HEALING_POTION") == 0) {
                    item = new Item(Item.ItemTypes.EMPTY);
                    defender.heal();
                    System.out.println(defender.getType().toString().toLowerCase()
                            + " used a healing potion.");
                }
            }
        }
        damage = defender.getAttack() * attacker.getDefense();
        attacker.takeHp(damage);
        System.out.println(attacker.getType().toString().toLowerCase() + " took " + damage
                + " damage.");
        if (attacker.getHp() <= K_O) {
            System.out.println(attacker.getType().toString().toLowerCase()
                    + " has been knocked out! "
                    + defender.getType().toString().toLowerCase()
                    + " has gained " + attacker.getLevel()
                    + " XP!");
            endBattle();
            defender.awardXp(attacker.getLevel());
            return defender;
        } else {
            return null;
        }
    }

    private void endBattle() {
        attacker.resetBonus();
        defender.resetBonus();
        if (World.getWorld().getCurrCycle().getTime().toString().compareTo("NIGHT") == 0) {
            attacker.markDidBattle();
            defender.markDidBattle();
        }
    }

}
