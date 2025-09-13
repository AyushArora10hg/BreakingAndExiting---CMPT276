package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.LoseScreen;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class LoseScreenTest {

    private LoseScreen loseScreen;
    private Stage stage;

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> latch.countDown());
        latch.await();
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            stage = new Stage();
            loseScreen = new LoseScreen(stage);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testSceneInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertNotNull(loseScreen.getScene());
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testButtonsInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {

            assertNotNull(loseScreen.getMainMenu());
            assertEquals(75, loseScreen.getMainMenu().getPrefHeight());
            assertEquals(400, loseScreen.getMainMenu().getPrefWidth());


            assertNotNull(loseScreen.getQuit());
            assertEquals(50, loseScreen.getQuit().getPrefHeight());
            assertEquals(150, loseScreen.getQuit().getPrefWidth());

            latch.countDown();
        });
        latch.await();
    }

}