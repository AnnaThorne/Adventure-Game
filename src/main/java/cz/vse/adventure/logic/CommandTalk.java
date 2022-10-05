package cz.vse.adventure.logic;

/**
 * Class CommandTalk - lets you talk to characters.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandTalk implements ICommand {
    private static final String NAME = "talk";
    private GamePlan plan;

    /**
     * Instantiates a new Command talk.
     *
     * @param plan the plan
     */
    public CommandTalk(GamePlan plan) {
        this.plan = plan;
    }

    @Override
    public String executeCommand(String... params) {
        if (params.length == 0) {
            return "Talk to who?";
        }
        Character c = plan.getCurrentRoom().getCharacter(params[0]);
        if (c != null) {
            StringBuilder s = new StringBuilder(c.getName() + ": ");
            if(plan.getBag().getItem(c.getUnlockItem()) != null){
                c.unlockDialogue();
            }
            if (c.isLocked()) {
                s.append(c.getGreetingsText());
            } else {
                s.append(c.getInfoText());
            }
            return s.toString();
        } else {
            return "That character doesn't exist.";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
