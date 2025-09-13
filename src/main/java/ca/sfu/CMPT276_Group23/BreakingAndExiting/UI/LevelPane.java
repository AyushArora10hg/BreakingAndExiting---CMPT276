package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.GameObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.ExitDoor;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.SpecialReward;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for creating different levelPanes and defining methods to physically
 * apply/add objects to the levelPane.
 *
 * @author Group 23
 * @version 2.0
 */

public class LevelPane extends Pane {

    /**
     * Default constructor of LevelPane.
     */
    public LevelPane(){
    }
    /**
     * Define height and width of background/level using the screen width and height as inputs.
     *
     * @param WORLD_WIDTH The width of the background image.
     * @param WORLD_HEIGHT The height of the background image.
     */
    public LevelPane(int WORLD_WIDTH, int WORLD_HEIGHT) {
        this.setPrefSize(WORLD_WIDTH, WORLD_HEIGHT);
        ImageView bg = new ImageView(new Image("file:src/Sprites/BankSprites/EvenMoreExpanded-tile-floor.png"));
        bg.setFitWidth(WORLD_WIDTH);
        bg.setFitHeight(WORLD_HEIGHT);
        this.getChildren().add(bg);
    }

    /**
     * Add the MainCharacter to the levelPane and position its image/representation.
     *
     * @param mainCharacter The mainCharacter object to be added to the levelPane.
     */
    public void addMainCharacter(MainCharacter mainCharacter) {
        mainCharacter.appearOnMap();
        getChildren().add(mainCharacter.getPhysicalRepresentation());
    }

    /**
     * Add the MapObjects to the levelPane and position their image/representation.
     *
     * @param barriers ArrayList of barriers to be added to the levelPane.
     */
    public void addMapObjects(ArrayList<MapObject> barriers) {
        for (MapObject mapObject: barriers){
            mapObject.appearOnMap();
            getChildren().add(mapObject.getPhysicalRepresentation());
        }

    }

    /**
     * Add the exit door to the levelPane and position their image/representation.
     *
     * @param exitDoor ExitDoor object to be added to the levelPane.
     */
    public void addExitDoor(ExitDoor exitDoor) {
        exitDoor.appearOnMap();
        getChildren().add(exitDoor.getPhysicalRepresentation());

    }

    /**
     * Add enemies to the levelPane and position their image/representation.
     *
     * @param enemy Enemy object to be added to the levelpane.
     */
    public void addEnemy(Enemy enemy) {
        enemy.appearOnMap();
        getChildren().add(enemy.getPhysicalRepresentation());
    }

    /**
     * Add rewards to the levelPane and position their image/representation.
     *
     * @param rewards ArrayList of rewards of a levelPane.
     */
    public void addRewards (ArrayList<Reward> rewards) {

        for (Reward reward : rewards) {
            if (!(reward instanceof SpecialReward)) {
                reward.appearOnMap();
                getChildren().add(reward.getPhysicalRepresentation());
            } else {
                getChildren().add(reward.getPhysicalRepresentation());
                ((SpecialReward)reward).specialRewardTimeline(this);
            }
        }
    }

    /**
     * Checks if a location on the map is currently empty or occupied by another object
     *
     * @param x represents the x coordinate
     * @param y represents the y coordinate
     * @param width Width of location
     * @param height Height of location
     * @return boolean that represents true if position is empty or false if occupied
     */
    public boolean isEmpty(int x, int y, int width, int height) {

        Rectangle objBound = new Rectangle(x,y,width,height);

        for (Node node : this.getChildren()) {
            Bounds nodeBound = node.getBoundsInParent();
            int min_x = (int) nodeBound.getMinX();
            int min_y = (int) nodeBound.getMinY();
            int max_x = (int) nodeBound.getMaxX();
            int max_y = (int) nodeBound.getMaxY();

            if (min_x != 0 && min_y != 0 && max_x != this.getPrefWidth() && max_y != this.getPrefHeight() && objBound.getLayoutBounds().intersects(nodeBound)) {

                return false;
            }
        }
        return true;
    }

    /**
     * Generates random coordinates on the map that are not currently occupied by other objects
     *
     * @param obj Game object to be given a random position
     * @return integer list of random x,y coordinates
     */
    public int[] randomPosGenerator(GameObject obj) {

        int level_width = (int)(this.getPrefWidth());
        int level_height = (int)(this.getPrefHeight());

        Random random = new Random();
        int x, y;
        int [] pos = new int[]{0,0};
        boolean added = false;

        while (!added) {

            x = random.nextInt(level_width/20, level_width - level_width/20);
            y = random.nextInt(level_height/20, level_height - level_height/20);

            if (this.isEmpty(x, y, obj.getWidth(), obj.getHeight())) {
                added = true;
                pos[0] = x;
                pos[1] = y;
            }
        }
        return pos;
    }

}