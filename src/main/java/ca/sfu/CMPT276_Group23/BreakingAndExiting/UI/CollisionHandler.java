package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.ExitDoor;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import javafx.scene.shape.Rectangle;
import java.util.List;

/**
 *
 *  AnimationHandler classes manages collision logic of objects that have movement or are barriers can collide
 *
 * @author Group 23
 * @version 1.0
 *
 */
public class CollisionHandler {

    private Rectangle collisionRectangle;
    private MainCharacter character;

    /**
     * Constructor to set up character and collision rectangle.
     *
     * @param mainCharacter is the MainCharacter to check collision against.
     */
    public CollisionHandler(MainCharacter mainCharacter) {

        this.character = mainCharacter;
        collisionRectangle = new Rectangle(character.getPosition()[0] + character.getWidth()/3, character.getPosition()[1] + character.getHeight()/2,character.getWidth()/3,character.getHeight()/2.5);
    }

    /**
     * updatePosition updates the position of the characters collision rectangle.
     */
    private void updatePosition() {

        collisionRectangle.setX(character.getPosition()[0] + character.getWidth()/3);
        collisionRectangle.setY(character.getPosition()[1] + character.getHeight()/2.5);
    }

    /**
     * <p>
     *     Checks if player has overlapped a reward, handles the collision,
     *     updates score and removes the reward from the map.
     * </p>
     *
     * @param rewards List of rewards on map.
     */
    public void checkCollision_rewards(List<Reward> rewards) {

        for (Reward reward : rewards) {
            if (reward.getVisibility() && reward.getPhysicalRepresentation().getBoundsInParent().
                    intersects(collisionRectangle.getLayoutBounds())) {
                reward.handleCollision(character);
                reward.removeFromMap();

            }
        }
        updatePosition();

    }


    /**
     * <p>
     *     Checks if player has overlapped an enemy,
     *     handles the collision, updates the score,
     *     and transitions to lose sequence.
     * </p>
     *
     * @param enemies List of all enemies on map.
     * @param barriers List of all barriers on map.
     * @return  Boolean informing the level if an enemy collision has occurred.
     */
    public boolean checkCollisionEnemies(List<Enemy> enemies, List<MapObject> barriers) {
        // Check collision with enemies
        updatePosition();
        for (Enemy enemy : enemies) {
            Rectangle collisionRectEnemy = new Rectangle(enemy.getPosition()[0] + enemy.getWidth()/3, enemy.getPosition()[1] + enemy.getHeight()/2, enemy.getWidth()/3, enemy.getHeight()/2);
            if (collisionRectEnemy.getLayoutBounds().
                    intersects(collisionRectangle.getLayoutBounds())) {
                return enemy.handleCollision(character);
            }

            // Check collision with map
            for (MapObject object : barriers) {
                if (collisionRectEnemy.getLayoutBounds().
                        intersects(object.getPhysicalRepresentation().getBoundsInParent())) {
                    if(object instanceof ExitDoor) {
                        return false;
                    }
                    // Object is below the enemy
                    if ((enemy.getPosition()[1] > (object.getPosition()[1]))
                            // Object is above the enemy
                            || (enemy.getPosition()[1] < (object.getPosition()[1]))) {
                        // Player is to the right of the enemy
                        if (character.getPosition()[0] > enemy.getPosition()[0]) {
                            enemy.move(-5, 0);
                        }
                        // Player is to the left of the enemy
                        if (character.getPosition()[0] < enemy.getPosition()[0]) {
                            enemy.move(5, 0);
                        }
                    }
                    // Object is to the left of the enemy
                    if ((enemy.getPosition()[0] > (object.getPosition()[0]))
                            // Object is to the right of the enemy
                            || (enemy.getPosition()[0] < (object.getPosition()[0]))) {
                        // Player is above the enemy
                        if (character.getPosition()[1] > enemy.getPosition()[1]) {
                            enemy.move(0, -5);
                        }
                        // Player is below the enemy
                        if (character.getPosition()[1] < enemy.getPosition()[1]) {
                            enemy.move(0, 5);
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * <p>
     *     Checks if player has overlapped a MapObject
     *     barrier, handles the collision, and moves their position.
     * </p>
     *
     * @param barriers List of barriers on map.
     * @return boolean true if player movement is overlapping a MapObject, false if not.
     */
    public boolean checkMapObjectCollision(List<MapObject> barriers) {
        Rectangle collisionRect = new Rectangle(character.getPosition()[0] + character.getPhysicalRepresentation().getFitWidth()/3, character.getPosition()[1] + character.getPhysicalRepresentation().getFitHeight()/4, 40, 70);
        for (MapObject mapObject : barriers){
            if (mapObject.getPhysicalRepresentation().getBoundsInParent().
                    intersects(collisionRect.getLayoutBounds())) {
                return true;

            }
        }
        return false;
    }

    /**
     * Allows testing of MapObject Collision
     * @param mapObject any single barrier
     * @return whether or not the player has collided with the barrier.
     */
    public boolean testMapObjectCollision(MapObject mapObject) {
        Rectangle collisionRect = new Rectangle(character.getPosition()[0] + character.getPhysicalRepresentation().getFitWidth()/3, character.getPosition()[1] + character.getPhysicalRepresentation().getFitHeight()/4, 40, 70);
        return mapObject.getPhysicalRepresentation().getBoundsInParent().intersects(collisionRect.getLayoutBounds());
    }

    /** Collision checker for exit door.
     * <p>
     *     Check if player has overlapped exit door, if they
     *     do not have enough score, collision is handled like
     *     a barrier, if the door is unlocked proceed to win sequence.
     * </p>
     *
     * @param exitDoor An ExitDoor object
     * @return true if player is overlapping win screen and they have enough score, false otherwise.
     */
    public boolean checkExitDoorCollision(ExitDoor exitDoor) {
        Rectangle collisionRect = new Rectangle(character.getPosition()[0] + 10, character.getPosition()[1] + 10, 80, 80);
        return (exitDoor.getPhysicalRepresentation().getBoundsInParent().
                intersects(collisionRect.getLayoutBounds())) && exitDoor.getDoorOpen();
    }
}
