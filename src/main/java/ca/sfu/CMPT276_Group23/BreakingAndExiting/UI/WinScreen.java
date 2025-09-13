package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
//private static File audioFile = new File("src/Sounds/WinScreen.wav");
/**
 * WinScreen represents the screen called if the user wins Breaking and Entering.
 *
 * <p>
 *     WinScreen consists of a main background image, the users final score
 *     adn two buttons, quit and main menu. The main menu
 *     button returns the user to the main menu while the quit button exits the game.
 * </p>
 *
 * @author Conor Benson
 * @version 1.0
 */
public class WinScreen {

    private Stage stage = new Stage();
    private Scene winScene;
    private Rectangle2D dimensions = Screen.getPrimary().getBounds();
    private int width;
    private int height;
    private Button quit;
    private Button mainMenu;
    private Label scoreLabel;
    private WindowPane winPane;
    private static File audioFile = new File("src/Sounds/WinScreen.wav");
    private ButtonCreator buttonCreator;

    /**
     * Constructor for WinScreen.
     *
     * <p>
     * Constructor first instantiates the width, height, main menu and quit variables. The constructor
     * then instantiates the losePane WindowPane with the background image for the lose screen.
     * Finally, the constructor implements the logic of the score label, main menu and
     * quit buttons and adds them to the WindowPane.
     * </p>
     *
     * @param stage the stage used by all screen in Breaking and Exiting
     * @param finalScore The final score attained by the player upon winning.
     */
    public WinScreen(Stage stage, int finalScore) {

        buttonCreator = new ButtonCreator();
        Font myFont = Font.loadFont(
                "file:src/main/resources/ca/sfu/CMPT276_Group23/BreakingAndExiting/Minecraft.ttf",
                30);

        width = (int) dimensions.getWidth();
        height = (int) dimensions.getHeight();
        mainMenu = buttonCreator.mainMenuButton(stage);
        quit = buttonCreator.quitButton();

        stage.setTitle("Win Screen");
        winPane = new WindowPane(width, height,
                new Image("file:src/Sprites/WinScreenSprites/winScreenBackground.png"));

        scoreLabel = new Label("Final Score: " + finalScore);
        scoreLabel.setFont(myFont);
        scoreLabel.setStyle("-fx-text-fill: yellow; " + "-fx-border-color: black; " +
                "-fx-border-width: 2px;" + "-fx-padding: 5px;");

        winPane.add(scoreLabel);

        winPane.addButton(mainMenu, 3);
        winPane.addButton(quit, 2);

        winScene = new Scene(winPane);
        this.playSound();
    }

    /**
     * Getter of the WinScreen's scene variable. Getter is used by the levelScreen to
     * set the stage with the WinScreen if the user wins the game.
     *
     * @return the WinScreens scene.
     */
    public Scene getWinScene() {
        return winScene;
    }


    /**
     * playSound plays the sound relating to winning the game
     * when the player enters the exit door.
     *
     */
    public void playSound() {
        SoundPlayer winScreenPlayer = new SoundPlayer(audioFile);
        winScreenPlayer.play();
    }

    /**
     * Getter of WinScreen's mainMenu button.
     *
     * @return WinScreen's mainMenu button.
     */
    public Button getMainMenu() {
        return mainMenu;
    }

    /**
     * Getter method of winScreens quit button.
     *
     * @return WinScreen's quit button.
     */
    public Button getQuit() {
        return quit;
    }

    /**
     * getScoreLabelString returns a string of the players score at the
     * end of the game.
     *
     * @return The score of the player upon winning.
     */
    public String getScoreLabelString(){
        return scoreLabel.getText();
    }

}

