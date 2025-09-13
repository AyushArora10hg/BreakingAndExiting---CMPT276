package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

/**
 * AbstractLevelFactories serves as a base class for level factories in the game,
 * providing common attributes and initialization for level dimensions.
 * This class stores the width and height for different levels,
 * which can be utilized by specific level factories that extend this class.
 *
 * @author Group 23
 * @version 1.0
 */

public class AbstractLevelFactories {

    /**
     * Width of the level.
     */
    protected static int width;
    /**
     * Height of the level.
     */
    protected static int height;

    /**
     * Constructs an AbstractLevelFactories instance, initializing the level dimensions.
     *
     * @param w The width of the level.
     * @param h The height of the level.
     */
    public AbstractLevelFactories(int w, int h) {
        width = w;
        height = h;
    }
}
