package cz.vse.adventure.logic;

public class Game implements IGame {
    private CommandsList validCommands;
    private GamePlan gamePlan;
    private boolean gameOver = false;

    public Game() {
        gamePlan = new GamePlan();
        validCommands = new CommandsList();
        validCommands.addCommand(new CommandHelp(validCommands));
        validCommands.addCommand(new CommandGo(gamePlan));
        validCommands.addCommand(new CommandExit(this));
        validCommands.addCommand(new CommandTake(gamePlan));
        validCommands.addCommand(new CommandBag(gamePlan));
        validCommands.addCommand(new CommandTalk(gamePlan));
        validCommands.addCommand(new CommandAttack(gamePlan, this));
        validCommands.addCommand(new CommandOpen(gamePlan));
        validCommands.addCommand(new CommandShowRoom(gamePlan));
    }


    public String returnGreeting() {
        return "Greetings!\n" +
                "This is a tale of a brave bunny, trying to get to the mighty golden carrot.\n" +
                "Type in 'help' if you feel lost.\n" +
                "\n" +
                gamePlan.getCurrentRoom().longDesc();
    }

    public String returnEpilogue() {
        return "You did it. Enjoy the carrot.";
    }

    public boolean endGame() {
        return gameOver;
    }

    public String executeCommand(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (validCommands.isValidCommand(slovoPrikazu)) {
            ICommand prikaz = validCommands.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.executeCommand(parametry);
            if (gamePlan.isVictorious()) {
                gameOver = true;
            }
        } else {
            textKVypsani = "This command is invalid.";
        }
        return textKVypsani;
    }

    void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GamePlan getGamePlan() {
        return gamePlan;
    }

}

