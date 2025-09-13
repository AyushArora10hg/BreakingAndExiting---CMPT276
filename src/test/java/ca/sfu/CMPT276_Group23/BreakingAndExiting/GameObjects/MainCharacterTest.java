package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainCharacterTest {

    private MainCharacter testChar;
    private Image testImage;
    private AnimationHandler animationHandler;

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
        testChar = new MainCharacter(100, 100,100,100);
        animationHandler = testChar.getAnimationHandler();
        testImage = new Image(
                "file:src/Sprites/CharacterSprites/Robby-Right-Stationary.png");
    }

    @Test
    void ZeroPosition() {
        testChar.setPosition(0, 0);
        assertEquals(0, testChar.getPosition()[0]);
        assertEquals(0, testChar.getPosition()[1]);
    }

    @Test
    void PositivePosition() {
        testChar.setPosition(10, 15);
        assertEquals(10, testChar.getPosition()[0]);
        assertEquals(15, testChar.getPosition()[1]);
    }

    @Test
    void NegativePosition() {
        testChar.setPosition(-10, -15);
        assertEquals(100, testChar.getPosition()[0]);
        assertEquals(100, testChar.getPosition()[1]);
    }

    @Test
    void renderNullImage() {
        testChar.render(null);
        assertEquals(testImage.getUrl(),
                testChar.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void appearOnMap() {
        testChar.appearOnMap();
        //assertEquals( testImage, testChar.getPhysicalRepresentation());
        assertEquals(0, testChar.getPhysicalRepresentation().getX());
        assertEquals(0, testChar.getPhysicalRepresentation().getY());
        assertEquals(testChar.getWidth(), testChar.getPhysicalRepresentation().getFitWidth());
        assertEquals(testChar.getHeight(), testChar.getPhysicalRepresentation().getFitHeight());
    }

    @Test
    void getPhysicalRepresentation() {

        testChar.render(new Image(
                "file:src/Sprites/CharacterSprites/Robby-Right-Stationary.png"));
        assertEquals(testImage.getUrl(),
                testChar.getPhysicalRepresentation().getImage().getUrl());
    }

    @Test
    void setCurrentCharacterFrames() {

    }

    @Test
    void moveRight() {
        int currentX = testChar.getPosition()[0];
        testChar.move(10, 0);

        assertEquals(animationHandler.getRightFrames(), animationHandler.getCurrentFrames());
        assertEquals(currentX + 10, testChar.getPosition()[0]);
    }

    @Test
    void moveLeft() {
        int currentX = testChar.getPosition()[0];
        testChar.move(-10, 0);
        assertEquals(animationHandler.getLeftFrames(), animationHandler.getCurrentFrames());
        assertEquals(currentX - 10, testChar.getPosition()[0]);
    }

    @Test
    void moveDown() {
        int currentY = testChar.getPosition()[1];
        testChar.move(0, 10);
        assertEquals(animationHandler.getLeftFrames(), animationHandler.getCurrentFrames());
        assertEquals(currentY + 10, testChar.getPosition()[1]);
    }

    @Test
    void moveUp() {
        int currentY = testChar.getPosition()[1];
        testChar.move(0, -10);
        assertEquals(animationHandler.getRightFrames(), animationHandler.getCurrentFrames());
        assertEquals(currentY - 10, testChar.getPosition()[1]);
    }

    @Test
    void changeFrameRight() {
        testChar.move(10, 0);
        Image[] rightArray = animationHandler.getCurrentFrames();
        testChar.move(10, 0);
        assertEquals(rightArray, animationHandler.getCurrentFrames());
    }

    @Test
    void changeFrameLeft() {
        testChar.move(-10, 0);
        Image[] leftArray = animationHandler.getCurrentFrames();
        testChar.move(-10, 0);
        assertEquals(leftArray, animationHandler.getCurrentFrames());
    }

    @Test
    void changeFrameUp() {
        testChar.move(0, 10);
        Image[] upArray = animationHandler.getCurrentFrames();
        testChar.move(0, 10);
        assertEquals(upArray, animationHandler.getCurrentFrames());
    }

    @Test
    void changeFrameDown() {
        testChar.move(0, -10);
        Image[] downArray = animationHandler.getCurrentFrames();
        testChar.move(0, -10);
        assertEquals(downArray, animationHandler.getCurrentFrames());
    }

    @Test
    void getScore() {
        testChar.setScore(0);
        assertEquals(0, testChar.getScore());
    }

    @Test
    void setScorePositive() {
        testChar.setScore(1);
        assertEquals(1, testChar.getScore());
    }

    @Test
    void setScoreNegative() {
        testChar.setScore(-1);
        assertEquals(0, testChar.getScore());
    }

    @Test
    void addToScorePositive() {
        testChar.setScore(0);
        testChar.addToScore(1);
        assertEquals(1, testChar.getScore());
    }

    @Test
    void addToScoreNegative() {
        testChar.setScore(10);
        testChar.addToScore(-5);
        assertEquals(5, testChar.getScore());
    }

}