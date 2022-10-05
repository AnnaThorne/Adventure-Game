package cz.vse.adventure.logic;

/**
 * Class CommandShowRoom - prints out information about the current room.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandShowRoom implements ICommand{
    private static final String NAZEV = "roomInfo";
    private GamePlan plan;

    /**
     * Instantiates a new Command show room.
     *
     * @param plan the plan
     */
    public CommandShowRoom(GamePlan plan) {
        this.plan = plan;
    }

    @Override
    public String executeCommand(String... params) {
        return plan.getCurrentRoom().longDesc();
    }

    @Override
    public String getName() {
        return NAZEV;
    }
}
