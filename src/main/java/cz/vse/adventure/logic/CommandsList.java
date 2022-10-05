package cz.vse.adventure.logic;

import java.util.HashMap;
import java.util.Map;

public class CommandsList {
    private  Map<String, ICommand> mapaSPrikazy;
    public CommandsList() {
        mapaSPrikazy = new HashMap<>();
    }
    public void addCommand(ICommand prikaz) {
        mapaSPrikazy.put(prikaz.getName(),prikaz);
    }

    public ICommand vratPrikaz(String retezec) {
        if (mapaSPrikazy.containsKey(retezec)) {
            return mapaSPrikazy.get(retezec);
        }
        else {
            return null;
        }
    }

    public boolean isValidCommand(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }

    public String getValidCommand() {
        StringBuilder seznam = new StringBuilder();
        for (String slovoPrikazu : mapaSPrikazy.keySet()){
            seznam.append(slovoPrikazu).append(" ");
        }
        return seznam.toString();
    }
    
}

