package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;

/**
 * MapObjectFactory to create MapObjects using concrete factory design pattern
 *
 * @author lacey swamy
 * @version 1.0
 */
public class MapObjectFactory {

    /**
     * Default constructor of Rewards factory.
     */
    public MapObjectFactory() {
    }

    /**
     * Create a MapObject for inside the map
     * @param x represents x coordinate
     * @param y represents y coordinate
     * @param width represents object's width
     * @param height represents object's height
     * @return MapObject which is a table or plant specific to the parameters
     */
    public static MapObject createMapObject(int x, int y, int width, int height) {
        return new MapObject(x, y, width, height);
    }
}