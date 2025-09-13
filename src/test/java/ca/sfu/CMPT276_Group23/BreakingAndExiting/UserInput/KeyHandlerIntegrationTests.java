package ca.sfu.CMPT276_Group23.BreakingAndExiting.UserInput;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.CollisionHandler;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.Level;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.ExitDoor;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class KeyHandlerIntegrationTests {
    private KeyHandler keyHandler;
    private Level mockLevel;
    private MainCharacter mockCharacter;
    private Bounds mockBounds;
    private ImageView mockPhysicalRepresentation;
    private ExitDoor mockExitDoor;
    private CollisionHandler collisionHandler;
    private MapObject mockBarrier;

    @BeforeEach
    void setUp() {
        mockLevel = mock(Level.class);
        mockCharacter = mock(MainCharacter.class);
        mockBounds = mock(Bounds.class);
        mockPhysicalRepresentation = mock(ImageView.class);
        mockExitDoor = mock(ExitDoor.class);
        mockBarrier = mock(MapObject.class);


        when(mockLevel.getCharacter()).thenReturn(mockCharacter);
        when(mockLevel.getExitDoor()).thenReturn(mockExitDoor);
        when(mockLevel.getExitDoor().getPhysicalRepresentation()).thenReturn(mockPhysicalRepresentation);
        when(mockLevel.getDimensions()).thenReturn(new int[] {800, 600});


        when(mockCharacter.getPosition()).thenReturn(new int[] {100, 100});
        when(mockBarrier.getPosition()).thenReturn(new int[] {100, 100});
        when(mockCharacter.getPhysicalRepresentation()).thenReturn(mockPhysicalRepresentation);
        when(mockBarrier.getPhysicalRepresentation()).thenReturn(mockPhysicalRepresentation);


        when(mockPhysicalRepresentation.getBoundsInParent()).thenReturn(mockBounds);
        when(mockBounds.getMinX()).thenReturn(100.0);
        when(mockBounds.getMinY()).thenReturn(100.0);
        when(mockBounds.getMaxX()).thenReturn(150.0);
        when(mockBounds.getMaxY()).thenReturn(150.0);


        keyHandler = new KeyHandler(mockLevel);
        collisionHandler=new CollisionHandler(mockCharacter);
    }


    @Test
    void testProcessDKey() {

        KeyEvent DEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.D, false, false, false, false);
        keyHandler.keyPressed(DEvent);
        keyHandler.processKey();

        verify(mockCharacter).move(10, 0);
    }

    @Test
    void testProcessAKey() {

        KeyEvent AEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
        keyHandler.keyPressed(AEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(-10, 0);
    }

    @Test
    void testProcessWKey() {

        KeyEvent WEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
        keyHandler.keyPressed(WEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(0, -10);
    }

    @Test
    void testProcessSKey() {

        KeyEvent SEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
        keyHandler.keyPressed(SEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(0, 10);
    }

    /* - FOR FUTURE IMPLEMENTATION
    @Test
    void testCollisionPreventionD() {
        when(collisionHandler.testMapObjectCollision(mockBarrier)).thenReturn(true);// assume there is a barrier;
        KeyEvent DEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.D, false, false, false, false);
        keyHandler.keyPressed(DEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(10, 0);
        verify(mockCharacter).move(-10, 0);
    }

    @Test
    void testCollisionPreventionA() {
        when(collisionHandler.testMapObjectCollision(mockBarrier)).thenReturn(true);// assume there is a barrier;
        KeyEvent AEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
        keyHandler.keyPressed(AEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(-10, 0);
        verify(mockCharacter).move(10, 0);
    }

    @Test
    void testCollisionPreventionW() {
        when(collisionHandler.testMapObjectCollision(mockBarrier)).thenReturn(true);// assume there is a barrier;
        KeyEvent WEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
        keyHandler.keyPressed(WEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(0, 10);
        verify(mockCharacter).move(0, -10);
    }

    @Test
    void testCollisionPreventionS() {
        when(collisionHandler.testMapObjectCollision(mockBarrier)).thenReturn(true);// assume there is a barrier;
        KeyEvent SEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
        keyHandler.keyPressed(SEvent);
        keyHandler.processKey();
        verify(mockCharacter).move(0, -10);
        verify(mockCharacter).move(0, 10);
    }

    @Test
    void testExitDoorCollision() {
        when(collisionHandler.checkExitDoorCollision(mockLevel.getExitDoor())).thenReturn(true); // assume exit door
        KeyEvent DEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.D, false, false, false, false);

        keyHandler.keyPressed(DEvent);
        keyHandler.processKey();

        verify(mockLevel).setWon(true);
    }*/

    @Test
    void testDoublePresses() {
        KeyEvent rightEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false);
        KeyEvent upEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false);

        keyHandler.keyPressed(rightEvent);
        keyHandler.keyPressed(upEvent);
        keyHandler.processKey();

        verify(mockCharacter).move(10, 0);
        verify(mockCharacter).move(0, -10);
    }

    @Test
    void testKeyReleased() {
        KeyEvent WEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);

        keyHandler.keyPressed(WEvent);
        keyHandler.keyReleased(WEvent);

        assertFalse(keyHandler.containsKey(KeyCode.W));
    }
}



