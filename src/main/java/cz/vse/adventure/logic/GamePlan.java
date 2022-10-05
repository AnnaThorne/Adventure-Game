package cz.vse.adventure.logic;


import java.util.HashSet;
public class GamePlan implements IObservable{
    
    private Room currentRoom;
    private Room victoryRoom;
    private Bag playerBag;
    private HashSet<IObserver> observerSet = new HashSet<>();;
    public GamePlan() {
        zalozProstoryHry();
        playerBag = new Bag();
    }

    public Bag getBag(){
        return playerBag;
    }
    private void zalozProstoryHry() {

        Room darkForest = new Room("DarkForest","a dark forest, your starting location.");
        Room castle = new Room("Castle", "a seemingly abandoned castle, with many pathways leading out of it.");
        Room crystalCave = new Room("CrystalCave","a cave lined with light emitting crystals.");
        Room wizardTower = new Room("WizardTower","an ancient magical tower, looming over you from high above.");
        Room witchHouse = new Room("WitchHouse","a house occupied by a friendly witch.");
        Room graveyard = new Room("Graveyard", "a graveyard, steeped in darkness.");
        Room pumpkinField = new Room("PumpkinField", "a field filled with pumpkins and a lone scarecrow staring you down.");
        Room lake = new Room("Lake", "an ordinary lake.");
        Room carrotFarm = new Room("CarrotFarm", "a farm filled with carrots, also your end destination.");

        Item rock = new Item("rock", false, false);
        darkForest.addItem(rock);

        Item sword = new Item("sword", true, true);
        Container chest = new Container("chest");
        chest.addItem(sword);
        Item crystal = new Item("crystal", true, false);
        crystalCave.addItem(chest);
        crystalCave.addItem(crystal);

        Item gateKey = new Item("GateKey", true, false);
        wizardTower.addItem(gateKey);

        Item goldenCarrot = new Item("GoldenCarrot", true, false);
        carrotFarm.addItem(goldenCarrot);

        darkForest.setExit(castle);

        castle.setExit(darkForest);
        castle.setExit(graveyard);
        castle.setExit(crystalCave);

        graveyard.setExit(castle);
        graveyard.setExit(pumpkinField);
        graveyard.setExit(lake);

        pumpkinField.setExit(graveyard);

        crystalCave.setExit(castle);
        crystalCave.setExit(wizardTower);
        crystalCave.setExit(witchHouse);

        wizardTower.setExit(crystalCave);
        wizardTower.setExit(witchHouse);

        witchHouse.setExit(crystalCave);
        witchHouse.setExit(wizardTower);
        witchHouse.setExit(lake);

        lake.setExit(graveyard);
        lake.setExit(witchHouse);
        lake.setExit(carrotFarm);

        pumpkinField.addCharacter(new Character("Scarecrow", false, false, null, "Let me give you a hint - if you're "
                + "looking for the carrot farm, \n"
                + "then you'll need to pass through the graveyard to get to the lake, that's where the entrance is. "
                + "\nOr, you can go past the wizard "
                + "tower, through the witch's house and then you'll get to the lake too."
                + "\nBut the entrance is locked, so you'll have to go to the wizard tower and find the key."
                , null));

        graveyard.addCharacter(new Character("SkeletonWarrior", true, false, null, "I have nothing to say to the likes "
                + "of you!", null));

        lake.addCharacter(new Character("LakeMonster", true, false, null, "Perish...", null));

        witchHouse.addCharacter(new Character("Witch", false, true, "Bring me a crystal"
                + " from the Crystal Cave.\n And I'll give you a hint.", "There's a chest inside the Crystal Cave containing a sword.\n"
                + "You won't be able to win any fights without a weapon.", "crystal"));

        setCurrentRoom(darkForest);
        victoryRoom = carrotFarm;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /** Returns the victory destination
     *
     * @return victory destination
     */
    public Room getVictoryRoom(){
        return victoryRoom;
    }

    /** Returns whether the player has won the game
     *
     * @return is the player victorious
     */
    public boolean isVictorious(){
        return currentRoom.equals(victoryRoom);
    }
    public void setCurrentRoom(Room room) {
       currentRoom = room;
       notifyObserver();
    }
    public void refreshRooms(){
        notifyObserver();
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
