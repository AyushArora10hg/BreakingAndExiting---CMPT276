package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract parent method of all objects within the game.
 */
public abstract class GameObject {
    /**
     * Width of a game object
     */
    protected int width;
    /**
     * Height of a game object
     */
    protected int height;
    /**
     * Array of x and y coordinates of a game object
     */
    protected int[] position;
    /**
     * Used to display a game object on the screen
     */
    protected ImageView physicalRepresentation;

    /**
     * Default constructor of gameObject creates a position array
     * and initializes physical representation.
     */
    public GameObject(){
        int[] position = {0,0};
        this.position = position;
        physicalRepresentation = new ImageView();
    }

    /**
     * Parametrized constructor of gameObject.
     *
     * <p>
     *     Constructor sets the position (x and y), height and width of the
     *     object as well as initializes physical representation.
     * </p>
     *
     *
     * @param x X-coordinate of object
     * @param y Y-coordinate of object
     * @param height Height of object
     * @param width Width of object
     */
    public GameObject(int x, int y, int height, int width){
        int[] position = {0,0};
        this.position = position;
        setPosition(x, y);
        this.width = width;
        this.height = height;
        physicalRepresentation = new ImageView();
    }

    /**
     * Retrieves the current position of the object on the map.
     *
     * @return an array containing the x and y coordinates of the object's position.
     */
    public int[] getPosition(){
        return position;
    }

    /**
     * Sets the position of the object on the map with specified x and y coordinates.
     *
     * @param x the x-coordinate of the object's position.
     * @param y the y-coordinate of the object's position.
     */
    public  void setPosition(int x, int y){
        if(x < 0 || y <0){
            System.out.println("Invalid Position");
            return;
        }
        position[0] = x;
        position[1] = y;
    }

    /**
     * Getter class of the Area of the game object on a map. This method uses the
     * height and width of the physical representation of the game object to return
     * the area of the game object.
     *
     * @return the area of the physical representation of the game object.
     */
    public int getArea(){
        return width*height;
    }

    /**
     * Setter class of the area of the game object.
     *
     * @param height the height of the physical representation of the game object.
     * @param width  the width of the physical representation of the game object.
     */
    public void setArea(int height, int width){
        this.height = Math.abs(height);
        this.width = Math.abs(width);
    }

    /**
     * Render class renders a new image for the physical representation of the
     * game object.
     *
     * @param image the new image to be used as the physical representation of the game object.
     */
    public  void render(Image image){
        if(image == null){
            return;
        }
        physicalRepresentation.setImage(image);
    }
    /**
     * Places the object on the game map by setting its position, dimensions, and visibility.
     * Updates the {@code physicalRepresentation} layout coordinates and dimensions to
     * match the object's position and size on the map.
     */
    public  void appearOnMap(){
        physicalRepresentation.setLayoutX(position[0]);
        physicalRepresentation.setLayoutY(position[1]);
        physicalRepresentation.setFitWidth(width);
        physicalRepresentation.setFitHeight(height);
    }


    /**
     * Returns the width of the game object as an {@code int}.
     *
     * @return an {@code int} representing the width of game object.
     */
    public int getWidth(){
        return width;
    }

    /**
     * Returns the height of the game object as an {@code int}.
     *
     * @return an {@code int} representing the height of game object.
     */
    public int getHeight(){
        return height;
    }

    /**
     * Returns the visual representation of the object as an {@code ImageView}.
     *
     * @return an {@code ImageView} representing the object.
     */
    public ImageView getPhysicalRepresentation () {
        return this.physicalRepresentation;
    }

}





