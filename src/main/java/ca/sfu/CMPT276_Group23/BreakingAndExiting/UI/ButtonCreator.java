package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * Button creator class is a factory class used to create buttons for the various screens of the game.
 */
public class ButtonCreator {

    private Button playButton;
    private Button quitButton;
    private Button mainMenuButton;

    /**
     * Default constructor of ButtonCreator
     */
    public ButtonCreator() {

    }
    /**
     * playButton creates a new instance of a button used to pass the stage from the mainMenu to
     * level.
     *
     * <p>
     *     playButton creates a new button, playButton, sets it graphic, height and width then
     *     implements the "click" action so that when clicked a new level is created and displayed.
     * </p>
     *
     * @param primaryStage The stage that play button links to.
     * @param menuSoundPlayer Sound player used to play a sound when user clicks play.
     * @return An instance of a playButton.
     */
    public Button playButton(Stage primaryStage, SoundPlayer menuSoundPlayer) {

        playButton = new Button();
        playButton.setPrefHeight(75);
        playButton.setPrefWidth(400);

        playButton.setGraphic(new ImageView(
                new Image("file:src/Sprites/MenuSprites/playButton.png")));

        playButton.setOnAction(e -> {
            Level level = new Level(primaryStage);
            menuSoundPlayer.stop();
            level.start();

        });

        return playButton;
    }

    /**
     * quitButton creates a new instance of a button used to quit the game application.
     *
     * <p>
     *     quitButton creates a new button, quitButton, sets it graphic, height and width then
     *     implements the "click" action so that when clicked the game window is closed.
     * </p>
     *
     *
     * @return A newly created quit button.
     */
    public Button quitButton() {

        quitButton = new Button();
        quitButton.setPrefHeight(50);
        quitButton.setPrefWidth(150);

        quitButton.setGraphic(new ImageView(
                new Image("file:src/Sprites/MenuSprites/quitButton.png")));
        quitButton.setOnAction(e -> {
            Platform.exit();
        });

        return quitButton;
    }

    /**
     * mainMenuButton creates a new instance of a button used to return to the main menu.
     *
     * <p>
     *      mainMenuButton creates a new button, mainMenuButton, sets it graphic, height and width then
     *      implements the "click" action so that when clicked the application displays the mainMenuScreen.
     * </p>
     *
     * @param primaryStage The stage that the mainMenu button links to.
     * @return A newly created mainMenu button.
     */
    public Button mainMenuButton(Stage primaryStage) {

        mainMenuButton = new Button();
        mainMenuButton.setPrefHeight(75);
        mainMenuButton.setPrefWidth(400);

        mainMenuButton.setGraphic(new ImageView(
                new Image("file:src/Sprites/LoseScreenSprites/mainMenuButton.png")));
        mainMenuButton.setOnAction(e -> {
            MainMenuScreen menu = new MainMenuScreen();
            try {
                menu.start(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });

        return mainMenuButton;
    }
}