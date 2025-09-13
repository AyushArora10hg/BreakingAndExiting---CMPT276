package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * LoseScreen represents the screen called if the user loses Breaking and Entering.
 *
 * <p>
 *     LoseScreen consists of a main background image and two buttons, quit and main menu. The main menu
 *     button returns the user to the main menu while the quit button exits the game.
 * </p>
 *
 * @author Conor Benson
 * @version 1.0
 */
public class LoseScreen {

    private Stage stage = new Stage();
    private Scene loseScreen;
    private Rectangle2D dimensions = Screen.getPrimary().getBounds();
    private int width;
    private int height;
    private Button mainMenu;
    private Button quit;
    private ButtonCreator buttonCreator;

    /**
     * Constructor for loseScreen.
     *
     * <p>
     *     Constructor first instantiates the width, height, main menu and quit variables. The constructor
     *     then instantiates the losePane WindowPane with the background image for the lose screen.
     *     Finally, the constructor implements the logic of both the main menu and
     *     quit buttons and adds them to the WindowPane.
     * </p>
     *
     * @param stage the stage used by all screen in Breaking and Exiting
     */
    public LoseScreen(Stage stage) {

        buttonCreator = new ButtonCreator();
        width = (int) dimensions.getWidth();
        height = (int) dimensions.getHeight();
        mainMenu = buttonCreator.mainMenuButton(stage);
        quit = buttonCreator.quitButton();

        stage.setTitle("Lose Screen");
        WindowPane losePane = new WindowPane(width, height,
                new Image("file:src/Sprites/LoseScreenSprites/loseScreenBackground.png"));

        losePane.addButton(mainMenu, 3);
        losePane.addButton(quit, 2);

        loseScreen = new Scene(losePane);
    }

    /**
     * Getter of the loseScreen's scene variable. Getter is used by the levelScreen to
     * set the stage with the loseScreen if the user loses the game.
     *
     * @return the loseScreen's scene
     */
    public Scene getScene() {
        return loseScreen;
    }

    /**
     * Getter of LoseScreen's mainMenu button.
     *
     * @return LoseScreen's mainMenu button object.
     */
    public Button getMainMenu() {
        return mainMenu;
    }

    /**
     * Getter of LoseScreen's quit button.
     *
     * @return LoseScreen's quit button object.
     */
    public Button getQuit() {
        return quit;
    }

}