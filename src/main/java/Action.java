import java.util.Random;

public class Action implements Constants{

    public Action(Trainer trainer){
        Random actionChooser = new Random();
        int actionChoice = actionChooser.nextInt(3);
        switch (actionChoice) {
            case ACTION_HEAL:
        }
    }

}
