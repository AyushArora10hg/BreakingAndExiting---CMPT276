package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RewardsUnitTest {

    private RegularReward regularReward;
    private SpecialReward specialReward;

    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }

    @BeforeEach
    void setUp() {
        regularReward = new RegularReward(100, 150, 50, 50);
        specialReward = new SpecialReward(200, 300, 60, 60);
    }

    @Test
    void testRegularRewardInitialization() {
        assertEquals(100, regularReward.getPosition()[0]);
        assertEquals(150, regularReward.getPosition()[1]);
        assertEquals(50, regularReward.getWidth());
        assertEquals(50, regularReward.getHeight());
        assertTrue(regularReward.getVisibility());
        assertEquals(100, regularReward.getRewardValue());
        assertNotNull(regularReward.getPhysicalRepresentation().getImage());

    }

    @Test
    void testSpecialRewardInitialization() {
        assertEquals(200, specialReward.getPosition()[0]);

        assertEquals(300, specialReward.getPosition()[1]);
        assertEquals(60, specialReward.getWidth());
        assertEquals(60, specialReward.getHeight());
        assertFalse(specialReward.getVisibility());
        assertEquals(300, specialReward.getRewardValue());
        assertEquals(15, specialReward.getVisibilityTime());
        assertNotNull(specialReward.getPhysicalRepresentation().getImage());
    }

    @Test
    void testSetAndGetValue() {
        regularReward.setRewardValue(1000);
        assertEquals(1000, regularReward.getRewardValue());

        specialReward.setRewardValue(10);
        assertEquals(10, specialReward.getRewardValue());
    }

    @Test
    void testSetAndGetVisibility() {
        regularReward.setVisibility(false);
        assertFalse(regularReward.getVisibility());

        specialReward.setVisibility(true);
        assertTrue(specialReward.getVisibility());
    }

    @Test
    void testAppearOnMap() {
        regularReward.appearOnMap();
        ImageView regularImageView = regularReward.getPhysicalRepresentation();
        assertEquals(100, regularImageView.getLayoutX());
        assertEquals(150, regularImageView.getLayoutY());
        assertEquals(50, regularImageView.getFitWidth());
        assertEquals(50, regularImageView.getFitHeight());
        assertTrue(regularReward.getVisibility());
    }

    @Test
    void testRemoveFromMap() {
        regularReward.removeFromMap();
        assertFalse(regularReward.getVisibility());
        assertNull(regularReward.getPhysicalRepresentation().getImage());
    }

    @Test
    void testRewardSetPosition() {
        regularReward.setPosition(400, 500);
        assertArrayEquals(new int[]{400, 500}, regularReward.getPosition());

        specialReward.setPosition(400, 500);
        assertArrayEquals(new int[]{400, 500}, specialReward.getPosition());
    }

    @Test
    void testRenderUpdatesImage() {
        Image newImage = new Image("file:src/Sprites/CharacterSprites/Bobby move left 1.pixil");
        regularReward.render(newImage);
        assertEquals(newImage, regularReward.getPhysicalRepresentation().getImage());
    }

    @Test
    void testRewardAreaCalculation() {
        assertEquals(2500, regularReward.getArea());
        assertEquals(3600, specialReward.getArea());
    }


}
