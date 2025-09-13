package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.RegularReward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.SpecialReward;

/**
 * RewardsFactory is a factory class responsible for creating different types of rewards.
 * Depending on the specified type, this factory produces instances of either
 * RegularReward or SpecialReward.
 *
 * @author Ayush Arora
 * @version 1.0
 */
public class RewardsFactory {

    /**
     * Default constructor of Rewards factory.
     */
    public RewardsFactory(){

    }
    /**
     * Creates and returns a reward object based on the specified type.
     *
     * @param type   The type of reward to create ("Regular" or "Special").
     * @param x      The x-coordinate of the reward's position.
     * @param y      The y-coordinate of the reward's position.
     * @param width  The width of the reward.
     * @param height The height of the reward.
     * @return A new instance of RegularReward or SpecialReward depending on the type.
     *         Returns null if the type is not recognized.
     */
    public static Reward createReward(String type, int x, int y, int height, int width) {

        if (type.equals("Regular"))
            return new RegularReward(x, y, height, width);
        else if (type.equals("Special"))
            return new SpecialReward(x, y, height, width);
        return null;
    }


}
