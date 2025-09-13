package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LaserEnemyTest {

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
        LaserEnemy testLaser = new LaserEnemy(50, 50, 100, 100);
        int[] pos = {50,50};
        assertArrayEquals(testLaser.getPosition(), pos);
        assertEquals(10000, testLaser.getArea());
    }

    @Test
    void testImage() {
        LaserEnemy testLaser = new LaserEnemy(50, 50, 100, 100);
        MainCharacter testCharacter = new MainCharacter(50, 50, 100, 100);
        testLaser.render(new Image("file:src/Sprites/GuardSprites/lasers.png"));
        assertEquals("file:src/Sprites/GuardSprites/lasers.png", testLaser.getPhysicalRepresentation().getImage().getUrl());
        testLaser.isVisible = false;
        testLaser.start(testCharacter);
        assertNull(testLaser.getPhysicalRepresentation().getImage());
    }

    @Test
    void testPlayerCollision() {
        LaserEnemy testLaser = new LaserEnemy(50, 50, 100, 100);
        MainCharacter testCharacter = new MainCharacter(50, 50,100,100);
        boolean lost = false;
        testCharacter.setScore(100);
        testLaser.start(testCharacter);
        testLaser.handleCollision(testCharacter);
        assertEquals(50, testCharacter.getScore());
    }

    @Test
    void testSound() {
        LaserEnemy testLaser = new LaserEnemy(50, 50, 100, 100);
        testLaser.playSound();
    }
}
