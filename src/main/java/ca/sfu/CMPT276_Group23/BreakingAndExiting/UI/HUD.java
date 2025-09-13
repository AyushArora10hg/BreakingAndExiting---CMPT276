package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;


/**
 * Represents the Heads-Up Display (HUD) for the game, showing the player's score,
 * pause state, and remaining time. The HUD is an HBox that displays various labels
 * and handles user interactions for pausing the game.
 *
 * @author Ayush Arora
 * @version 1.0
 */

public class HUD extends HBox {

    private int scoreCounter;
    private double timerCounter;
    private Font myFont;
    private Level level;
    private Label score;
    private Button pause;
    private Label timeLabel;

    /**
     * Default constructor of HUD.
     */
    public HUD(){
    }
    /**
     * Initializes the HUD with the given level, setting up the score, pause label,
     * and timer display. Configures the appearance and layout of the HUD.
     *
     * @param level the current level associated with the HUD.
     */
    public void startHUD(Level level) {

        myFont = Font.loadFont("file:src/main/resources/ca/sfu/CMPT276_Group23/BreakingAndExiting/Minecraft.ttf", 30);
        this.scoreCounter = level.getCharacter().getScore();
        this.level = level;

        this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(50);
        this.setAlignment(Pos.TOP_CENTER);

        format_pause();
        format_score();
        format_timeLabel();
        this.getChildren().add(score);
        this.getChildren().add(pause);
        getChildren().add(timeLabel);

    }

    /**
     * Retrieves the current score counter value.
     *
     * @return The score counter.
     */
    public double getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Retrieves the current timer counter value.
     *
     * @return The timer counter.
     */
    public double getTimerCounter() {
        return this.timerCounter;
    }

    /**
     * Increases the score counter by the specified amount.
     *
     * @param score the amount to add to the score counter.
     */
    public void updateScoreBy(double score) {
        this.scoreCounter += score;
    }

    /**
     * Updates the score and timer display on the HUD. Changes the score label color
     * based on whether the score increased or decreased since the last update.
     */
    public void update() {

        int prevScore = scoreCounter;
        scoreCounter = level.getCharacter().getScore();
        PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
        transition.setOnFinished(_ -> {
            score.setTextFill(Color.WHITE);
        });

        if (prevScore < scoreCounter) {
            score.setTextFill(Color.DARKGREEN);
            transition.play();
        } else if (prevScore > scoreCounter) {
            score.setTextFill(Color.DARKRED);
            transition.play();
        }

        score.setText("SCORE : " + scoreCounter);
        int minutes = level.getRemainingTime() / 60;
        int seconds = level.getRemainingTime() % 60;
        timeLabel.setText("Time Left : " + (minutes) + ":" + String.format("%02d", seconds));

    }

    /**
     * Formats and initializes the score label with the current score.
     */
    private void format_score() {

        score = new Label("SCORE : " + scoreCounter);
        score.setFont(myFont);
        score.setTextFill(Color.WHITE);
    }

    /**
     * Formats and initializes the pause button. Pauses the level upon being pressed.
     * Provides visual signs upon mouse been brought in proximity.
     */
    private void format_pause() {

        ImageView img = new ImageView(new Image("file:src/Sprites/HudSprites/playPause.png"));
        img.setFitHeight(50);
        img.setFitWidth(50);
        pause = new Button();
        pause.setGraphic(img);
        pause.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        pause.setFocusTraversable(false);
        pause.setPrefHeight(50);
        pause.setPrefWidth(50);
        pause.setOnAction(_ -> {
            level.pauseGame();
        });
        pause.setOnMouseEntered(_ -> {
            img.setScaleX(1.2);
            img.setScaleY(1.2);
            pause.setScaleX(1.2);
            pause.setScaleY(1.2);
        });
        pause.setOnMouseExited(_ -> {
            img.setScaleX(1);
            img.setScaleY(1);
            pause.setScaleX(1);
            pause.setScaleY(1.1);
        });

    }

    /**
     * Formats and initializes the timer label with the default time display.
     */
    private void format_timeLabel() {

        timeLabel = new Label("TIME : 3:00");
        timeLabel.setFont(myFont);
        timeLabel.setTextFill(Color.WHITE);

    }

}
