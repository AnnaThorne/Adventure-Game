package cz.vse.adventure.logic;

/**
 * Class CommandTake - lets the player take items.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandTake implements ICommand {
    private static final String NAME = "take";
    private GamePlan plan;
    private Bag bag;

    /**
     * Instantiates a new Command take.
     *
     * @param plan the plan
     */
    public CommandTake(GamePlan plan) {
        this.plan = plan;
        this.bag = plan.getBag();
    }

    @Override
    public String executeCommand(String... params) {
        if (params.length == 0) {
            return "What am I supposed to take?";
        }
        String itemName = params[0];
        Room currentRoom = plan.getCurrentRoom();
        Item takenItem = currentRoom.getItem(itemName);
        if (takenItem != null) {
            if (takenItem.isMovable()) {
                if(bag.add(takenItem)){
                    currentRoom.removeItem(takenItem);
                    return "You've taken the item";
                }else{
                    return "Your bag is full";
                }
            } else {
                return itemName+ " is too heavy to move";
            }
        } else {
            return "Not found";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}

