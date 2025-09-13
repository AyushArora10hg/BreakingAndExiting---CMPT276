package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.AnimationHandler;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

/**
 * FollowEnemy defines the enemies that follow the player.
 *
 * @author Aidan de Vaal
 * @version 1.1
 */
public class FollowEnemy extends Enemy {

    private AnimationHandler animationHandler;

    /**
     * Constructor for the enemy. Sets height, width, position, and images for the enemy.
     * @param x set x-position of the enemy
     * @param y set y-position of the enemy
     * @param width set dynamic width of the enemy
     * @param height set dynamic height of the enemy
     */
    public FollowEnemy(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        Image [] leftEnemyFrames = new Image[] {
                new Image("file:src/Sprites/GuardSprites/guard-left.png"),
                new Image("file:src/Sprites/GuardSprites/guard-left1.png"),
                new Image("file:src/Sprites/GuardSprites/guard-left2.png")
        };

        Image [] rightEnemyFrames = new Image[] {
                new Image("file:src/Sprites/GuardSprites/guard-right.png"),
                new Image("file:src/Sprites/GuardSprites/guard-right1.png"),
                new Image("file:src/Sprites/GuardSprites/guard-right2.png")
        };
        this.animationHandler=new AnimationHandler(leftEnemyFrames, rightEnemyFrames);
        while(x%5!=0) {
            x++;
        }
        while(y%5!=0) {
            y--;
        }
        this.setPosition(x, y);
        this.physicalRepresentation = new ImageView();
        render(new Image("file:src/Sprites/GuardSprites/guard-left.png"));
        this.audioFile = new File("src/Sounds/LoseScreen.wav");
    }

    /**
     * Method for the enemy to follow the player taking the MainCharacter as a parameter.
     * The enemy only moves in one direction at a time, this is for fairness to the player, so that the player CAN escape.
     * The enemy moves towards the player in the x direction until the enemy's x equals the player's x,
     * it then moves towards the player in the y direction.
     *
     * @param character the player, to track their location and move the enemies accordingly.
     */
    @Override
    public void start(MainCharacter character) {
        if (character.getPosition()[0] > this.getPosition()[0]) {
            this.render(animationHandler.setRight());
            this.move(5, 0);
        } else if (character.getPosition()[0] < this.getPosition()[0]) {
            this.render(animationHandler.setLeft());
            this.move(-5, 0);
        } else if (character.getPosition()[1] > this.getPosition()[1]) {
            this.render(animationHandler.setLeft());
            this.move(0, 5);
        } else if (character.getPosition()[1] < this.getPosition()[1]) {
            this.render(animationHandler.setRight());
            this.move(0, -5);
        }
    }
}