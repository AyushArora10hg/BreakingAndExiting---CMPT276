package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapObjectFactoryTest {
    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        MapObjectFactory mapObjectFactory;
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});});
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }

    @Test
    void testPositionAreaCreate(){
        MapObject testObj= MapObjectFactory.createMapObject(100, 100,50,50);
        assertEquals(100, testObj.getPosition()[0]);
        assertEquals(100, testObj.getPosition()[1]);
        assertEquals(50, testObj.getWidth());
        assertEquals(50, testObj.getHeight());
    }

}