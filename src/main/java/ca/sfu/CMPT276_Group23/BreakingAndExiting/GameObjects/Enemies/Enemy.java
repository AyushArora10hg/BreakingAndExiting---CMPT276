package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.GameObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Enemy is an abstract class that defines the methods to be used by each of the enemies.
 *
 * @author Aidan de Vaal
 * @version 1.0
 */
public abstract class Enemy extends GameObject {

    /**
     * Speed variable of enemies (Currently unused).
     */
    protected double speed;
    /**
     * Boolean of whether an enemy is visible or not.
     */
    protected boolean isVisible = true;
    /**
     * Audio file relating to enemies.
     */
    protected File audioFile;

    /**
     * Default constructor of enemy class, calls game object default constructor.
     */
    public Enemy(){
        super();
    }

    /**
     * Method to move the enemy's position and update their image location to match.
     * @param x X-coordinate of the enemy.
     * @param y Y-coordinate of the enemy.
     */
    public void move(int x, int y){
        physicalRepresentation.setLayoutX(physicalRepresentation.getLayoutX() + x);
        physicalRepresentation.setLayoutY(physicalRepresentation.getLayoutY() + y);
        this.setPosition(this.position[0] + x, this.position[1] + y);
    };

    /**
     * Abstract start method implemented by enemy child classes
     *
     * @param character pass in the character to modify the player's score.
     */
    public abstract void start(MainCharacter character);

    /**
     * Handle collision with the enemy, for followEnemy, collision is game-ending.
     * The function returns a boolean "lost" to tell the level class (running gameLoop)
     * to end the game.
     *
     * @param character pass in the character to modify the player's score
     * @return A boolean informing the level of a collision.
     */
    public boolean handleCollision(MainCharacter character) {
        if (this.isVisible) {
            character.setScore(0);
            this.isVisible = false;
            this.playSound();
        }
        return true;
    }

    /**
     * Sound controller for the enemy collision effects.
     */
    public void playSound(){
        SoundPlayer enemyPlayer = new SoundPlayer(audioFile);
        enemyPlayer.play();
    };

}