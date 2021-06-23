public class Trainer {

    private CodeAMon[] codeAMons;
    private int credits;
    private Item[] inventory;
    private int wins;
    private int losses;

    public Trainer(){
        codeAMons = new CodeAMon[6];
        credits = Constants.STARTING_CREDITS;
        inventory = new Item[Constants.MAX_INVENTORY_SIZE];
        for (int i = 0; i < Constants.MAX_INVENTORY_SIZE; i++) {
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
}