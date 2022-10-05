package cz.vse.adventure.logic;

import java.util.Objects;

/**
 * Class Item - an item that the player can interact with.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class Item {
    private String name;
    private boolean isMovable;
    private boolean isWeapon;

    /**
     * Instantiates a new Item.
     *
     * @param name      name
     * @param isMovable is the item movable?
     */
    public Item(String name, boolean isMovable) {
        this.name = name;
        this.isMovable = isMovable;
        this.isWeapon = false;
    }

    /**
     * Instantiates a new Item.
     *
     * @param name      name
     * @param isMovable is the item movable?
     * @param isWeapon  is the item a weapon?
     */
    public Item(String name, boolean isMovable, boolean isWeapon) {
        this.name = name;
        this.isMovable = isMovable;
        this.isWeapon = isWeapon;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Is movable boolean.
     *
     * @return the boolean
     */
    public boolean isMovable() {
        return isMovable;
    }

    /**
     * Is weapon boolean.
     *
     * @return the boolean
     */
    public boolean isWeapon(){
        return isWeapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
