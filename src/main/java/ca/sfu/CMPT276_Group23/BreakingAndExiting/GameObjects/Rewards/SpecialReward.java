package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.LevelPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

/**
 * Represents a special type of reward in the game that provides a higher reward value
 * than regular rewards. It has a defined visibility duration and can be configured to
 * appear on the map for a limited time.

 * @author Ayush Arora
 * @version 1.0
 */
public class SpecialReward extends RegularReward {

    /** Represents time (in sec) for which this reward stays visible on map **/
    final int visibilityTime = 15;

    /**
     * Constructs a {@code SpecialReward} at the specified coordinates with given dimensions.
     * Initializes the reward with a higher reward value and sets it to be initially invisible.
     *
     * @param x      the x-coordinate of the special reward's position.
     * @param y      the y-coordinate of the special reward's position.
     * @param width  the width of the special reward's visual representation.
     * @param height the height of the special reward's visual representation.
     */
    public SpecialReward(int x, int y, int height, int width) {

        super(x,y,height,width);
        this.imgPath = "file:src/Sprites/RewardSprites/specialReward.png";
        this.audioFile = new File("src/Sounds/SpecialReward.wav");
        this.isVisible = false;

        this.rewardValue = 300;

    }

    /**
     * Retrieves the visibility duration for the special reward.
     *
     * @return the time in seconds that the special reward is visible.
     */

    public int getVisibilityTime() {
        return visibilityTime;
    }

    /**
     * <p>
     *     Starts random spawn of special rewards, starts a
     *     timeline so they are on map for selected periods
     * </p>
     *
     * @param pane LevelPane that special reward will be added to.
     */
    public void specialRewardTimeline(LevelPane pane) {

        Timeline specialRewardTimeline = new Timeline(new KeyFrame(Duration.seconds(20), _ -> {
            if (!this.isVisible) {
                int[] pos = pane.randomPosGenerator(this);
                this.setPosition(pos[0],pos[1]);
                this.isVisible = true;
                this.appearOnMap();
                Timeline removeReward = new Timeline(new KeyFrame(Duration.seconds(this.visibilityTime), _ -> {
                    this.removeFromMap();
                    this.isVisible = false;
                }));
                removeReward.setCycleCount(1);
                removeReward.play();
            }
        }));

        specialRewardTimeline.setCycleCount(Timeline.INDEFINITE);
        specialRewardTimeline.play();
    }

    /**
     * Appear on map method of special reward.
     */
    @Override
    public void appearOnMap() {
        super.appearOnMap();
        render(new Image(imgPath));
    }
}
