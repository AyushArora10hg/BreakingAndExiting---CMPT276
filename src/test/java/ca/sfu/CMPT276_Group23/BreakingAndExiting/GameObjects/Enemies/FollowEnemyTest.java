package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FollowEnemyTest {

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
        FollowEnemy testFollow = new FollowEnemy(49, 49, 100, 100);
        int[] pos = {50,45};
        assertArrayEquals(pos, testFollow.getPosition());
        assertEquals(testFollow.getArea(), 10000);
    }

    @Test
    void testImage() {
        FollowEnemy testFollow = new FollowEnemy(50, 50, 100, 100);
        testFollow.render(new Image("file:src/Sprites/GuardSprites/guard-left.png"));
        testFollow.appearOnMap();
        assertEquals("file:src/Sprites/GuardSprites/guard-left.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void testMove() {
        FollowEnemy testFollow = new FollowEnemy(50, 50, 100, 100);
        testFollow.move(5, 0);
        assertEquals(testFollow.getPosition()[0], 55);
        assertEquals(testFollow.getPosition()[1], 50);
        testFollow.move(0, 5);
        assertEquals(testFollow.getPosition()[0], 55);
        assertEquals(testFollow.getPosition()[1], 55);
    }

    @Test
    void testFrames() {
        FollowEnemy testFollow = new FollowEnemy(50, 50, 100, 100);
        MainCharacter testCharacter = new MainCharacter(100, 50, 100, 100);
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right1.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testCharacter.move(-40, -15);
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right2.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-right1.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testCharacter.move(-100, 0);
        testFollow.move(100,0);
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left2.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testCharacter.move(95, 100);
        testFollow.move(0,-100);
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left1.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
        testFollow.start(testCharacter);
        assertEquals("file:src/Sprites/GuardSprites/guard-left2.png", testFollow.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void testSound() {
        FollowEnemy testFollow = new FollowEnemy(50, 50, 100, 100);
        testFollow.playSound();
    }
}
