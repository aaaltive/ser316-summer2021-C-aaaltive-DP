public class Item {

    private final ItemTypes type;

    /**
     * Costructor of an item.
     * @param type the type of item to construct
     */
    public Item(ItemTypes type) {
        switch (type) {
            case EMPTY:
                this.type = ItemTypes.EMPTY;
                break;
            case HEALING_POTION:
                this.type = ItemTypes.HEALING_POTION;
                break;
            case ATTACK_BOOSTER:
                this.type = ItemTypes.ATTACK_BOOSTER;
                break;
            default:
                this.type = ItemTypes.EMPTY;
        }
    }

    public ItemTypes getType() {
        return type;
    }

    public enum ItemTypes { EMPTY, HEALING_POTION, ATTACK_BOOSTER }

}
