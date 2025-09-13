package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MainCharacter;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardsIntegrationTest {

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {

        Thread javafxInitThread = new Thread(() -> Platform.startup(() -> {}));
        javafxInitThread.setDaemon(true);
        javafxInitThread.start();
        Thread.sleep(1000);
    }

    @Test
    public void RegularRewardCollisionTest() throws InterruptedException {

        Thread thread = new Thread(() -> Platform.startup(() -> {}));
        Reward reward = new RegularReward(100,100,20,20);
        MainCharacter character = new MainCharacter(100,100,100,100);
        character.setScore(0);
        reward.handleCollision(character);
        assertEquals(100, character.getScore());
        Thread.sleep(2000);


    }

    @Test
    public void SpecialRewardCollisionTest() throws InterruptedException {

        Thread thread = new Thread(() -> Platform.startup(() -> {}));
        Reward reward = new SpecialReward(100,100,20,20);
        MainCharacter character = new MainCharacter(100,100,100,100);
        character.setScore(0);
        reward.handleCollision(character);
        assertEquals(300, character.getScore());
        Thread.sleep(2000);


    }


}
