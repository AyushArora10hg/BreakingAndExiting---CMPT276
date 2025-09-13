package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * MainCharacter class represents the functionality of the user controlled character Robby-Bobby.
 *
 * <p>
 *     The MainCharacter class contains implementation for both placing a MainCharacter object
 *     on a map aswell as the physical representaion of that character and the animation required
 *     when the user inputs keys to move the main character.
 * </p>
 *
 *
 *
 * @author Conor Benson
 * @version 1.0
 */

public class MainCharacter extends GameObject {

    private int score;
    private double speed;
    private AnimationHandler animationHandler;

    /**
     * Constructor class for MainCharacter object.
     *
     * <p>
     *     The constructor assigns a score, width and height
     *     as well as arrays containing both left and right movement sprites. The constructor finally
     *     sets up the physical representation of the MainCharacter object on a map by calling appearOnMap.
     * </p>
     *
     * @param x X-coordinate of the mainCharacter.
     * @param y Y-coordinate of the mainCharacter.
     * @param height Height of the mainCharacter.
     * @param width Width of the mainCharacter.
     */
    public MainCharacter(int x, int y, int width, int height) {
        super(x,y,height,width);
        this.score = 0;
        Image [] leftCharacterFrames = new Image[] {
                new Image("file:src/Sprites/CharacterSprites/Bobby-Left-Stationary.png"),
                new Image("file:src/Sprites/CharacterSprites/Bobby-Left-Move1.png"),
                new Image("file:src/Sprites/CharacterSprites/Bobby-Left-Move2 (2).png")
        };

        Image [] rightCharacterFrames = new Image[] {
                new Image("file:src/Sprites/CharacterSprites/Bobby-Right-Stationary.png"),
                new Image("file:src/Sprites/CharacterSprites/Bobby-Right-Move1.png"),
                new Image("file:src/Sprites/CharacterSprites/Bobby-Right-Move2 (2).png")
        };
        this.animationHandler=new AnimationHandler(leftCharacterFrames,rightCharacterFrames);


        while(x%5!=0) {
            x++;
        }
        while(y%5!=0) {
            y--;
        }
        this.setPosition(x,y);
        this.physicalRepresentation = new ImageView();
        render(new Image("file:src/Sprites/CharacterSprites/Robby-Right-Stationary.png"));

        this.appearOnMap();

    }

    /**
     * Move methods used to handle the change of the characters position and character frame
     * when the user presses a direction key.
     *
     * <p>
     *     Method takes in the change in x and y co-ordinate and adds them to the
     *     current x and y co-ordinates,the method then determines which direction the character moved
     *     in and sets the character frame accordingly. Finally, the method calls the changeFrame method
     *     followed by the setCurrentCharacterFrame method to change the frame of the physical representation
     *     of the main character.
     * </p>
     *
     *
     * @param x change in x co-ordinate of the main character
     * @param y change in y co-ordinate of the main character
     */
    public void move(int x, int y) {
        physicalRepresentation.setLayoutX(physicalRepresentation.getLayoutX() + x);
        physicalRepresentation.setLayoutY(physicalRepresentation.getLayoutY() + y);
        this.setPosition(this.position[0] + x, this.position[1] + y);

        if (x < 0){
            this.render(animationHandler.setLeft());
        }
        else if (x > 0){
            this.render(animationHandler.setRight());
        }
        else if (y < 0){
            this.render(animationHandler.setRight());
        }
        else if (y > 0){
            this.render(animationHandler.setLeft());
        }
    }

    /**
     * Getter of the score variable.
     *
     * @return the current value of the main characters score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter of the score variable.
     *
     * @param score the value to be assigned to the score.
     */
    public void setScore(int score) {
        if(score <0){
            System.out.println("Invalid Score");
            this.score = 0;
            return;
        }
        this.score = score;
    }

    /**
     * Add to scores add a double value to the current value of score. Method is used
     * to increments characters score when rewards are picked up.
     *
     * @param score the score value to be added.
     */
    public void addToScore(double score) {
        this.score += score;
    }

    /**
     * Getter of the main characters speed variable (currently unimplemented).
     *
     * @return the current speed of the main character in terms of a double.
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     *Setter of the main characters speed variable (currently unimplemented).
     *
     * @param speed the new speed of the main character's movement.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }


    /**
     * Getter of the animationHandler object.
     *
     * @return MainCharacters animationHandler object.
     */
    public AnimationHandler getAnimationHandler() {
        return animationHandler;
    }

}