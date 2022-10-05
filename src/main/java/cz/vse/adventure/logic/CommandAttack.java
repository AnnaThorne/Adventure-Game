package cz.vse.adventure.logic;

/**
 * Class CommandAttack - the attack command.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class CommandAttack implements ICommand{
    private static final String NAME = "attack";
    private GamePlan plan;
    private Game game;

    /**
     * Instantiates a new Command attack.
     *
     * @param plan the plan
     * @param game the game
     */
    public CommandAttack(GamePlan plan, Game game) {
        this.plan = plan;
        this.game = game;
    }

    @Override
    public String executeCommand(String... params) {
        if (params.length == 0) {
            return "Attack who?";
        }
        Character c = plan.getCurrentRoom().getCharacter(params[0]);
        if (c != null) {
            boolean hasWeapon = false;
            if(!plan.getBag().getHeldItems().values().isEmpty()){
            for (Item i : plan.getBag().getHeldItems().values()){
                if(i.isWeapon()){
                    hasWeapon = true;
                    break;
                }
            }
            }
            if(hasWeapon){
                plan.getCurrentRoom().removeCharacter(c);
                if(c.isEnemy()){
                return "You've killed "+c.getName()+".";
                } else {
                    return "You've killed "+c.getName()+".\nBut they weren't an enemy...";
                }
            }else{
                game.setGameOver(true);
                return "You didn't have a weapon.\nSo you've been killed.";
            }
        } else {
            return "That character doesn't exist.";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
