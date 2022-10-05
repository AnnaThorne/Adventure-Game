package cz.vse.adventure;

import cz.vse.adventure.logic.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída CommandsListTest slouží ke komplexnímu otestování třídy
 * CommandsList
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class CommandsListTest
{
    private Game game;
    private CommandExit exit;
    private CommandGo go;
    private CommandAttack attack;
    private CommandBag bag;
    private CommandHelp help;
    private CommandOpen open;
    private CommandShowRoom roomInfo;
    private CommandTake take;
    private CommandTalk talk;
    
    @BeforeEach
    public void setUp() {
        game = new Game();
        exit = new CommandExit(game);
        attack = new CommandAttack(game.getGamePlan(), game);
        go = new CommandGo(game.getGamePlan());
        take = new CommandTake(game.getGamePlan());
        bag = new CommandBag(game.getGamePlan());
        talk = new CommandTalk(game.getGamePlan());
        open = new CommandOpen(game.getGamePlan());
        take = new CommandTake(game.getGamePlan());
        help = new CommandHelp(new CommandsList());
        roomInfo = new CommandShowRoom(game.getGamePlan());
    }

    @Test
    public void vlozeniVybraniTest() {
        CommandsList seznPrikazu = new CommandsList();
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(go);
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(attack);
        seznPrikazu.addCommand(take);
        seznPrikazu.addCommand(bag);
        seznPrikazu.addCommand(open);
        seznPrikazu.addCommand(help);
        seznPrikazu.addCommand(roomInfo);

        assertEquals(exit, seznPrikazu.vratPrikaz("exit"));
        assertEquals(go, seznPrikazu.vratPrikaz("go"));
        assertEquals(help, seznPrikazu.vratPrikaz("help"));
        assertEquals(attack, seznPrikazu.vratPrikaz("attack"));
        assertEquals(take, seznPrikazu.vratPrikaz("take"));
        assertEquals(bag, seznPrikazu.vratPrikaz("bag"));
        assertEquals(open, seznPrikazu.vratPrikaz("open"));
        assertEquals(roomInfo, seznPrikazu.vratPrikaz("roomInfo"));
        assertNull(seznPrikazu.vratPrikaz("Help"));
    }
    @Test
    public void jePlatnyPrikazTest() {
        CommandsList seznPrikazu = new CommandsList();
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(go);
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(attack);
        seznPrikazu.addCommand(take);
        seznPrikazu.addCommand(bag);
        seznPrikazu.addCommand(open);
        seznPrikazu.addCommand(help);
        seznPrikazu.addCommand(roomInfo);

        assertTrue(seznPrikazu.isValidCommand("exit"));
        assertTrue(seznPrikazu.isValidCommand("go"));
        assertTrue(seznPrikazu.isValidCommand("help"));
        assertTrue(seznPrikazu.isValidCommand("take"));
        assertTrue(seznPrikazu.isValidCommand("attack"));
        assertTrue(seznPrikazu.isValidCommand("bag"));
        assertTrue(seznPrikazu.isValidCommand("open"));
        assertTrue(seznPrikazu.isValidCommand("roomInfo"));
        assertFalse(seznPrikazu.isValidCommand("End"));
    }
    
    @Test
    public void nazvyPrikazuTest() {
        CommandsList seznPrikazu = new CommandsList();
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(go);
        seznPrikazu.addCommand(exit);
        seznPrikazu.addCommand(attack);
        seznPrikazu.addCommand(take);
        seznPrikazu.addCommand(bag);
        seznPrikazu.addCommand(open);
        seznPrikazu.addCommand(help);
        seznPrikazu.addCommand(roomInfo);
        String nazvy = seznPrikazu.getValidCommand();
        assertTrue(nazvy.contains("exit"));
        assertTrue(nazvy.contains("go"));
        assertTrue(nazvy.contains("attack"));
        assertTrue(nazvy.contains("take"));
        assertTrue(nazvy.contains("bag"));
        assertTrue(nazvy.contains("open"));
        assertTrue(nazvy.contains("roomInfo"));
        assertTrue(nazvy.contains("help"));
        assertFalse(nazvy.contains("End"));
    }
    
}
