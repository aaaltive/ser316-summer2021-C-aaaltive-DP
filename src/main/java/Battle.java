public class Battle implements Constants{

    private CodeAMon attacker;
    private CodeAMon defender;
    private Trainer attTrainer;
    private Trainer defTrainer;

    public Battle (Trainer attTrainer, CodeAMon attacker, Trainer defTrainer, CodeAMon defender) {
        this.attTrainer = attTrainer;
        this.defTrainer = defTrainer;
        this.attacker = attacker;
        this.defender = defender;
        attacker.setTypeBonus(defender);
        defender.setTypeBonus(attacker);
        attacker.setLanguageBonus(World.getWorld().getCycle().getEnvironment().getLanguage());
        defender.setLanguageBonus(World.getWorld().getCycle().getEnvironment().getLanguage());
    }

    public CodeAMon fightRound(CodeAMon attacker, CodeAMon defender){
        double damage = attacker.getAttack() * defender.getDefense();
        defender.takeHp(damage);
        System.out.println(defender.getType().toString().toLowerCase() + " took " + damage + " damage.");
        if(defender.getHp() <= K_O) {
            System.out.println(defender.getType().toString().toLowerCase() + " has been knocked out! " +
                    attacker.getType().toString().toLowerCase() + " has gained " + defender.getLevel() +
                    " XP!");
            endBattle();
            attacker.awardXP(defender.getLevel());
            return attacker;
        } else if(defender.getHp() < HEALING_POTION_THRESHOLD && !(defTrainer.getName().compareTo("wild") == 0)) {
            for (Item item: defTrainer.getInventory()) {
                if(item.toString().compareTo("HEALING_POTION") == 0) {
                    item = new Item(Item.ItemTypes.EMPTY);
                    defender.heal();
                    System.out.println(defender.getType().toString().toLowerCase() + " used a healing potion.");
                }
            }
        }
        damage = defender.getAttack() * attacker.getDefense();
        attacker.takeHp(damage);
        System.out.println(attacker.getType().toString().toLowerCase() + " took " + damage + " damage.");
        if(attacker.getHp() <= K_O) {
            System.out.println(attacker.getType().toString().toLowerCase() + " has been knocked out! " +
                    defender.getType().toString().toLowerCase() + " has gained " + attacker.getLevel() +
                    " XP!");
            endBattle();
            defender.awardXP(attacker.getLevel());
            return defender;
        } else {
            return null;
        }
    }

    private void endBattle(){
        attacker.resetBonus();
        defender.resetBonus();
        if(World.getWorld().isDay()) {
            attacker.markDidBattle();
            defender.markDidBattle();
        }
    }

}
