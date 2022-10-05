package cz.vse.adventure.logic;

import java.util.*;
import java.util.stream.Collectors;

public class Room {

    private String name;
    private String description;
    private Set<Room> exits;
    private Map<String, Item> items;
    private Set<Character> characters;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashSet<>();
        items = new HashMap<>();
        characters = new HashSet<>();
    }

    /**
     * Adds an item to the room
     *
     * @param item
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    /**
     * Gets an item
     *
     * @param item the item to get
     * @return the returned item
     */
    public Item getItem(Item item) {
        return items.get(item.getName());
    }

    /**
     * Gets an item by name
     *
     * @param itemName the item name
     * @return the returned item
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    /**
     * Removes item
     *
     * @param item the item to remove
     */
    public void removeItem(Item item) {
        items.remove(item.getName());
    }

    /**
     * Adds a character to the room
     *
     * @param c the character to add
     */
    public void addCharacter(Character c) {
        characters.add(c);
    }

    /**
     * Gets a character
     *
     * @param name the name of the charcter to get
     * @return returned character
     */
    public Character getCharacter(String name) {
        for (Character c : characters) {
            if (Objects.equals(c.getName(), name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Removes a character from the room
     *
     * @param c The character to remove
     */
    public void removeCharacter(Character c) {
        characters.remove(c);
    }

    /**
     * Returns the enemy present in the room
     *
     * @return enemy
     */
    public Character getEnemy() {
        return characters.stream().filter(Character::isEnemy).findAny().orElse(null);
    }

    private String itemsDescription() {
        StringBuilder returnText = new StringBuilder("Items:");
        if (items.isEmpty()) {
            returnText.append(" ").append("There are no items in this room.");
        } else {
            for (String nazev : items.keySet()) {
                returnText.append(" ").append(nazev);

            }
        }
        return returnText.toString();
    }

    /**
     * Returns a character description
     *
     * @return character description
     */
    private String charactersDescription() {
        StringBuilder returnText = new StringBuilder("Characters:");
        if (characters.isEmpty()) {
            returnText.append(" ").append("There are no characters in this room.");
        } else {
            for (Character c : characters) {
                returnText.append(" ").append(c.getName());
            }
        }
        return returnText.toString();
    }

    public void setExit(Room vedlejsi) {
        exits.add(vedlejsi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;

        return (java.util.Objects.equals(this.name, room.name));
    }

    @Override
    public int hashCode() {
        int result = 3;
        int nameHash = java.util.Objects.hashCode(this.name);
        result = 37 * result + nameHash;
        return result;
    }

    public String getName() {
        return name;
    }


    public String longDesc() {
        return "You're currently in " + description + ".\n"
                + exitDescription() + "\n" + itemsDescription() + "\n" + charactersDescription();
    }

    private String exitDescription() {
        StringBuilder returnText = new StringBuilder("Exits:");
        for (Room adjacent : exits) {
            returnText.append(" ").append(adjacent.getName());
        }
        return returnText.toString();
    }

    public Room getAdjacentRoom(String adjacentName) {
        List<Room> adjacentRooms =
                exits.stream()
                        .filter(adjacent -> adjacent.getName().equals(adjacentName))
                        .collect(Collectors.toList());
        if (adjacentRooms.isEmpty()) {
            return null;
        } else {
            return adjacentRooms.get(0);
        }
    }

    public Collection<Room> getExits() {
        return Collections.unmodifiableCollection(exits);
    }

    @Override
    public String toString() {
        return getName();
    }
}
