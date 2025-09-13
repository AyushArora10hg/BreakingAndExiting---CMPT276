package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.ExitDoor;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories.BankLevelFactory;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UserInput.KeyHandler;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;

/**
 * Level class to build the level components on the screen
 *
 * @author Group 23
 * @version 1.1
 */
public class Level {

    private final int LEVEL_DURATION;
    private int remainingTime;
    private final Rectangle2D dimensions;
    private int WORLD_WIDTH;
    private int WORLD_HEIGHT;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private SoundPlayer levelMusicPlayer;
    private CollisionHandler collisionHandler;

    private MainCharacter character;
    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;
    private BankLevelFactory bankLevelFactory;
    private ArrayList<MapObject> barriers;
    private ExitDoor exitDoor;

    private KeyHandler keyHandler;
    private LevelPane levelPane;
    private Timeline levelTimer;
    private HUD hud;
    private boolean paused;
    private Timeline gameLoop;
    private Stage stage;
    private boolean lost;
    private boolean won;

    /**
     * Level class constructor.
     *
     * <p>
     *     Instantiates all fields and objects for the game level including;
     *     WORLD_WIDTH, WORLD_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT, the hud, mainCharacter,
     *     keyHandler, and the bankLevelFactory for creating the level.
     *
     * </p>
     * @param stage stage being passed to level.
     */
    public Level(Stage stage){

        // LEVEL DYNAMICS
        this.LEVEL_DURATION = 120;
        this.remainingTime = LEVEL_DURATION;
        this.stage = stage;
        this.dimensions = Screen.getPrimary().getBounds();
        WORLD_WIDTH = (int) (dimensions.getWidth() * 1.5);
        WORLD_HEIGHT = (int) (dimensions.getHeight() * 1.5);
        SCREEN_WIDTH = (int) dimensions.getWidth();
        SCREEN_HEIGHT = (int) dimensions.getHeight();

        // LEVEL ELEMENTS
        levelPane = new LevelPane(WORLD_WIDTH, WORLD_HEIGHT);
        bankLevelFactory = new BankLevelFactory(WORLD_WIDTH, WORLD_HEIGHT);
        this.hud = new HUD();
        this.character = new MainCharacter((int)(WORLD_WIDTH/25), (int)(WORLD_HEIGHT/40), (int)(WORLD_WIDTH/19.2), (int)(WORLD_HEIGHT/12));
        this.levelMusicPlayer = new SoundPlayer(new File("src/Sounds/game.wav"));
        this.exitDoor = ExitDoor.getInstance();

        // MOVEMENT HANDLING
        keyHandler = new KeyHandler(this);
        collisionHandler = new CollisionHandler(this.character);

        // LEVEL STATUS CONTROLLERS
        this.paused = false;
        this.lost = false;
        this.won = false;


    }

    /**
     * Getter method for the main character.
     *
     * @return MainCharacter the object of the character.
     */
    public MainCharacter getCharacter () {

        return this.character;
    }

    /**
     * Getter method of world dimensions.
     *
     * @return an Array of integers representing world width, world height
     */
    public int[] getDimensions () {
        return new int []{WORLD_WIDTH, WORLD_HEIGHT};
    }

    /**
     * Getter method of barriers in level.
     *
     * @return ArrayList of MapObjects barriers on map
     */
    public ArrayList<MapObject> getBarriers() {
        return this.barriers;
    }

    /**
     * Getter method of rewards in level
     *
     * @return ArrayList of Rewards on map
     */
    public ArrayList<Reward> getRewards() {
        return this.rewards;
    }

    /**
     * Getter method of enemies in level
     * @return ArrayList of enemies on map
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Getter method of remaining time of game.
     *
     * @return integer representing seconds left of game
     */
    public int getRemainingTime () {
        return remainingTime;
    }

    /**
     * Getter method of boolean if player lost game
     *
     * @return true if game is lost, false is still playing
     */

    public boolean getLost(){
        return this.lost;
    }

    /**
     *Setter method of boolean if player lost game
     *
     * @param lost represents true is game should be lost, false is still playing
     */
    public void setLost(Boolean lost){
        this.lost = lost;
    }

    /**
     * Getter method of boolean if player won game
     *
     * @return true if game is won, false if still playing
     */
    public boolean getWon(){
        return this.won;
    }

    /**
     * Setter method of boolean if player won game
     *
     * @param won represents true if game is won, false if still playing
     */
    public void setWon(Boolean won){
        this.won = won;
    }

    /**
     * Getter method of boolean if the game is paused
     *
     * @return true if paused, false if still playing
     */
    public boolean isPaused() {

        return paused;
    }
    /**
     * Getter of stackPane object of Level. Use primarily for testing purposes.
     *
     * @return Level's levelPane.
     */
    public LevelPane getLevelPane(){
        return levelPane;
    }

    /**
     * Getter of Level's exitDoor, Used primarily for testing purposes.
     *
     * @return The exit door of the level.
     */
    public ExitDoor getExitDoor(){
        return exitDoor;
    }

