public class Trainer implements Constants {

    private CodeAMon[] codeAMons;
    private int numMons;
    private int credits;
    private Item[] inventory;
    private int wins;
    private int losses;
    private final String name;

    public Trainer() {
        name = "wild";
    }

    /**
     * Constructor for a Trainer.
     * @param name the name of the trainer.
     * @param factory the CodeAMonFactory that was initiated in
     *                this world.
     */

    public Trainer(String name, CodeAMonFactory factory) {
        this.name = name;
        codeAMons = new CodeAMon[6];
        codeAMons[0] = factory.getCodeAMon();
        numMons = 1;
        credits = STARTING_CREDITS;
        inventory = new Item[MAX_INVENTORY_SIZE];
        for (int i = 0; i < MAX_INVENTORY_SIZE; i++) {
            inventory[i] = new Item(Item.ItemTypes.EMPTY);
        }
        wins = 0;
        losses = 0;
    }

    /**
     * Adds a item to the inventory of a trainer.
     * @param itemType the type of Item being added
     * @return if the purchase was successfull, returns true
     */

    public boolean buyItem(Item.ItemTypes itemType) {
        credits -= ITEM_COST;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].getType().toString().compareTo(Item.ItemTypes.EMPTY.toString()) == 0) {
                inventory[i] = new Item(itemType);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the CodeAMon with the most health and selects it for battle.
     * @return CodeAMon that will do battle.
     */
    
    public CodeAMon callCodeAMon() {
        CodeAMon chosenMon = codeAMons[0];
        for (int i = 1; i < numMons; i++) {
            if (codeAMons[i].getHp() > chosenMon.getHp()) {
                chosenMon = codeAMons[i];
            }
        }
        return chosenMon;
    }

    /**
     * Method calls heal on all the monsters that did not
     * do battle at night.
     */

    public void restMons() {
        for (int i = 0; i < numMons; i++) {
            codeAMons[i].endDay();
        }
    }

    /**
     * Method cycles through all CodeAMon and runs evolve method.
     */
    public void evolveCodeAMons() {
        for (int i = 0; i < numMons; i++) {
            codeAMons[i].evolve();
        }
    }

    public int getCredits() {
        return credits;
    }

    /**
     * method to check if a Trainer has a attack booster and use it.
     * @return returns true if a attack booster exists in inventory
     */

    public boolean useAttackBooser() {
        for (Item item : inventory) {
            if (item.toString().compareTo("ATTACK_BOOSTER") == 0) {
                item = new Item(Item.ItemTypes.EMPTY);
                return true;
            }
        }
        return false;
    }

    /**
     * method to check if a Trainer has a healing potion and use it.
     * @return returns true if a healing potion exists in inventory
     */
    public boolean useHealingPotion() {
        for (Item item : inventory) {
            if (item.toString().compareTo("HEALING_POTION") == 0) {
                item = new Item(Item.ItemTypes.EMPTY);
                return true;
            }
        }
        return false;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getName() {
        return name;
    }

    public void catchMon(CodeAMon mon) {
        codeAMons[numMons] = mon;
        numMons++;
    }

    public void awardCredits() {
        this.credits += BATTLE_REWARD;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
    }

    public int getNumMons() {
        return numMons;
    }

    /**
     * Method prints out the details of the CodeAMon onto the terminal.
     */

    public void printDetails() {
        System.out.println("~~~~****~~~~\n" + name + " has " + numMons + " Code-A-Mons:");
        for (int i = 0; i < numMons; i++) {
            codeAMons[i].printDetails();
        }
        System.out.println("Wins: " + wins + "\nLosses: "
                + losses + "\nCredits: " + credits);
    }

}
