package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.FollowEnemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.LaserEnemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.TrackEnemy;

/**
 * EnemyFactory to create Enemies following the concrete factory design pattern
 *
 * @author aidandevaal
 * @version 1.0
 */
public class EnemyFactory {


    /**
     * Default constructor of EnemyFactory.
     */
    public EnemyFactory(){

    }
    /**
     * Creates and returns an enemy of specified type - null if type is not recognized
     *
     * @param x x-coordinate of the enemy's position
     * @param y y-coordinate of the enemy's position
     * @param width dynamic width of the enemy
     * @param height dynamic height of the enemy
     * @param type type of the enemy
     * @return enemy using the given x, y, width, height, and type
     */
    public static Enemy createEnemy(int x, int y, int width, int height, String type){
        return switch (type) {
            case "Track" -> new TrackEnemy(x, y, width, height);
            case "Follow" -> new FollowEnemy(x, y, width, height);
            case "Laser" -> new LaserEnemy(x, y, width, height);
            default -> null;
        };
    }
}
