package ca.sfu.CMPT276_Group23.BreakingAndExiting.UserInput;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.CollisionHandler;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * KeyHandler class takes in a specific key and moves the
 * main character accordingly. KeyHandler additionally checks if the location the main character
 * moved into causes a collision and reacts accordingly.
 *
 * @author Conor Benson
 * @version 2.0
 */
public class KeyHandler {
    private Set < KeyCode > keyMap;
    private Level level;
    private double min_characterX;
    private double min_characterY;
    private double  max_characterX;
    private double max_characterY;
    private int moveDistance;
    private MainCharacter mainCharacter;
    private int[] position;
    private CollisionHandler collisionHandler;

    /**
     * Constructor of KeyHandler creates a KeyHandler object relating to
     * the level it is created within.
     *
     * <p>
     *     The constructor first takes in a level and a mainCharacter, instantiates level, keyMap and
     *     moveDistance. The constructor then uses the mainCharacter to instantiate min_characterX,
     *     min_characterY, max_characterX and max_characterY to determine the bounds that the
     *     mainCharacter can move.
     *
     * </p>
     *
     * @param level the specific level the KeyHandler was created in.
     */
    public KeyHandler(Level level) {

        this.level = level;
        keyMap = new HashSet < > ();
        moveDistance = 10;
        this.mainCharacter = level.getCharacter();
        this.position = mainCharacter.getPosition();
        min_characterX = mainCharacter.getPhysicalRepresentation().getBoundsInParent().getMinX() + 10;
        min_characterY = mainCharacter.getPhysicalRepresentation().getBoundsInParent().getMinY() + 20;
        max_characterX = mainCharacter.getPhysicalRepresentation().getBoundsInParent().getMaxX() - 10;
        max_characterY = mainCharacter.getPhysicalRepresentation().getBoundsInParent().getMaxY() - 10;
        collisionHandler = new CollisionHandler(this.mainCharacter);

    }

    /**
     * keyPressed adds the key pressed by the user into a keyMap hashmap in order to
     * be handled in the processKey method.
     *
     * @param keyEvent A key pressed by the user.
     */
    public void keyPressed(KeyEvent keyEvent) {
        keyMap.add(keyEvent.getCode());
    }


    /**
     * Method used to check if a specific key is currently in the keyMap.
     *
     * @param keyCode A key that could be in the keyMap.
     * @return A boolean depending on if the specified key exists in the key map or not.
     */
    public boolean containsKey(KeyCode keyCode) {
        return keyMap.contains(keyCode);
    }


    /**
     * KeyReleased removed the key released by the user from the keyMap hashmap.
     *
     * @param keyEvent A key released by the user.
     */
    public void keyReleased(KeyEvent keyEvent) {
        keyMap.remove(keyEvent.getCode());
    }

    /**
     * processKey uses the hashMap to check what key is currently in the keyMap and acts accordingly.
     *
     * <p>
     *     processKey first checks which key is in the keyMap, then moves the character accordingly.
     *     If the mainCharacter
     * </p>
     *
     */
    public void processKey() {

        if (keyMap.contains(KeyCode.UP) || keyMap.contains(KeyCode.W)) {

            if (min_characterY - moveDistance >= 0) {
                level.getCharacter().move(0, -moveDistance);
                if (collisionHandler.checkMapObjectCollision(level.getBarriers())) {
                    level.getCharacter().move(0, moveDistance);
                } else if (collisionHandler.checkExitDoorCollision(level.getExitDoor())) {
                    level.setWon(true);
                }

            }
        }

        if (keyMap.contains(KeyCode.LEFT) || keyMap.contains(KeyCode.A)) {

            if (min_characterX - moveDistance >= 0) {
                level.getCharacter().move(-moveDistance, 0);
                if (collisionHandler.checkMapObjectCollision(level.getBarriers())) {
                    level.getCharacter().move(moveDistance, 0);
                } else if (collisionHandler.checkExitDoorCollision(level.getExitDoor())) {
                    level.setWon(true);
                }
            }
        }

        if (keyMap.contains(KeyCode.DOWN) || keyMap.contains(KeyCode.S)) {

            if (max_characterY + moveDistance <= level.getDimensions()[1]) {
                level.getCharacter().move(0, moveDistance);
                if (collisionHandler.checkMapObjectCollision(level.getBarriers())) {
                    level.getCharacter().move(0, -moveDistance);
                } else if (collisionHandler.checkExitDoorCollision(level.getExitDoor())) {
                    level.setWon(true);
                }

            }
        }

        if (keyMap.contains(KeyCode.RIGHT) || keyMap.contains(KeyCode.D)) {

            if (max_characterX + moveDistance <= level.getDimensions()[0]) {
                level.getCharacter().move(moveDistance, 0);
                if (collisionHandler.checkMapObjectCollision(level.getBarriers())) {
                    level.getCharacter().move(-moveDistance, 0);
                } else if (collisionHandler.checkExitDoorCollision(level.getExitDoor())) {
                    level.setWon(true);
                }

            }
        }



    }

}