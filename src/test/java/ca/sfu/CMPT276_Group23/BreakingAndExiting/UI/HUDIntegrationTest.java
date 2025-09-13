package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class HUDIntegrationTest {

    private HUD hud;
    private Level mockLevel;
    private MainCharacter mockCharacter;

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        if (!Platform.isFxApplicationThread()) {
            Thread thread = new Thread(() -> Platform.startup(() -> {}));
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(1000); // Ensure JavaFX is ready
        }
    }

    @BeforeEach
    public void setUp() throws InterruptedException {

        mockLevel = mock(Level.class);
        mockCharacter = mock(MainCharacter.class);

        // Configure default mock behavior
        when(mockLevel.getCharacter()).thenReturn(mockCharacter);
        when(mockCharacter.getScore()).thenReturn(0);
        when(mockLevel.getRemainingTime()).thenReturn(180);

        // Initialize HUD
        hud = new HUD();
        hud.startHUD(mockLevel);
    }

    @Test
    void testUpdateScoreIncrease() {
        when(mockCharacter.getScore()).thenReturn(10);
        hud.update();

        Label scoreLabel = (Label) hud.getChildren().getFirst();
        assertEquals("SCORE : 10", scoreLabel.getText(), "Score label should reflect the updated score");

        assertEquals(Color.DARKGREEN, scoreLabel.getTextFill(), "Score label color should be green for increased score");
    }

    @Test
    void testUpdateScoreDecrease() {

        when(mockCharacter.getScore()).thenReturn(20);
        hud.update();

        when(mockCharacter.getScore()).thenReturn(10);
        hud.update();

        Label scoreLabel = (Label) hud.getChildren().getFirst();
        assertEquals("SCORE : 10", scoreLabel.getText(), "Score label should reflect the decreased score");

        assertEquals(Color.DARKRED, scoreLabel.getTextFill(), "Score label color should be red for decreased score");
    }

    @Test
    void testUpdateTimeDisplay() {

        when(mockLevel.getRemainingTime()).thenReturn(150);
        hud.update();

        Label timeLabel = (Label) hud.getChildren().get(2);
        assertEquals("Time Left : 2:30", timeLabel.getText(), "Time label should reflect the updated remaining time");
    }

    @Test
    void testGamePause () {

        assertFalse(mockLevel.isPaused());
        hud.startHUD(mockLevel);
        ((Button) hud.getChildren().get(1)).fire();
        verify(mockLevel, times(1)).pauseGame();
    }
}