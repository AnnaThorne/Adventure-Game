package cz.vse.adventure;

import cz.vse.adventure.logic.Item;
import cz.vse.adventure.logic.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/*******************************************************************************
 * Testovací třída RoomTest slouží ke komplexnímu otestování
 * třídy Room
 *
 * @author Jarmila Pavlíčková
 * @version pro skolní rok 2016/2017
 */
public class RoomTest {
    private Room darkForest;
    private Room castle;
    private Room crystalCave;
    private Room graveyard;
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
        darkForest = new Room("DarkForest", "a dark forest, your starting location.");
        castle = new Room("Castle", "a seemingly abandoned castle, with many pathways leading out of it.");
        crystalCave = new Room("CrystalCave", "a cave lined with light emitting crystals.");
        graveyard = new Room("Graveyard", "a graveyard, steeped in darkness.");
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře,
     */
    @Test
    public void testLzeProjit() {
        darkForest = new Room("DarkForest", "a dark forest, your starting location.");
        castle = new Room("Castle", "a seemingly abandoned castle, with many pathways leading out of it.");
        crystalCave = new Room("CrystalCave", "a cave lined with light emitting crystals.");
        graveyard = new Room("Graveyard", "a graveyard, steeped in darkness.");
        darkForest.setExit(castle);
        castle.setExit(darkForest);
        castle.setExit(graveyard);
        castle.setExit(crystalCave);
        graveyard.setExit(castle);
        crystalCave.setExit(castle);

        assertEquals(darkForest, castle.getAdjacentRoom("DarkForest"));
        assertEquals(graveyard, castle.getAdjacentRoom("Graveyard"));
        assertEquals(crystalCave, castle.getAdjacentRoom("CrystalCave"));
        assertEquals(castle, darkForest.getAdjacentRoom("Castle"));
        assertEquals(castle, graveyard.getAdjacentRoom("Castle"));
        assertEquals(castle, crystalCave.getAdjacentRoom("Castle"));
        assertNull(castle.getAdjacentRoom("CarrotFarm"));
    }

    @Test
    public void addItemTest() {
        Item cake = new Item("cake",true,false);
        Item stone = new Item("stone",false,false);
        darkForest.addItem(cake);
        darkForest.addItem(stone);
        assertEquals(cake, darkForest.getItem(cake));
        assertEquals(stone, darkForest.getItem(stone));
        assertNull(darkForest.getItem("Fridge"));

    }
}
