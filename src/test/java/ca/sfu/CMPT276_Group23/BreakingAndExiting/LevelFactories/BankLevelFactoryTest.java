package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BankLevelFactoryTest {

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
    public void testBankLevelFactoryConstructor() {

        BankLevelFactory factory = new BankLevelFactory(100,100);
        assertNotNull(factory);
        assertEquals(100,BankLevelFactory.width);
        assertEquals(100,BankLevelFactory.height);
    }

    @Test
    void createBankMapObjectsTest() {
        ArrayList<MapObject> barriers = BankLevelFactory.setMapObjects();

        assertNotNull(barriers, "Barriers list should not be null.");
        assertFalse(barriers.isEmpty(), "Barriers list should not be empty.");
    }

    @Test
    void createBankEnemiesTest() {
        ArrayList<Enemy> enemies;
        enemies = BankLevelFactory.spawnEnemies();
        assertNotNull(enemies);
        assertFalse(enemies.isEmpty());

    }

    @Test
    void createBankRewardTest() {
        ArrayList<Reward> rewards;
        rewards = BankLevelFactory.populateRewards();
        assertNotNull(rewards);
        assertFalse(rewards.isEmpty());

    }

}
