package cz.vse.adventure.logic;
public class CommandExit implements ICommand {

    private static final String NAME = "exit";

    private Game game;
    public CommandExit(Game game) {
        this.game = game;
    }
    @Override
    public String executeCommand(String... params) {
        if (params.length > 0) {
            return "Exit what? There shouldn't be another param.";
        }
        else {
            game.setGameOver(true);
            return "Game exited";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
