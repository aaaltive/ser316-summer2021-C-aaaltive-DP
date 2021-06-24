import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Action implements Constants{

    public Action(Trainer trainer, Cycle.Time time){
        Random randomChooser = new Random();
        int actionChoice = randomChooser.nextInt(2);
        if (time.toString().compareTo("DAY") == 0) {
            switch (actionChoice) {
                case ACTION_BUY:
                    int itemChooser = randomChooser.nextInt(3);
                    Item.ItemTypes purchase = Item.ItemTypes.values()[itemChooser];
                    System.out.println(trainer.getName() + " wants to buy a " + purchase.toString() + ".");
                    if (trainer.getCredits() > ITEM_COST && trainer.getInventory().length < MAX_INVENTORY_SIZE) {
                        System.out.println(trainer.getName() + " purchased a " + purchase.toString() + ".");
                    } else {
                        System.out.println(trainer.getName() + " did not have enough credits, or already had a full inventory");
                    }
                    break;
                case ACTION_BATTLE:
                    System.out.println(trainer.getName() + " wants to try to catch a CodeAMon");
                    CodeAMonFactory factory = new CodeAMonFactory();
                    Battle battle = new Battle();
                    CodeAMon attacker = trainer.callCodeAMon();
                    CodeAMon wildCodeAMon = factory.getWildCodeAMon();
                    System.out.println(trainer.getName() + " found a " + wildCodeAMon.getType().toString().toLowerCase()
                            + " and will attempt to catch it using a " + attacker.getType().toString().toLowerCase()
                            + ".");
                    CodeAMon winner = null;
                    while (winner == null){
                        winner = battle.fightRound(attacker, wildCodeAMon);
                    }
                    if (winner.equals(attacker)){
                        wildCodeAMon.heal();
                        trainer.catchMon(wildCodeAMon);
                        System.out.println(trainer.getName() + " has caught the wild " + wildCodeAMon.getType()
                                .toString().toLowerCase() + ".");
                    } else {
                        System.out.println(trainer.getName() + " failed to catch the wild " + wildCodeAMon.getType()
                                .toString().toLowerCase() + ".");
                    }
                    break;
                default:
                    System.out.println("Something went wrong, the trainer did nothing all day");
            }
        } else {
            switch (actionChoice) {
                case ACTION_BATTLE:
                    CodeAMon attacker = trainer.callCodeAMon();
                    if (attacker.getHp() < MIN_HP_ATTACK) {
                        actionChoice = ACTION_HEAL;
                        break;
                    }
                    System.out.println(trainer.getName() + " wants to battle with another trainer tonight!");
                    int randomOppChooser = randomChooser.nextInt(World.getWorld().getTrainers().length);
                    Trainer trainerOpp = World.getWorld().getTrainers()[randomOppChooser];
                    CodeAMon opponent = trainerOpp.callCodeAMon();
                    int count = 0;
                    while (trainerOpp.equals(trainer) || opponent.getHp() < MIN_HP_ATTACK) {
                        count++;
                        if (count == World.getWorld().getTrainers().length){
                            System.out.println(trainer.getName() + " could not find any opponents with CodeAMon able to fight.");
                            break;
                        }
                        trainerOpp = World.getWorld().getTrainers()[(randomOppChooser + count) % World.getWorld().getTrainers().length];
                        opponent = trainerOpp.callCodeAMon();
                    }
                    System.out.println(trainer.getName() + "'s opponent will be: " + trainerOpp.getName() + ".");
                    System.out.println(trainer.getName() + " sends out " + attacker.getType().toString().toLowerCase()
                            + " and " + trainerOpp.getName() + " sends out " + opponent.getType().toString().toLowerCase()
                            + ".");
                    CodeAMon winner = null;
                    Battle battle = new Battle();
                    while (winner == null){
                        winner = battle.fightRound(attacker, opponent);
                    }
                    if (winner.equals(attacker)){
                        System.out.println(trainer.getName() + " wins the battle with a" + attacker.getType()
                                .toString().toLowerCase() + ". " + trainer.getName() + " wins 2 credits!");
                    } else {
                        System.out.println(trainerOpp.getName() + " wins the battle with a" + opponent.getType()
                                .toString().toLowerCase() + ". " + trainer.getName() + " wins 2 credits!");
                    }
                    break;
                default:
                    System.out.println(trainer.getName() + " wants to let his CodeAMon rest for the night");
            }
        }
    }

}
