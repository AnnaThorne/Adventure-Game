package cz.vse.adventure;
import cz.vse.adventure.logic.Bag;
import cz.vse.adventure.logic.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída GameTest slouží ke komplexnímu otestování
 * třídy Game
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class GameTest {
    private Game game;
    private Bag bag;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        game = new Game();
        bag = game.getGamePlan().getBag();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @BeforeEach
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void victoryTest() {
        assertEquals("DarkForest", game.getGamePlan().getCurrentRoom().getName());
        game.executeCommand("take rock");
        assertFalse(game.endGame());
        game.executeCommand("go Castle");
        assertFalse(game.endGame());
        assertEquals("Castle", game.getGamePlan().getCurrentRoom().getName());
        game.executeCommand("go CrystalCave");
        assertFalse(game.endGame());
        assertEquals("CrystalCave", game.getGamePlan().getCurrentRoom().getName());
        assertFalse(game.endGame());
        game.executeCommand("open chest");
        assertFalse(game.endGame());
        game.executeCommand("take sword");
        assertFalse(game.endGame());
        game.executeCommand("go WizardTower");
        assertFalse(game.endGame());
        assertEquals("WizardTower", game.getGamePlan().getCurrentRoom().getName());
        assertFalse(game.endGame());
        game.executeCommand("take GateKey");
        assertFalse(game.endGame());
        game.executeCommand("go WitchHouse");
        assertFalse(game.endGame());
        assertEquals("WitchHouse", game.getGamePlan().getCurrentRoom().getName());
        assertFalse(game.endGame());
        game.executeCommand("talk Witch");
        assertFalse(game.endGame());
        game.executeCommand("go Lake");
        assertFalse(game.endGame());
        assertEquals("Lake", game.getGamePlan().getCurrentRoom().getName());
        assertFalse(game.endGame());
        game.executeCommand("attack LakeMonster");
        assertFalse(game.endGame());
        game.executeCommand("go CarrotFarm");
        assertEquals("CarrotFarm", game.getGamePlan().getCurrentRoom().getName());
        assertTrue(game.getGamePlan().isVictorious());
        assertTrue(game.endGame());
    }
    @Test
    public void exitTest() {
        assertFalse(game.endGame());
        game.executeCommand("exit");
        assertTrue(game.endGame());
    }
    @Test
    public void itemTakeTest() {
        game.executeCommand("take rock");
        assertNull(bag.getItem("rock"));
        game.executeCommand("go Castle");
        game.executeCommand("go CrystalCave");
        game.executeCommand("take chest");
        assertNull(bag.getItem("chest"));
        game.executeCommand("open chest");
        assertEquals("sword",game.getGamePlan().getCurrentRoom().getItem("sword").getName());
        game.executeCommand("take sword");
        assertNull(game.getGamePlan().getCurrentRoom().getItem("sword"));
        assertEquals("sword",bag.getItem("sword").getName());
    }
    @Test
    public void characterTest() {
        game.executeCommand("go Castle");
        game.executeCommand("go CrystalCave");
        game.executeCommand("go WitchHouse");
        assertEquals("Witch",game.getGamePlan().getCurrentRoom().getCharacter("Witch").getName());
    }
}
