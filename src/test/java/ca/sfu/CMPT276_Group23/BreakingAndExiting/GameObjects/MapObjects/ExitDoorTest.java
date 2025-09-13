package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitDoorTest {
    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});});
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }
    @Test
    void testDefaultInstance(){
        ExitDoor testObj= ExitDoor.getInstance();
        assertTrue(testObj.getVisibleOnMap());
        assertEquals(25, testObj.getWidth());
        assertEquals(25, testObj.getHeight());
        assertNotNull(testObj.getPhysicalRepresentation());
    }
    @Test
    void testDoorOpen(){
        ExitDoor testObj= ExitDoor.getInstance();
        testObj.setDoorOpen(false);
        assertFalse(testObj.getDoorOpen());
        testObj.setDoorOpen(true);
        assertTrue(testObj.getDoorOpen());
    }
    @Test
    void testDoorImage(){
        ExitDoor testObj = ExitDoor.getInstance();
        testObj.setDoorOpen(false);
        ImageView testImage = new ImageView(new Image("file:src/Sprites/ObjectSprites/exitDoor.png"));
        assertEquals(testImage.getImage().getUrl(), testObj.getPhysicalRepresentation().getImage().getUrl());

        testObj.setDoorOpen(true);
        testImage = new ImageView(new Image("file:src/Sprites/ObjectSprites/exitDoorOpen.png"));
        assertEquals(testImage.getImage().getUrl(), testObj.getPhysicalRepresentation().getImage().getUrl());
    }
}