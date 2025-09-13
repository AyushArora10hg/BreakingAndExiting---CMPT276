package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.scene.image.Image;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * LaserEnemy is an enemy that if the player walks into the x,y bounds of the laser, they lose score (stationary enemy).
 *
 * @author Aidan de Vaal
 * @version 1.1
 */
public class LaserEnemy extends Enemy {

    private Image[] currentEnemyFrames;

    /**
     * Constructor for the enemy. Sets height, width, position, and images for the enemy.
     * @param x set x-position of the enemy
     * @param y set y-position of the enemy
     * @param width set dynamic width of the enemy
     * @param height set dynamic height of the enemy
     */
    public LaserEnemy(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        currentEnemyFrames = new Image[]{
                new Image("file:src/Sprites/LaserSprites/lasers.png"),
                new Image("file:src/Sprites/LaserSprites/lasers1.png"),
                new Image("file:src/Sprites/LaserSprites/lasers2.png"),
                new Image("file:src/Sprites/LaserSprites/lasers3.png")
        };
        this.physicalRepresentation.setImage(new Image("file:src/Sprites/GuardSprites/lasers.png"));
        this.physicalRepresentation.setSmooth(false);
        this.physicalRepresentation.setFitWidth(this.width);
        this.physicalRepresentation.setFitHeight(this.height);
        this.audioFile = new File("src/Sounds/Laser.wav");
        this.setPosition(x, y);
        this.isVisible = true;
    }

    /**
     * Method is unused for LaserEnemy.
     */
    @Override
    public void start(MainCharacter character) {
        if (this.isVisible) {
            int rand = new Random().nextInt(4);
            this.physicalRepresentation.setImage(currentEnemyFrames[rand]);
        } else {
            this.physicalRepresentation.setImage(null);
        }
    }

    /**
     * Handle collision between the MainCharacter and the enemy, for LaserEnemy, collision causes loss of score.
     * The function returns a boolean "lost" to tell the level class (running gameLoop)
     * NOT to end the game.
     * @param character the player so that the player's score can be accessed
     * @return whether or not the game is lost (true to trigger game-end, false to continue)
     */
    @Override
    public boolean handleCollision(MainCharacter character) {
        if (this.isVisible) {
            if (character.getScore() > 0){
                character.addToScore(-50);
                this.playSound();
                this.physicalRepresentation.setImage(null);
                this.isVisible = false;
            } else { return true; }
        }
        return false;
    }

}