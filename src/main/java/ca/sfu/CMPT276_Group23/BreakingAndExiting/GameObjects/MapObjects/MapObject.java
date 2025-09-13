package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * MapObject represents barriers on map that the MainCharacter cannot overlap
 *
 * @author lacey swamy
 * @version 1.0
 */
public class MapObject extends GameObject {

    private boolean visibleOnMap;
    private File audioFile;

    /**
     * Default constructor, creates default table object
     */
    public MapObject(){
        super();
        this.visibleOnMap=true;
        this.physicalRepresentation = new ImageView();
        this.physicalRepresentation.setImage(new Image("file:src/Sprites/ObjectSprites/table1.png"));
        this.physicalRepresentation.setSmooth(false);
        this.height=25;
        this.width=25;
        audioFile = new File("src/Sounds/Barrier.wav");

    }

    /**
     * Constructor for MapObjects around the map
     *
     * @param x represents x coordinate.
     * @param y represents y coordinate.
     * @param width represents width of object.
     * @param height represents height of object.
     */
    public MapObject(int x, int y, int width, int height){
        super();
        this.visibleOnMap=true;
        this.physicalRepresentation = new ImageView();
        this.physicalRepresentation.setImage(new Image("file:src/Sprites/ObjectSprites/table1.png"));
        this.physicalRepresentation.setSmooth(false);
        this.physicalRepresentation.setFitHeight(height);
        this.physicalRepresentation.setFitWidth(width);
        this.width=width;
        this.height=height;
        setPosition(x, y);
    }
    /**
     * Getter method for visibleOnMap Boolean variable
     *
     * @return whether objects is visible (true) or not
     */
    public boolean getVisibleOnMap(){
        return this.visibleOnMap;
    }

    /**
     * Setter method to set object visible
     *
     * @param visible represents if object should be visible (true) or not
     */
    public void setVisibility(boolean visible){
        this.visibleOnMap=visible;
    }

    @Override
    public void appearOnMap() {
        super.appearOnMap();
        this.visibleOnMap=true;
    }

    
}
