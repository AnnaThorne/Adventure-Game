package cz.vse.adventure.main;

import cz.vse.adventure.logic.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HomeController implements IObserver{
    @FXML
    public MenuItem helpMenu;
    @FXML
    public MenuItem newGameMenu;
    @FXML
    public ListView<Item> inventoryPanel;
    @FXML
    private ImageView player;
    @FXML
    private ListView<Room> exitPanel;
    @FXML
    private TextArea textOutput;
    @FXML
    private TextField textInput;
    @FXML
    private Button buttonSend;

    private Map<String, Point2D> coordinates;
    private HashMap<Object, Object> roomImages;
    private HashMap<Object, Object> itemImages;
    private IGame game = new Game();

    /** Initializes a new game
     *
     */
    @FXML
    private void initialize() {
        setRoomImages();
        setItemImages();
        setCoordinates();
        player.setFitWidth(59);
        player.setFitHeight(52);
        game.getGamePlan().register(this);
        game.getGamePlan().getBag().register(this);
        textOutput.appendText(game.returnGreeting() + "\n\n");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textInput.requestFocus();
            }
        });
        updateExits();

        exitPanel.setCellFactory(listView -> new RoomListCell());
        inventoryPanel.setCellFactory(listView -> new ItemListCell());
    }

    /** Retrieves text input from the gui and passes it onto the game logic
     *
     * @param actionEvent button press event
     */
    @FXML
    private void sendInput(ActionEvent actionEvent) {
        String cmd = textInput.getText();
        textInput.clear();
        executeCmd(cmd);
    }

    /** Executes in-game commands and prints out the result onto the gui
     *
     * @param cmd name of the command to execute
     */
    private void executeCmd(String cmd) {
        String result = game.executeCommand(cmd);
        textOutput.appendText("> " + cmd + "\n");
        textOutput.appendText(result + "\n\n");
        if (game.endGame()) {
            if(game.getGamePlan().isVictorious()) {
                textOutput.appendText(game.returnEpilogue());
            }
            textInput.setDisable(true);
            buttonSend.setDisable(true);
            exitPanel.setDisable(true);
        }
    }

    /** Moves the player to the selected room upon clicking on it in the exits panel
     *
     * @param mouseEvent mouse click event
     */
    public void onExitPanelCLicked(MouseEvent mouseEvent) {
        Room newRoom = exitPanel.getSelectionModel().getSelectedItem();
        if (newRoom == null) return;
        String cmd = CommandGo.NAME + " " + newRoom;
        executeCmd(cmd);
    }

    /** Shows the contents of the player's inventory upon clicking on the player avatar
     *
     * @param mouseEvent mouse click event
     */
    public void playerClicked(MouseEvent mouseEvent) {
        executeCmd(CommandBag.NAME);
    }

    /** Updates the exit list on the gui
     *
     */
    private void updateExits() {
        exitPanel.getItems().clear();
        Collection<Room> exits = game.getGamePlan().getCurrentRoom().getExits();
        exitPanel.getItems().addAll(exits);
    }

    /** Updates the observer upon being notified by the observable
     *
     * @param observable the observable
     */
    @Override
    public void update(IObservable observable) {
        updateExits();
        updatePlayerPosition();
        updateInventory();
    }

    /** Returns room coordinates
     *
     * @return room coordinates
     */
    public Map<String, Point2D> getRoomCoordinates() {
        if (coordinates == null) {
            setCoordinates();
        }
        return coordinates;
    }

    /** Sets room coordinates
     *
     */
    public void setCoordinates() {
        coordinates = new HashMap<>();
        coordinates.put("DarkForest", new Point2D(39, 89));
        coordinates.put("Castle", new Point2D(135, 89));
        coordinates.put("CrystalCave", new Point2D(135, 150));
        coordinates.put("WizardTower", new Point2D(135, 210));
        coordinates.put("WitchHouse", new Point2D(225, 150));
        coordinates.put("Graveyard", new Point2D(135, 30));
        coordinates.put("PumpkinField", new Point2D(135, -20));
        coordinates.put("Lake", new Point2D(225, 89));
        coordinates.put("CarrotFarm", new Point2D(345, 89));
    }

    /** Updates player position
     *
     */
    public void updatePlayerPosition() {
        Room currentRoom = game.getGamePlan().getCurrentRoom();
        Point2D coordinate = coordinates.get(currentRoom.getName());
        player.setLayoutX(coordinate.getX());
        player.setLayoutY(coordinate.getY());
    }

    /** Returns a room image
     *
     * @param room room
     * @return item view with a room image
     */
    private ImageView getRoomImage(Room room){
        String pathToImage = getClass().getResource("/roomImg").toExternalForm();
        ImageView iw = new ImageView(pathToImage+"/" + room.getName() + ".png");
        iw.setFitHeight(70);
        iw.setPreserveRatio(true);
        return iw;
    }

    /** Sets room images
     *
     */
    private void setRoomImages(){
        roomImages = new HashMap<>();
        roomImages.put("DarkForest", new ImageView());
        roomImages.put("Castle", new ImageView());
        roomImages.put("CrystalCave", new ImageView());
        roomImages.put("WizardTower", new ImageView());
        roomImages.put("WitchHouse", new ImageView());
        roomImages.put("Graveyard", new ImageView());
        roomImages.put("Lake", new ImageView());
        roomImages.put("CarrotFarm", new ImageView());

    }

    /** User clicked on help menu
     *
     * @param actionEvent
     */
    public void helpMenuClicked(ActionEvent actionEvent) {
        Stage helpWindow = new Stage();
        WebView wv = new WebView();
        helpWindow.setScene(new Scene(wv));
        wv.getEngine().load(getClass().getResource("/cz.vse.adventure.main/help.html").toExternalForm());
        helpWindow.show();
    }

    /** User clicked on start new game
     *
     * @param actionEvent
     */
    public void newGameMenuClicked(ActionEvent actionEvent) {
        textOutput.clear();
        game = new Game();
        game.getGamePlan().register(this);
        game.getGamePlan().refreshRooms();
    }

    /**
     * Updates inventory gui
     */
    private void updateInventory(){
        inventoryPanel.getItems().clear();
        Collection<Item> items = game.getGamePlan().getBag().getInventory();
        inventoryPanel.getItems().addAll(items);
    }

    /**
     * Sets item images
     */
    private void setItemImages(){
        itemImages = new HashMap<>();
        itemImages.put("sword", new ImageView());
        itemImages.put("crystal", new ImageView());
        itemImages.put("GateKey", new ImageView());
        itemImages.put("GoldenCarrot", new ImageView());
    }

    /**
     * Returns an item image
     * @param item item
     * @return image view with an item image
     */
    private ImageView getItemImage(Item item){
        String pathToImage = getClass().getResource("/itemImg").toExternalForm();
        ImageView iw = new ImageView(pathToImage+"/" + item.getName() + ".png");
        iw.setFitHeight(70);
        iw.setPreserveRatio(true);
        return iw;
    }
    /** Internal cell class for rooms
     *
     */
    class RoomListCell extends ListCell<Room> {
        @Override
        protected void updateItem(Room room, boolean b) {
            super.updateItem(room, b);
            if (room != null) {
                setText(room.getName());
                setGraphic(getRoomImage(room));
            } else {
                setText(null);
                setGraphic(null);
            }
        }
    }
    /** Internal cell class for items
     *
     */
    class ItemListCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean b) {
            super.updateItem(item, b);
            if (item != null) {
                setText(item.getName());
                setGraphic(getItemImage(item));
            } else {
                setText(null);
                setGraphic(null);
            }
        }
    }
}


