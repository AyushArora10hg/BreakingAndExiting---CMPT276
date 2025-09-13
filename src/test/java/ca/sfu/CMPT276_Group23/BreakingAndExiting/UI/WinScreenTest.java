package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class WinScreenTest {


    private WinScreen winScreen;
    private Stage stage;
    protected int testValue;

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
            testValue = 1000;
            winScreen = new WinScreen(stage,testValue);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testSceneInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertNotNull(winScreen.getWinScene());
            assertEquals("Final Score: "+testValue, winScreen.getScoreLabelString());
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testButtonsInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {

            assertNotNull(winScreen.getMainMenu());
            assertEquals(75, winScreen.getMainMenu().getPrefHeight());
            assertEquals(400, winScreen.getMainMenu().getPrefWidth());


            assertNotNull(winScreen.getQuit());
            assertEquals(50, winScreen.getQuit().getPrefHeight());
            assertEquals(150, winScreen.getQuit().getPrefWidth());

            latch.countDown();
        });
        latch.await();
    }

}