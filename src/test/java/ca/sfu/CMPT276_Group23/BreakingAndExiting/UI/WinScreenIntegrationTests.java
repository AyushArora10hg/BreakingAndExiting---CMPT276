package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WinScreenIntegrationTests {



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
    @Order(1)
    void testMainMenu() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> {
                winScreen.getMainMenu().fire();
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
                winScreen.getQuit().fire();
            });

            latch.countDown();
        });
        latch.await();
    }



}