package ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MapObjectTest {
    @BeforeAll
    public static void initJavaFX() throws InterruptedException {
        Thread thread = new Thread(() -> {
            Platform.startup(() -> {});});
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1000);
    }
    @Test
    void testDefaultConstructor(){
      MapObject testObj= new MapObject();
      assertTrue(testObj.getVisibleOnMap());
      assertEquals(25, testObj.getWidth());
      assertEquals(25, testObj.getHeight());
      assertNotNull(testObj.getPhysicalRepresentation());
    }

    @Test
    void testPositionAreaConstructor(){
        MapObject testObj= new MapObject(100,100,50,50);
        assertEquals(100, testObj.getPosition()[0]);
        assertEquals(100, testObj.getPosition()[1]);
        assertEquals(50, testObj.getWidth());
        assertEquals(50, testObj.getHeight());
    }
    @Test
    void testVisibility(){
        MapObject testObj=new MapObject();
        testObj.setVisibility(false);
        assertFalse(testObj.getVisibleOnMap());
        testObj.setVisibility(true);
        assertTrue(testObj.getVisibleOnMap());
    }

    @Test
    void testPosition(){
        MapObject testObj=new MapObject();
        testObj.setPosition(25,50);
        assertEquals(25, testObj.getPosition()[0]);
        assertEquals(50, testObj.getPosition()[1]);
    }

    @Test
     void testNegativePosition(){
        MapObject testObj=new MapObject();
        testObj.setPosition(50,50);
        testObj.setPosition(-100,-100);
        assertEquals(50, testObj.getPosition()[0]);
        assertEquals(50, testObj.getPosition()[1]);
    }

    @Test
    void testArea(){
        MapObject testObj=new MapObject();
        testObj.setArea(10,10);
        assertEquals(10, testObj.getWidth());
        assertEquals(10, testObj.getHeight());
        assertEquals(100,testObj.getArea());
    }

    @Test
    void testNegativeArea(){
        MapObject testObj=new MapObject();
        testObj.setArea(-20,-20);
        assertEquals(20, testObj.getWidth());
        assertEquals(20, testObj.getHeight());
        assertEquals(400,testObj.getArea());
    }

    @Test
    void testRender(){
        MapObject testObj=new MapObject();
        Image testImage = new Image("file:src/Sprites/ObjectSprites/exitDoor.png");
        testObj.render(testImage);
        assertEquals(testImage.getUrl(), testObj.getPhysicalRepresentation().getImage().getUrl());
    }


    @Test
    void testAppearOnMap(){
        MapObject testObj=new MapObject();
        testObj.setVisibility(false);
        testObj.appearOnMap();
        assertTrue(testObj.getVisibleOnMap());
    }





}