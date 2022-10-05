package cz.vse.adventure.logic;

/**
 * Class CommandBag - the bag command.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandBag implements ICommand {
    public static final String NAME = "bag";
    /**
     * The Bag.
     */
    Bag bag;

    /**
     * Instantiates a new Command bag.
     *
     * @param plan the plan
     */
    public CommandBag(GamePlan plan) {
        this.bag = plan.getBag();
    }

    @Override
    public String executeCommand(String... params) {
        return bag.getItems();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
