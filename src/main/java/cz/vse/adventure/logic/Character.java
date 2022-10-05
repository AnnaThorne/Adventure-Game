package cz.vse.adventure.logic;

/**
 * Class Character - used to create characters that the player can interact with.
 * Author: Ashley Urválků
 * Version: 1.0
 */
public class Character {
    private String name;
    private boolean isEnemy;
    private boolean isLocked;
    private String greetingsText;
    private String infoText;
    private String unlockItem;

    /**
     * Instantiates a new Character.
     *
     * @param name          name
     * @param isEnemy       is the character an enemy?
     * @param isLocked      is their extra dialogue locked?
     * @param greetingsText basic greetings dialogue
     * @param infoText      additional  dialogue
     * @param unlockItem    item that unlocks the extra dialogue
     */
    public Character(String name, boolean isEnemy, boolean isLocked, String greetingsText, String infoText, String unlockItem) {
        this.name = name;
        this.isEnemy = isEnemy;
        this.isLocked = isLocked;
        this.greetingsText = greetingsText;
        this.infoText = infoText;
        this.unlockItem = unlockItem;
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Unlocks dialogue.
     */
    public void unlockDialogue() {
        isLocked = false;
    }

    /**
     * Is the character an enemy?.
     *
     * @return boolean
     */
    public boolean isEnemy() {
        return isEnemy;
    }

    /**
     * Is their extra dialogue locked?
     *
     * @return boolean
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Gets greetings text.
     *
     * @return the greetings text
     */
    public String getGreetingsText() {
        return greetingsText;
    }

    /**
     * Gets info text.
     *
     * @return the info text
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * Gets unlock item.
     *
     * @return the unlock item
     */
    public String getUnlockItem() {
        return unlockItem;
    }

}
