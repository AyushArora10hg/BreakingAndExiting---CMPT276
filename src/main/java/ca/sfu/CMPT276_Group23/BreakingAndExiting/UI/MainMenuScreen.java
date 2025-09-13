package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

/**
 * MainMenuScreen represents the main menu of Breaking and Exiting.
 *
 * <P>
 *     MainMenuScreen first uses a Rectangle2D object to determine the height and width of the screen.
 *     MainMenuScreen then creates a scene using a WindowPane that contains a play and quit button.
 *     Finally, MainMenuScreen sets the scene and displays it
 * </P>
 *
 * @author Conor Benson
 * @version 1.0
 */
public class MainMenuScreen extends Application {

    private Rectangle2D dimensions = Screen.getPrimary().getBounds();
    private int width;
    private int height;
    private Button play;
    private Button quit;
    private WindowPane menuPane;
    private Scene mainMenuScene;
    private SoundPlayer menuSoundPlayer;
    private ButtonCreator buttonCreator;

    /**
     * Constructor of MainMenuScreen (Called automatically by javaFX thread).
     *
     *<p>
     *     Constructor instantiates buttonCreator, menuSoundPlayer, width, height, and quit fields.
     *     The constructor then instantiates the menuPane WindowPane with the background image for the
     *     main menu. Finally, the constructor instantiates mainMenuScene with the menuPane.
     *</p>
     *
     */
    public MainMenuScreen() {
        buttonCreator = new ButtonCreator();
        menuSoundPlayer = new SoundPlayer(new File("src/Sounds/MainMenu.wav"));
        width = (int) dimensions.getWidth();
        height = (int) dimensions.getHeight();
        quit = buttonCreator.quitButton();
        menuPane = new WindowPane(width, height,
                new Image("file:src/Sprites/MenuSprites/MainMenuBg.jpg"));

        mainMenuScene = new Scene(menuPane);
    }

    /**
     * Start method of mainMenuScreen starts javaFX application thread.
     *
     * <p>
     *     Start method first instantiates the play button, because of its need of the stage to be
     *     created, then adds both buttons to the menuPane. Start then sets stage with the mainMenuScene
     *     and displays the stage to the user.
     * </p>
     *
     * @param primaryStage the primaryStage used by all scenes in Breaking and Exiting
     */
    @Override
    public void start(Stage primaryStage) {

        play = buttonCreator.playButton(primaryStage, menuSoundPlayer);
        menuPane.addButton(play, 1);
        menuPane.addButton(quit, 2);
        menuSoundPlayer.play();
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(mainMenuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }

    /**
     * Getter of play button. Used mainly for testing.
     *
     * @return MainMenuScreen's play button
     */
    public Button getPlay() {
        return play;
    }

    /**
     * Getter of quit button. Used mainly for testing.
     *
     * @return MainMenuScreen's quit button
     */
    public Button getQuit() {
        return quit;
    }

    /**
     * Getter of MainMenuScen.
     *
     * @return MainMenuScreen's mainMenuScene object.
     */
    public Scene getMainMenuScene() {
        return mainMenuScene;
    }

}