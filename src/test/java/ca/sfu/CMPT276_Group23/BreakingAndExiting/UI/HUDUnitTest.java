package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class HUDUnitTest {

    private HUD hud;
    private Level mockLevel;

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
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            mockLevel = new Level(new Stage());
            hud = new HUD();              // Create HUD instance on JavaFX thread
            latch.countDown();
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS), "JavaFX setup timed out");
    }

    @Test
    void testScoreCounterInitialization() {
        assertEquals(0, hud.getScoreCounter(), "Score counter should be initialized to 0");
    }

    @Test
    void testTimerCounterInitialization() {
        assertEquals(0.0, hud.getTimerCounter(), "Timer counter should be initialized to 0.0");
    }


    @Test
    void testScoreFormatting() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            hud.startHUD(mockLevel);
            Label scoreLabel = (Label) hud.getChildren().getFirst(); // Assumes score is the first child
            assertTrue(scoreLabel.getText().contains("SCORE : 0"), "Score label should display correct text");
            latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS), "Test timed out");
    }

    @Test
    void testPauseButtonFormatting() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            hud.startHUD(mockLevel);
            Button pauseButton = (Button) hud.getChildren().get(1); // Assumes pause button is second
            assertNotNull(pauseButton.getGraphic(), "Pause button should have an image graphic");
            MouseEvent mouseEnteredEvent = new MouseEvent(MouseEvent.MOUSE_ENTERED,
                    0, 0, 0, 0, MouseButton.NONE, 0,
                    false, false, false, false,
                    false, false, false, false, false, false, null);
            pauseButton.fireEvent(mouseEnteredEvent);

            assertEquals(1.2, pauseButton.getGraphic().getScaleX(), 0.01, "ImageView scale X should increase on mouse enter");
            assertEquals(1.2, pauseButton.getGraphic().getScaleY(), 0.01, "ImageView scale Y should increase on mouse enter");

            // Act & Assert: Test mouse exited behavior
            MouseEvent mouseExitedEvent = new MouseEvent(MouseEvent.MOUSE_EXITED,
                    0, 0, 0, 0, MouseButton.NONE, 0,
                    false, false, false, false,
                    false, false, false, false, false, false, null);
            pauseButton.fireEvent(mouseExitedEvent);

            assertEquals(1.0, pauseButton.getGraphic().getScaleX(), 0.01, "ImageView scale X should reset on mouse exit");
            assertEquals(1.0, pauseButton.getGraphic().getScaleY(), 0.01, "ImageView scale Y should reset on mouse exit");

            latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS), "Test timed out");
    }

    @Test
    void testUpdateScoreBy() {

        assertEquals(0, hud.getScoreCounter(), "Score counter should be initialized to 0");
        hud.updateScoreBy(100);
        assertEquals(100, hud.getScoreCounter(), "Score counter should be updated");
    }
}

