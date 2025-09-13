package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;


import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.scene.image.Image;
import java.io.File;

/**
 * Represents a regular reward in the game, providing a specific reward value to the
 * main character upon collision. This reward includes an image and sound effect.

 * @author Ayush Arora
 * @version 1.0
 */

public class RegularReward extends Reward {

    /** The amount player receives after collecting this object **/
    protected int rewardValue;

    /**
     * Constructs a {@code RegularReward} with specified position, dimensions, and
     * initializes its image and sound properties.
     *
     * @param x      the x-coordinate of the reward's position.
     * @param y      the y-coordinate of the reward's position.
     * @param width  the width of the reward's visual representation.
     * @param height the height of the reward's visual representation.
     */
    public RegularReward(int x, int y, int height, int width) {

        super(x, y, height, width);
        this.imgPath = "file:src/Sprites/RewardSprites/reward.png";
        this.audioFile = new File("src/Sounds/RegularReward.wav");
        this.isVisible = true;
        render(new Image(imgPath));

        this.rewardValue = 100; // magic number
    }

    /**
     * Sets the reward value, which determines how much score the character gains upon collecting this reward.
     *
     * @param rewardValue the value to set for this reward.
     */
    public void setRewardValue(int rewardValue) {

        this.rewardValue = rewardValue;
    }

    /**
     * Retrieves the reward value of this {@code RegularReward}.
     *
     * @return the reward value of this reward.
     */
    public int getRewardValue() {

        return rewardValue;
    }

    /**
     * Handles the collision between the main character and the reward.
     * Plays a sound and increases the character's score by the reward value.
     *
     * @param character the {@code MainCharacter} instance that collects the reward.
     */
    @Override
    public void handleCollision(MainCharacter character) {

        this.playSound();
        character.addToScore(rewardValue);
    }




}
