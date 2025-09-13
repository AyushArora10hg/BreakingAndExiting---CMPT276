package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainMenuIntegrationTests {

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
    @Order(1)
    void testPlay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> {
                mainMenuScreen.getPlay().fire();
            });

            latch.countDown();
        });
        latch.await();
    }

    @Test
    @Order(2)
    void testQuit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> {
                mainMenuScreen.getQuit().fire();
            });

            latch.countDown();
        });
        latch.await();
    }




}