package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects;

import javafx.scene.image.Image;

/**
 *
 *  AnimationHandler classes manages animation logic of objects that require the look of movement
 *
 * @author Group 23
 * @version 1.0
 *
 */
public class AnimationHandler {
    private Image[] leftFrames;
    private Image[] rightFrames;
    private Image[] currentFrames;
    private int frameIndex;

    /**
     * Constructor for objects with image frames
     * @param LEFT represents image array of left facing frames
     * @param RIGHT represents image array of right facing frames
     */
    public AnimationHandler(Image[] LEFT, Image[] RIGHT){
        leftFrames=LEFT;
        rightFrames=RIGHT;
    }
    /**
     * Getter for leftFrames.
     *
     * @return Image array containing left frames.
     */
    public Image[] getLeftFrames() {
        return leftFrames;
    }

    /**
     * Getter for rightFrames.
     *
     * @return Image array containing right frames.
     */

    public Image[] getRightFrames() {
        return rightFrames;
    }


    /**
     * Getter for the characters current Image frame array.
     *
     * @return The array the characters image frames is currently coming from.
     */
    public Image[] getCurrentFrames() {
        return currentFrames;
    }

    /**
     * Method for animation logic to increment the frames index
     */
    public void nextFrame() {
        frameIndex = (frameIndex + 1) % currentFrames.length;
    }

    /**
     * Method to provide a frame at specific image for updating the object's image
     * @param i integer representing the index
     * @return the Image at index i
     */
    public Image updateFrame(int i) {
        return currentFrames[i];
    }

    /**
     * Method for when the object turns right, sets currentFrames accordingly and updates current frame
     * @return the new current Image
     */
    public Image setRight(){
        currentFrames = rightFrames;
        nextFrame();
        return (updateFrame(frameIndex));
    }
    /**
     * Method for when the object turns left, sets currentFrames accordingly and updates current frame
     * @return the new current Image
     */
    public Image setLeft(){
        currentFrames = leftFrames;
        nextFrame();
        return (updateFrame(frameIndex));
    }
    /**
     * Setter for the current animation frame of the physical representation of the main
     * character. Method takes in an indices relating to which frame to be displayed, then set the
     * physical representation of the character to the that index of the currentCharacterFrames array,
     *
     * @param i index relating to a character frame
     * @return The current from of an animated object.
     */

    public Image setCurrentFrame(int i) {
        return currentFrames[i];
    }
}
