package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.UI.Level;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyIntegrationTests {

    private Level level;
    private Stage stage;

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            stage = new Stage();
            level = new Level(stage);
            level.start();
            latch.countDown();
        });
        latch.await();
    }

    @Test
    @Order(1)
    void testPlayerTrackCollision() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> {
                TrackEnemy testTrack = new TrackEnemy(50, 50, 100, 100);
                MainCharacter testCharacter = new MainCharacter(50, 50, 100, 100);
                testCharacter.setScore(100);
                testTrack.start(testCharacter);
                level.setLost(true);
                testTrack.handleCollision(testCharacter);
                assertEquals(0, testCharacter.getScore());
                assertTrue(level.getLost());
            });
            latch.countDown();
        });
        latch.await();
    }

    @Test
    @Order(2)
    void testPlayerFollowCollision() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> {
                FollowEnemy testFollow = new FollowEnemy(50, 50, 100, 100);
                MainCharacter testCharacter = new MainCharacter(50, 50, 100, 100);
                testCharacter.setScore(100);
                testFollow.start(testCharacter);
                level.setLost(true);
                testFollow.handleCollision(testCharacter);
                assertEquals(0, testCharacter.getScore());
                assertTrue(level.getLost());
            });
            latch.countDown();
        });
        latch.await();
    }
}
