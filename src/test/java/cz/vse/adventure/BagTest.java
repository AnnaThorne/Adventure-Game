package cz.vse.adventure;

import cz.vse.adventure.logic.Bag;
import cz.vse.adventure.logic.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Bag.
 * Author: Ashley Urválků
 * Version: 1.0
 */
class BagTest {
    private Bag bag;

    /**
     * Set up.
     */
    @BeforeEach
    void setUp(){
        bag = new Bag(3);
    }

    /**
     * Add item test.
     */
    @Test
    void addTest() {
        Item i1 = new Item("cake",true,false);
        Item i2 = new Item("sword",true,true);
        Item i3 = new Item("fork",true,true);
        Item i4 = new Item("spoon",true,true);
        assertTrue(bag.add(i1));
        assertTrue(bag.add(i2));
        assertTrue(bag.add(i3));
        assertFalse(bag.add(i4));
    }

    /**
     * Get item test.
     */
    @Test
    void getItemTest() {
        Item i1 = new Item("cake",true,false);
        Item i2 = new Item("sword",true,true);
        Item i3 = new Item("fork",true,true);
        bag.add(i1);
        bag.add(i2);
        bag.add(i3);
        assertEquals(i1,bag.getItem("cake"));
        assertEquals(i2,bag.getItem("sword"));
        assertEquals(i3,bag.getItem("fork"));

    }
}