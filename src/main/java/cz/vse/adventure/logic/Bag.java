package cz.vse.adventure.logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class Bag - this is where the player stores their items.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class Bag implements IObservable {
    private Map<String, Item> heldItems;
    private static int CAPACITY = 5;
    private HashSet<IObserver> observerSet = new HashSet<>();
    /**
     * Instantiates a new Bag.
     */
    public Bag() {
        this.heldItems = new HashMap<>();
    }

    /**
     * Instantiates a new Bag.
     *
     * @param capacity the capacity
     */
    public Bag(int capacity) {
        this.heldItems = new HashMap<>();
        CAPACITY = capacity;
    }

    /**
     * Adds a new item
     *
     * @param i the item you want to add
     * @return true on success, false on fail
     */
    public boolean add(Item i){
        if(heldItems.size() < CAPACITY){
           heldItems.put(i.getName(), i);
           notifyObserver();
           return true;
        }else{
            return false;
        }
    }

    /**
     * Gets item.
     *
     * @param itemName the item name
     * @return the item
     */
    public Item getItem(String itemName){
        return heldItems.get(itemName);
    }

    /**
     * Gets names of all held items
     *
     * @return names
     */
    public String getItems(){
        StringBuilder returnText = new StringBuilder("Held Items: ");
        for (String nazev : heldItems.keySet()){
            returnText.append(" ").append(nazev);

        }
        return returnText.toString();
    }

    /**
     * Gets all held items.
     *
     * @return held items
     */
    public Map<String, Item> getHeldItems(){
        return heldItems;
    }

    public Collection<Item> getInventory(){
        return heldItems.values();
    }

    /**
     * Has victory key boolean.
     *
     * @return boolean
     */
    public boolean hasVictoryKey(){
        return heldItems.values().stream().anyMatch(i -> i.getName().equals("GateKey"));
    }

    /** Registers a new observer
     *
     * @param observer the observer to register
     */
    @Override
    public void register(IObserver observer) {
        this.observerSet.add(observer);
    }

    /** Notifies each observer
     *
     */
    private void notifyObserver(){
        for (IObserver observer: observerSet) {
            observer.update(this);
        }
    }
}
