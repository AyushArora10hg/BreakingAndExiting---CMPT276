package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * WindowPane class represents a stackPane used by the MainMenuScreen, LoseScreen and WinScreen.
 * Class uses a stackpane to create a centered background and uses a vBox to store the buttons of
 * the screen.
 *
 *
 * @author Conor Benson
 * @version 1.0
 */

public class WindowPane extends StackPane {

    private VBox buttonBox;

    /**
     * Constructor of windowPane sets up a stackPane to store the elements of the corresponding screen.
     *
     *
     * <p>
     *     The constructor first sets the width and height of the pane based on the provided WORLD_WIDTH
     *  `  and WORLD_HEIGHT parameters, it then sets the background to the provided image. Finally,
     *     the constructor creates a vBox, buttonBox, to store elements in the center of the screen.
     * </p>
     *
     *
     * @param WORLD_WIDTH  width of the WindowPane
     * @param WORLD_HEIGHT height of the WindowPane
     * @param backGroundImage image to be used as the background of the WindowPane
     */
    public WindowPane(int WORLD_WIDTH, int WORLD_HEIGHT, Image backGroundImage) {

        setAlignment(Pos.CENTER);
        this.setPrefSize(WORLD_WIDTH, WORLD_HEIGHT);
        ImageView bg = new ImageView(backGroundImage);
        bg.setFitWidth(WORLD_WIDTH);
        bg.setFitHeight(WORLD_HEIGHT);
        this.getChildren().add(bg);

        buttonBox = new VBox(25);
        buttonBox.setAlignment(Pos.CENTER);
        this.getChildren().add(buttonBox);

    }

    /**
     * addButton method is used to add buttons to the buttonBox vbox of the WindowPane.
     *
     * <p>
     *     addButton takes in a button and a key, it first sets the the hover image of the button
     *     based on the corresponding key, addButton then add the button to the WindowPane
     *     buttonBox vbox.
     * </p>
     *
     * @param button a button to be added to the WindowPanes buttonBox vbox.
     * @param key a key specific to the type of button being added.
     */
    public void addButton(Button button, int key) {
        ImageView currentGraphic = (ImageView) button.getGraphic();

        if (key == 1) {

            button.setOnMouseEntered(e -> button.setGraphic(
                    new ImageView(new Image("file:src/Sprites/MenuSprites/highlightedPlayButton.png"))));
        }

        if (key == 2) {
            button.setOnMouseEntered(e -> button.setGraphic(
                    new ImageView(new Image("file:src/Sprites/MenuSprites/highlightedQuitButton.png"))));
        }

        if (key == 3) {
            button.setOnMouseEntered(e -> button.setGraphic(
                    new ImageView(new Image("file:src/Sprites/LoseScreenSprites/highlightedMainMenu.png"))));
        }

        button.setOnMouseExited(e -> button.setGraphic(currentGraphic));
        button.setStyle("-fx-background-color: transparent;");
        buttonBox.getChildren().add(button);
    }

    /**
     * Add methods for adding none button nodes to the buttonBox vbox.
     *
     * @param node a node to be added to the button box of a screen.
     */
    public void add(Node node) {
        buttonBox.getChildren().add(node);
    }
}