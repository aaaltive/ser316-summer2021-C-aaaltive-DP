public class Trainer implements Constants{

    private CodeAMon[] codeAMons;
    private int numMons;
    private int credits;
    private Item[] inventory;
    private int wins;
    private int losses;
    private String Name;

    public Trainer(String name){
        codeAMons = new CodeAMon[6];
        // TODO: 6/23/21 add a CodeAMon to array
        numMons = 1;
        credits = STARTING_CREDITS;
        inventory = new Item[MAX_INVENTORY_SIZE];
        for (int i = 0; i < MAX_INVENTORY_SIZE; i++) {
            inventory[i] = new Item(Item.ItemTypes.EMPTY);
        }
        wins = 0;
        losses = 0;
    }

    public void buyItem(int itemCost, Item.ItemTypes itemType){
        credits -= itemCost;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].getType().toString().compareTo(Item.ItemTypes.EMPTY.toString()) == 0){
                inventory[i] = new Item(itemType);
                return;
            }
        }
    }

    public CodeAMon callCodeAMon() {
        CodeAMon chosenMon = codeAMons[0];
        for (int i = 1; i < codeAMons.length; i++) {
            if (codeAMons[i].getHp() > chosenMon.getHp()) {
                chosenMon = codeAMons[i];
            }
        }
        return chosenMon;
    }

    public void restMons() {
        for (int i = 0; i < codeAMons.length; i++) {
            codeAMons[i].heal();
        }
    }

    public CodeAMon[] getCodeAMons() {
        return codeAMons;
    }

    public int getCredits() {
        return credits;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getName() {
        return Name;
    }

    public void catchMon(CodeAMon mon) {
        codeAMons[numMons] = mon;
    }

    public void payReward() {
        credits += BATTLE_REWARD;
    }
}
