package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrackEnemyTest {

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }

    @Test
    void testSpawn() {
        TrackEnemy testTrack = new TrackEnemy(49, 49, 100, 100);
        int[] pos = {50,45};
        assertArrayEquals(testTrack.getPosition(), pos);
        assertEquals(testTrack.getArea(), 10000);
    }

    @Test
    void testImage() {
        TrackEnemy testTrack = new TrackEnemy(50, 50, 100, 100);
        testTrack.render(new Image("file:src/Sprites/GuardSprites/guard-left.png"));
        assertEquals("file:src/Sprites/GuardSprites/guard-left.png", testTrack.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void testMove() {
        TrackEnemy testTrack = new TrackEnemy(50, 50, 100, 100);
        testTrack.move(5, 0);
        assertEquals(testTrack.getPosition()[0], 55);
        assertEquals(testTrack.getPosition()[1], 50);
        testTrack.move(0, 5);
        assertEquals(testTrack.getPosition()[0], 55);
        assertEquals(testTrack.getPosition()[1], 55);
    }

    @Test
    void testFrames() {
        TrackEnemy testTrack = new TrackEnemy(50, 50, 100, 100);
        MainCharacter testCharacter = new MainCharacter(100, 50, 100, 100);
        testTrack.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left1.png", testTrack.getPhysicalRepresentation().getImage().getUrl());
        testTrack.move(0,95);
        testTrack.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right2.png", testTrack.getPhysicalRepresentation().getImage().getUrl());
        testTrack.move(95,0);
        testTrack.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right.png", testTrack.getPhysicalRepresentation().getImage().getUrl());
        testTrack.move(0,-95);
        testTrack.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left1.png", testTrack.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void testSound() {
        TrackEnemy testTrack = new TrackEnemy(50, 50, 100, 100);
        testTrack.playSound();
    }
}
