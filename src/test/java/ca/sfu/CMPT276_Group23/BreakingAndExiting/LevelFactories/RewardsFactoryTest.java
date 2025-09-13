package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.RegularReward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.SpecialReward;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RewardsFactoryTest {

    private Reward reward;

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
    public void testRegularRewardCreation() {

        reward = RewardsFactory.createReward("Regular",0,0,0,0);
        assertInstanceOf(RegularReward.class, reward);
    }

    @Test
    public void testSpecialRewardCreation() {
        reward = RewardsFactory.createReward("Special",0,0,0,0);
        assertInstanceOf(SpecialReward.class, reward);
    }

    @Test
    public void testInvalidCreation() {
        reward = RewardsFactory.createReward("NoRewardType",0,0,0,0);
        assertNull(reward);
    }
}
