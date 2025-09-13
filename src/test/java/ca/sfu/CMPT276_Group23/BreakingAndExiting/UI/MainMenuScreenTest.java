package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuScreenTest {

    private MainMenuScreen mainMenuScreen;
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
            mainMenuScreen = new MainMenuScreen();
            mainMenuScreen.start(stage);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testSceneInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertNotNull(mainMenuScreen.getMainMenuScene());
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testButtonsInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {

            assertNotNull(mainMenuScreen.getPlay());
            assertEquals(75, mainMenuScreen.getPlay().getPrefHeight());
            assertEquals(400, mainMenuScreen.getPlay().getPrefWidth());

            assertNotNull(mainMenuScreen.getQuit());
            assertEquals(50, mainMenuScreen.getQuit().getPrefHeight());
            assertEquals(150, mainMenuScreen.getQuit().getPrefWidth());

            latch.countDown();
        });
        latch.await();
    }

}