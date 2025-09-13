package ca.sfu.CMPT276_Group23.BreakingAndExiting.UI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    private Level level;
    private Stage stage;
    private CollisionHandler collisionHandler;

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});});
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }
    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            stage = new Stage();
            level = new Level(stage);
            latch.countDown();
        });
        latch.await();
    }
    @Test
    void testGetCharacter(){
        assertNotNull(level.getCharacter());
    }
    @Test
    void testGetDimensions(){
        assertNotNull(level.getDimensions());
    }

//    @Test
//    void testGetBarriers(){
//        assertNotNull(level.getBarriers());
//    }

    @Test
    void testRemainingTime(){
        int num=0;
        assertNotEquals(level.getRemainingTime(),0);
    }

    @Test
    void testLost(){
        assertFalse(level.getLost());
        level.setLost(true);
        assertTrue(level.getLost());
    }

    @Test
    void testWon(){
        assertFalse(level.getWon());
        level.setWon(true);
        assertTrue(level.getWon());
    }
}