package cz.vse.adventure.logic;
public class CommandHelp implements ICommand {
    
    private static final String NAME = "help";
    private CommandsList platnePrikazy;
    public CommandHelp(CommandsList platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    @Override
    public String executeCommand(String... params) {
        return "Your task is to make your way through the map,\n"
        + "until you reach the golden carrot, which is located at the carrot farm.\n"
        + "\n"
        + "Usable commands:\n"
        + platnePrikazy.getValidCommand();
    }
    @Override
      public String getName() {
        return NAME;
     }

}