    /**
     * Consistently checks score to test if door should be open or closed and sets it to appropriate state
     */
    public void checkDoorOpen(){
        if (hud.getScoreCounter()>=1000){
            barriers.remove(exitDoor);
            exitDoor.setDoorOpen(true);
        }
        else if (hud.getScoreCounter()<=1000) {
            barriers.add(exitDoor);
            exitDoor.setDoorOpen(false);
            setWon(false);
        }
    }

    /**
     * Checks if player has run out of time and updates lose variable to invoke lose sequence
     */
    public void checkRemainingTime () {

        if (remainingTime == 0)
            lost = true;
    }
    /**
     * Checks if player has a negative score and updates lose variable to invoke lose sequence
     */
    public void checkNegativeScore () {

        if (character.getScore() < 0)
            lost = true;
    }

    /**
     * Starts enemy movement and enemy following character procedures
     */
    private void startEnemies(MainCharacter mainCharacter) {
        for (Enemy enemy : enemies) {
            enemy.start(mainCharacter);
        }
    }

    /**
     * <p>
     *     Starts collision detection to see if rewards and
     *     enemies are overlapping player and handle their collision
     * </p>
     */
    private void startCollisionDetection() {

        AnimationTimer collisionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                collisionHandler.checkCollision_rewards(rewards);
                won = collisionHandler.checkExitDoorCollision(exitDoor);
                lost = collisionHandler.checkCollisionEnemies(enemies, barriers);
            }
        };
        collisionTimer.start();
    }

    /**
     * Starts level time to set up the timeline for countdown
     */
    private void startLevelTimer() {
        // Set up the Timeline for the countdown
        levelTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingTime--;
        }));

        levelTimer.setCycleCount(LEVEL_DURATION);
        levelTimer.play();
    }

    /**
     * <p>
     *     Starts game loop that consistently process user input,
     *     updates camera, checks variables, updates the HUD, and
     *     determines if the win or lose sequence should be invoked
     * </p>
     *
     * @param character represents the player, game character
     * @param keyHandler represents the user input manager
     */
    public void startGameLoop(MainCharacter character, KeyHandler keyHandler) {
        if (!paused && !lost) {
            gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.075), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    keyHandler.processKey();
                    if(lost) {
                        levelMusicPlayer.stop();
                        gameLoop.stop();
                        LoseScreen loseScreen = new LoseScreen(stage);
                        stage.setScene(loseScreen.getScene());
                    }
                    if(won){
                        levelMusicPlayer.stop();
                        gameLoop.stop();
                        WinScreen WinScreen = new WinScreen(stage,character.getScore());
                        stage.setScene(WinScreen.getWinScene());
                    }
                    updateCamera();
                    startEnemies(character);
                    startCollisionDetection();
                    checkRemainingTime();
                    checkNegativeScore();
                    checkDoorOpen();
                    hud.update();
                }
            }));
            gameLoop.setCycleCount(Timeline.INDEFINITE);
            gameLoop.play();
        }

    }

    /**
     * Updates the camera view relative to the player's location and moves the screen
     */
    private void updateCamera() {
        double characterX = character.getPosition()[0];
        double characterY = character.getPosition()[1];

        // Calculate camera bounds
        double cameraX = Math.max(0, Math.min(characterX - SCREEN_WIDTH / 2, WORLD_WIDTH - SCREEN_WIDTH));
        double cameraY = Math.max(0, Math.min(characterY - SCREEN_HEIGHT / 2, WORLD_HEIGHT - SCREEN_HEIGHT));

        // Translate the level pane to center the camera on the character
        levelPane.setTranslateX(-cameraX);
        levelPane.setTranslateY(-cameraY);
    }
    /**
     * <p>
     *     Starts level game; adds objects to levelPane,
     *     start scene, key handler game loop, music, etc
     * </p>
     */
    public void start() {

        stage.setTitle("Level");
        hud.startHUD(this);


        barriers = BankLevelFactory.setMapObjects();
        levelPane.addMapObjects(barriers);

        exitDoor= BankLevelFactory.setExitDoor();
        levelPane.addExitDoor(exitDoor);
        barriers.add(exitDoor);

        enemies = BankLevelFactory.spawnEnemies();
        for(Enemy enemy : enemies){
            levelPane.addEnemy(enemy);
        }
        startEnemies(character);

        rewards = BankLevelFactory.populateRewards();
        levelPane.addRewards(rewards);

        levelPane.addMainCharacter(character);

        StackPane root = new StackPane(levelPane, hud);

        // Position the HUD at the top of the StackPane
        Scene levelScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Key event handler for moving the character
        levelScene.setOnKeyPressed(keyHandler::keyPressed);
        levelScene.setOnKeyReleased(keyHandler::keyReleased);

        stage.setScene(levelScene);
        stage.setFullScreen(true);
        stage.show();

        startGameLoop(character, keyHandler);
        startLevelTimer();
        levelMusicPlayer.play();

    }

    /**
     * Allows player to pause the game, pauses gameLoop, timers, music, and rewards from spawning
     */
    public void pauseGame() {

        paused = !paused;
        if (paused) {
            gameLoop.pause();
            levelTimer.pause();
            levelMusicPlayer.stop();
        }
        else {
            gameLoop.play();
            levelTimer.play();
            levelMusicPlayer.play();
        }

    }


}
