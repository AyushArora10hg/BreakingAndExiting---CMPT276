package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.AnimationHandler;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * TrackEnemy defines the enemies that move on a set track.
 *
 * @author Aidan de Vaal
 * @version 1.1
 */
public class TrackEnemy extends Enemy {

    private int spawnX;
    private int spawnY;
    private AnimationHandler animationHandler;

    /**
     * Constructor for the enemy. Sets height, width, position, and images for the enemy.
     * @param x set x-position of the enemy
     * @param y set y-position of the enemy
     * @param width set dynamic width of the enemy
     * @param height set dynamic height of the enemy
     */
    public TrackEnemy(int x, int y, int width, int height) {
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
        while (x % 5 != 0) {
            x++;
        }
        while (y % 5 != 0) {
            y--;
        }
        this.setPosition(x, y);
        this.physicalRepresentation = new ImageView();
        render(new Image("file:src/Sprites/GuardSprites/guard-left.png"));
        this.spawnX = x;
        this.spawnY = y;
        this.audioFile = new File("src/Sounds/LoseScreen.wav");
    }

    /**
     * Method for the enemy's track movement, TrackEnemy follows a movement pattern based around their spawn point.
     * @param character to maintain abstraction, unused in this context.
     */
    public void start(MainCharacter character) {
        if (this.getPosition()[0] == this.spawnX && (this.getPosition()[1] < this.spawnY + 100 || this.getPosition()[1] == this.spawnY)) {
            this.render(animationHandler.setLeft());
            this.move(0, 5);
        } else if (this.getPosition()[0] < this.spawnX + 100 && this.getPosition()[1] == this.spawnY + 100) {
            this.render(animationHandler.setRight());
            this.move(5, 0);
        } else if (this.getPosition()[0] == this.spawnX + 100 && this.getPosition()[1] > this.spawnY) {
            this.render(animationHandler.setRight());
            this.move(0, -5);
        } else if (this.getPosition()[0] > this.spawnX && this.getPosition()[1] == this.spawnY) {
            this.render(animationHandler.setLeft());
            this.move(-5, 0);
        }

    }

}
