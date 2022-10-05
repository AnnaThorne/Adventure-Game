package cz.vse.adventure.logic;

import java.util.ArrayList;

/**
 * Class Container - an item that can store other items.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class Container extends Item{
    private ArrayList<Item> containedItems;

    /**
     * Instantiates a new Container.
     *
     * @param name the name
     */
    public Container(String name) {
        super(name, false, false);
        containedItems = new ArrayList<>();
    }

    /**
     * Add item.
     *
     * @param i the
     */
    public void addItem (Item i){
        containedItems.add(i);
    }

    /**
     * Empty.
     */
    public void empty(){
        containedItems = new ArrayList<>();
    }
    public ArrayList<Item> getItems(){
        return containedItems;
    }

}
