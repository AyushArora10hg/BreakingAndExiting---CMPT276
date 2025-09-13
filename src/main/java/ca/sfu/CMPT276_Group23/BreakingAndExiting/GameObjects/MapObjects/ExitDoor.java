package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * ExitDoor represents object door to exit level, allows main character to end game
 *
 * @author lacey swamy
 * @version 1.0
 */
public class ExitDoor extends MapObject {

    private Boolean doorOpen;
    private static ExitDoor instance;

    /**
     * Default private constructor.
     */
    private ExitDoor(){
        super();
        this.doorOpen=false;
        this.physicalRepresentation = new ImageView();
        this.physicalRepresentation.setImage(new Image("file:src/Sprites/ObjectSprites/exitDoor.png"));
        this.physicalRepresentation.setSmooth(false);
        this.physicalRepresentation.setFitHeight(100);
        this.physicalRepresentation.setFitWidth(100);
    }
    /**
     * Implements singleton method to create/get ExitDoor
     *
     * @return the ExitDoor object
     */
    public static ExitDoor getInstance(){
        if (instance==null){
            instance=new ExitDoor();
        }
        return instance;
    }
    /**
     * Getter method of doorOpen boolean variable
     *
     * @return true if door is open, false if door is closed
     */
    public Boolean getDoorOpen(){
        return this.doorOpen;

    }

    /**
     * Setter method of doorOpen boolean variable, updates image
     *
     * @param doorOpens to set true or false
     */
    public void setDoorOpen(boolean doorOpens){
        if (doorOpens) {
            this.doorOpen = true;
            this.physicalRepresentation.setImage(new Image("file:src/Sprites/ObjectSprites/exitDoorOpen.png"));
            this.physicalRepresentation.setSmooth(false);
            this.physicalRepresentation.setFitHeight(100);
            this.physicalRepresentation.setFitWidth(100);
        }
        else {
            this.doorOpen=false;
            this.physicalRepresentation.setImage(new Image("file:src/Sprites/ObjectSprites/exitDoor.png"));
            this.physicalRepresentation.setSmooth(false);
            this.physicalRepresentation.setFitHeight(100);
            this.physicalRepresentation.setFitWidth(50);
        }

    }

}
