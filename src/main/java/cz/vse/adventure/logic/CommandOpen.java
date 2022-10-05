package cz.vse.adventure.logic;

/**
 * Class CommandOpen - the open command.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandOpen implements ICommand {
    private static final String NAME = "open";
    private GamePlan plan;
    private Bag bag;

    /**
     * Instantiates a new Command open.
     *
     * @param plan the plan
     */
    public CommandOpen(GamePlan plan) {
        this.plan = plan;
        this.bag = plan.getBag();
    }

    @Override
    public String executeCommand(String... params) {
        if (params.length == 0) {
            return "What am I supposed to open?";
        }
        String itemName = params[0];
        Room currentRoom = plan.getCurrentRoom();
        Item openedItem = currentRoom.getItem(itemName);
        if (openedItem != null) {
            if (openedItem instanceof Container) {
                Container openedContainer = (Container) openedItem;
                StringBuilder sb = new StringBuilder("Contained items:");
                for (Item i : openedContainer.getItems()) {
                    sb.append(" ").append(i.getName());
                    plan.getCurrentRoom().addItem(i);
                }
                openedContainer.empty();
                return sb.toString();
            } else {
                return "This can't be opened";
            }
        }
        return "Not found";
    }
    @Override
    public String getName () {
        return NAME;
    }
}
