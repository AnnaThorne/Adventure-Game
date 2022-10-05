package cz.vse.adventure.logic;
public class CommandGo implements ICommand {
    public static final String NAME = "go";
    private GamePlan plan;
    public CommandGo(GamePlan plan) {
        this.plan = plan;
    }

    @Override
    public String executeCommand(String... params) {
        if (params.length == 0) {
            return "Where am I supposed to go?";
        }

        String direction = params[0];

        Room adjacentRoom = plan.getCurrentRoom().getAdjacentRoom(direction);

        if (adjacentRoom == null) {
            return "You can't go there!";
        } else {
            if (plan.getCurrentRoom().getEnemy() != null) {
                return "There's a " + plan.getCurrentRoom().getEnemy().getName() + " blocking your path! \nYou'll have to attack them first.";
            } else {
                if (adjacentRoom == plan.getVictoryRoom()) {
                    if (plan.getBag().hasVictoryKey()) {
                        plan.setCurrentRoom(adjacentRoom);
                        return adjacentRoom.longDesc();
                    } else {
                        return "This passage is locked, you need a gate key to pass.";
                    }
                } else {
                    plan.setCurrentRoom(adjacentRoom);
                    return adjacentRoom.longDesc();
                }
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

}
