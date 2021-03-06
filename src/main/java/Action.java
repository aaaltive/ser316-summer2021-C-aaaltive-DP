import java.util.concurrent.ThreadLocalRandom;

public class Action implements Constants {

    /**
     * This method is a mediator between the cycle, and the trainers and CodeAMon.
     * @param trainer the trainer that is performing the action
     * @param time day or night
     */

    public Action(Trainer trainer, Cycle.Time time) {
        int actionChoice = ThreadLocalRandom.current().nextInt(0, 2);
        if (time.toString().compareTo("DAY") == 0) {
            switch (actionChoice) {
                case ACTION_BUY:
                    int itemChooser = ThreadLocalRandom.current().nextInt(1, NUM_ITEMS_IN_GAME);
                    Item.ItemTypes purchase = Item.ItemTypes.values()[itemChooser];
                    System.out.println(trainer.getName() + " wants to buy a " + purchase + ".");
                    if (trainer.getCredits() > ITEM_COST && trainer.buyItem(purchase)) {
                        System.out.println(trainer.getName() + " purchased a " + purchase + ".");
                    } else {
                        System.out.println(trainer.getName() + " did not have enough credits, or "
                                + "already had a full inventory");
                    }
                    break;
                case ACTION_BATTLE:
                    System.out.println(trainer.getName() + " wants to try to catch a CodeAMon");
                    Trainer wild = new Trainer();
                    CodeAMon attacker = trainer.callCodeAMon();
                    CodeAMon wildCodeAMon = World.getWorld().getFactory().getWildCodeAMon();
                    Battle battle = new Battle(trainer, attacker, wild, wildCodeAMon);
                    System.out.println(trainer.getName() + " found a " + wildCodeAMon.getType()
                            .toString().toLowerCase()
                            + " and will attempt to catch it using a "
                            + attacker.getType().toString().toLowerCase()
                            + ".");
                    CodeAMon winner = null;
                    while (winner == null) {
                        winner = battle.fightRound(attacker, wildCodeAMon);
                    }
                    if (winner.equals(attacker)) {
                        wildCodeAMon.heal();
                        trainer.catchMon(wildCodeAMon);
                        attacker.awardXp(wildCodeAMon.getLevel());
                        System.out.println(trainer.getName() + " has caught the wild "
                                + wildCodeAMon.getType()
                                .toString().toLowerCase() + ".");
                    } else {
                        System.out.println(trainer.getName() + " failed to catch the wild "
                                + wildCodeAMon.getType().toString().toLowerCase()
                                + ".");
                    }
                    break;
                default:
                    System.out.println("Something went wrong, the trainer did nothing all day");
            }
        } else {
            if (actionChoice == ACTION_BATTLE) {
                CodeAMon attacker = trainer.callCodeAMon();
                if (attacker.getHp() < MIN_HP_ATTACK) {
                    System.out.println(trainer.getName() + " wants to let to let his CodAMon rest "
                            + "for the night.");
                    return;
                }
                System.out.println(trainer.getName() + " wants to battle with another trainer "
                        + "tonight!");
                int randomOppChooser = ThreadLocalRandom.current().nextInt(0, World.getWorld()
                        .getTrainers().size());
                Trainer trainerOpp = World.getWorld().getTrainers().get(randomOppChooser);
                CodeAMon opponent = trainerOpp.callCodeAMon();
                int count = 0;
                while (trainerOpp.equals(trainer) || opponent.getHp() < MIN_HP_ATTACK) {
                    count++;
                    if (count == World.getWorld().getTrainers().size()) {
                        System.out.println(trainer.getName() + " could not find any opponents with "
                                + "CodeAMon healthy enough to fight.");
                        break;
                    }
                    trainerOpp = World.getWorld().getTrainers().get((randomOppChooser + count)
                            % World.getWorld().getTrainers().size());
                    opponent = trainerOpp.callCodeAMon();
                }
                System.out.println(trainer.getName() + "'s opponent will be: " + trainerOpp
                        .getName() + ".");
                System.out.println(trainer.getName() + " sends out " + attacker.getType()
                        .toString().toLowerCase()
                        + " and " + trainerOpp.getName() + " sends out " + opponent.getType()
                        .toString().toLowerCase()
                        + ".");
                CodeAMon winner = null;
                Battle battle = new Battle(trainer, attacker, trainerOpp, opponent);
                while (winner == null) {
                    winner = battle.fightRound(attacker, opponent);
                }
                if (winner.equals(attacker)) {
                    System.out.println(trainer.getName() + " wins the battle with a " + attacker
                            .getType().toString().toLowerCase() + ". " + trainer.getName()
                            + " wins 2 credits!");
                    trainer.awardCredits();
                    trainer.incrementWins();
                    trainerOpp.incrementLosses();
                } else {
                    System.out.println(trainerOpp.getName() + " wins the battle with a"
                            + opponent.getType()
                            .toString().toLowerCase() + ". " + trainerOpp.getName()
                            + " wins 2 credits!");
                    trainerOpp.awardCredits();
                    trainerOpp.incrementWins();
                    trainer.incrementLosses();
                }
                return;
            }
        }
        System.out.println(trainer.getName() + " wants to let his CodeAMon rest for the night");
    }

}
