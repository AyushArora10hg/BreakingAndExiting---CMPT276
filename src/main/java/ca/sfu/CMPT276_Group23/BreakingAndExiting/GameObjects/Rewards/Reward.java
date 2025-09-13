package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.GameObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.Sound.SoundPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * The {@code Reward} class represents a reward object in the game.
 * Rewards are collectible objects that interact with the main character.
 * This class is abstract and serves as a base for specific reward types,
 * providing methods for handling visibility, collision, and sound effects.
 *
 * @author Ayush Arora
 * @version 2.0
 */

public abstract class Reward extends GameObject {


    /** A flag indicating whether the reward is currently visible on map. */
    protected boolean isVisible;

    /** The audio file associated with the reward. */
    protected File audioFile;

    /** The path to the image representing the reward. */
    protected String imgPath;

    /**
     * Parametrized constructor of reward. Calls the gameObjects parametrized constructor.
     *
     * @param x X-coordinate of the reward.
     * @param y Y-coordinate of the reward.
     * @param height Height of the reward.
     * @param width Width of the reward.
     */
    public Reward(int x, int y, int height, int width){
        super(x,y,height,width);
    }


    /**
     * Sets the visibility of the reward.
     *
     * @param visibility {@code true} to make the reward visible, {@code false} otherwise.
     */
    public void setVisibility (boolean visibility) {

        this.isVisible = visibility;
    }

    /**
     * Retrieves the visibility status of the reward.
     *
     * @return {@code true} if the reward is visible, {@code false} otherwise.
     */
    public boolean getVisibility () {

        return this.isVisible;
    }

    /**
     * Places the reward on the game map by setting its position, dimensions, and visibility.
     * Updates the {@code physicalRepresentation} layout coordinates and dimensions to
     * match the reward's position and size on the map.
     */
    @Override
    public void appearOnMap() {
        super.appearOnMap();
        this.setVisibility(true);
    }

    /**
     * Removes the reward from the game map by setting its visibility to {@code false}
     * and removing its image representation.
     */
    public void removeFromMap() {

        this.setVisibility(false);
        physicalRepresentation.setImage(null);
    };

    /**
     * Plays the sound associated with the reward when collected.
     * Attempts to load the audio file, then initializes and starts playback.
     * Catches exceptions for file not found, unsupported audio format,
     * or unavailable audio line, logging an appropriate error message in each case.
     */

    public void playSound() {

        SoundPlayer rewardsPlayer = new SoundPlayer(audioFile);
        rewardsPlayer.play();

    }

    /**
     * Defines the behavior when the reward collides with the main character.
     * This method should be implemented to specify actions, such as
     * updating the character's score or triggering other effects.
     *
     * @param character the {@code MainCharacter} instance that collides with the reward.
     */
    public abstract void handleCollision (MainCharacter character);

}
