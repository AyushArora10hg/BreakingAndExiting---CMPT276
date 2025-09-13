package ca.sfu.CMPT276_Group23.BreakingAndExiting.LevelFactories;

import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.ExitDoor;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.MapObjects.MapObject;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Rewards.Reward;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.Enemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.FollowEnemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.TrackEnemy;
import ca.sfu.CMPT276_Group23.BreakingAndExiting.GameObjects.Enemies.LaserEnemy;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * Factory methods to create the Bank level.
 *
 * @author Group 23
 * @version 2.0
 */
public class BankLevelFactory extends AbstractLevelFactories{

    private static ArrayList<MapObject> barriers;
    private static ArrayList<Reward> rewards;
    private static MapObjectFactory mapObjectFactory;
    private static EnemyFactory enemyFactory;
    private static ArrayList<Enemy> enemies;
    private static ExitDoor exitDoor;

    /**
     * Define the height and width of the screen for reference.
     *
     * @param width Width of screen.
     * @param height Height of screen.
     */
    public BankLevelFactory(int width, int height) {
        super(width, height);
    }


    /**
     * Creates all map objects relative to screen size including, plants, tables, and the vault door.
     */
    public static void setMapObjectImages(){
        for (MapObject barrier: barriers) {
            if (!(barrier.getPhysicalRepresentation().getImage().getUrl().equals("file:src/Sprites/BankSprites/wall1.png")) && !(barrier.getPhysicalRepresentation().getImage().getUrl().equals("file:src/Sprites/BankSprites/vault.png")) ) {
                Random rand = new Random();
                int random = rand.nextInt(10);
                if (random <= 2) {
                    barrier.render(new Image("file:src/Sprites/ObjectSprites/plant.png"));
                }
            }
        }
    }

    /**
     * Setter of mapObjects (Boarders and barriers).Creates an instance of all
     * boarders and barriers to be placed on map and add them to an arraylist.
     *
     * @return An arraylist of map objects to be placed on map.
     */
    public static ArrayList<MapObject> setMapObjects() {

        final int BARRIER_WIDTH = width /32;
        final int BARRIER_HEIGHT = height /26;

        barriers=new ArrayList<MapObject>();

        //Boarder
        for (int i = 0; i<((int)(width /1.02)); i=i+50){
            barriers.add(MapObjectFactory.createMapObject(i, 0, BARRIER_WIDTH, BARRIER_HEIGHT));
            barriers.getLast().render(new Image("file:src/Sprites/BankSprites/wall1.png"));
            if (i==100 || i==101){
                barriers.getLast().render(new Image("file:src/Sprites/BankSprites/vault.png"));
            }
            barriers.add(MapObjectFactory.createMapObject(i, ((int)(height /1.03)) ,BARRIER_WIDTH, BARRIER_HEIGHT));
            barriers.getLast().render(new Image("file:src/Sprites/BankSprites/wall1.png"));
        }
        for (int i = 0; i<((int)(height /1.01)); i=i+50){
            barriers.add(MapObjectFactory.createMapObject(0, i, BARRIER_WIDTH, BARRIER_HEIGHT));
            barriers.getLast().render(new Image("file:src/Sprites/BankSprites/wall1.png"));
            barriers.add(MapObjectFactory.createMapObject(((int)(width /1.02)), i,BARRIER_WIDTH, BARRIER_HEIGHT));
            barriers.getLast().render(new Image("file:src/Sprites/BankSprites/wall1.png"));
        }

        // Barriers
        barriers.add(MapObjectFactory.createMapObject(BARRIER_WIDTH, (int)(height /1.5), BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject(width /10, height /20 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject(width /15, (int)(height /2.8), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject(width /8, (int)(height /1.7), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject(width /15, (int)(height /1.02) - height /16 , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /5.5), height /9 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /4.5), height /4, BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /4), (int)(height /2.5) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /5), (int)(height /1.3) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /3), (int)(height /15), BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /2), (int)(height /5), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2.5), (int)(height /3), BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /2.25), height /2 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /4), (int)(height /1.5) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /3), (int)(height /1.75) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /2.5), (int)(height /1.1) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2.3), (int)(height /1.3) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /5), (int)(height /1.15) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2), (int)(height /20) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2), (int)(height /1.5) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2.8), (int)(height /1.3) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.1), (int)(height /1.25) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /1.5), (int)(height /1.2) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.3), (int)(height /2.5) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.1), (int)(height /1.7) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /1.1), (int)(height /1.1), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /2), (int)(height /3), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.7), (int)(height /1.7), BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.6), (int)(height /2.7), BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /40), height /7 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.8), (int)(height /1.1) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /1.55), height /5 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.55), (int)(height /1.4) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /1.4), height /20 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.3), (int)(height /4) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.4), (int)(height /2) , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.4), (int)(height /1.25) , BARRIER_WIDTH, BARRIER_HEIGHT));

        barriers.add(MapObjectFactory.createMapObject((int)(width /1.1), height /6 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.05), height /3 , BARRIER_WIDTH, BARRIER_HEIGHT));
        barriers.add(MapObjectFactory.createMapObject((int)(width /1.2), (int)(height /1.5) , BARRIER_WIDTH, BARRIER_HEIGHT));

        setMapObjectImages();

        return barriers;
    }

    /**
     * Creates rewards as an array to be placed onto the map.
     *
     * @return An arraylist of rewards to be place on map.
     */
    public static ArrayList<Reward> populateRewards() {

        rewards = new ArrayList<>();

        final int WIDTH = width/60;
        final int HEIGHT = height/60;

        rewards.add(RewardsFactory.createReward("Regular", width/25, (int)(height/2.5), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", width/25, (int)(height/1.2), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", width/10, (int)(height/1.7), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", width/6, (int)(height/6), HEIGHT,WIDTH));

        rewards.add(RewardsFactory.createReward("Regular", (int)(width/5.5), (int)(height/2.4), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/6), (int)(height/1.17), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/3), (int)(height/5), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/2.8), (int)(height/2.3), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/2.8), (int)(height/1.35), HEIGHT,WIDTH));

        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.9), (int)(height/8), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/2.1), (int)(height/4), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/2.1), (int)(height/1.15), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.5), (int)(height/12), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.8), (int)(height/1.7), HEIGHT,WIDTH));

        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.6), (int)(height/1.1), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.1), (int)(height/15), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.15), (int)(height/3.5),HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.05), (int)(height/2.5), HEIGHT,WIDTH));
        rewards.add(RewardsFactory.createReward("Regular", (int)(width/1.3), (int)(height/1.8), HEIGHT,WIDTH));

        rewards.add(RewardsFactory.createReward("Special", 0,0,height/40,width/40));
        return rewards;
    }

    /**
     * Creates an array of all the enemies to be spawned onto the map.
     *
     * @return An arraylist of enemies to be place on map.
     */
    public static ArrayList<Enemy> spawnEnemies() {
        enemies = new ArrayList<>();
        int enemyWidth = (int)(width/19.2);
        int enemyHeight = (int)(height/12);
        int laserWidth = (int)(width/26.88);
        int laserHeight = (int)(height/16.8);
        enemies.add(EnemyFactory.createEnemy((int)(width/1.3), (int)(height/1.3), enemyWidth, enemyHeight, "Track"));
        enemies.add(EnemyFactory.createEnemy((int)(width/10.5), (int)(height/2.6), enemyWidth, enemyHeight, "Track"));
        enemies.add(EnemyFactory.createEnemy((int)(width/4), (int)(height/7), enemyWidth, enemyHeight, "Track"));
        enemies.add(EnemyFactory.createEnemy((int)(width/1.5), (int)(height/4), enemyWidth, enemyHeight, "Follow"));
        enemies.add(EnemyFactory.createEnemy((int)(width/3), (int)(height/2), enemyWidth, enemyHeight, "Follow"));
        enemies.add(EnemyFactory.createEnemy((int)(width/6), (int)(height/2), enemyWidth, enemyHeight, "Follow"));
        enemies.add(EnemyFactory.createEnemy((int)(width/1.85), (int)(height/3), laserWidth, laserHeight, "Laser"));
        enemies.add(EnemyFactory.createEnemy((int)(width/1.2), (int)(height/2.3), laserWidth, laserHeight, "Laser"));
        enemies.add(EnemyFactory.createEnemy((int)(width/3.6), (int)(height/1.3), laserWidth, laserHeight, "Laser"));
        enemies.add(EnemyFactory.createEnemy((int)(width/1.4), (int)(height/10), laserWidth, laserHeight, "Laser"));
        enemies.add(EnemyFactory.createEnemy((int)(width/2.4), (int)(height/4), laserWidth, laserHeight, "Laser"));
        enemies.add(EnemyFactory.createEnemy((int)(width/4), (int)(height/6), laserWidth, laserHeight, "Laser"));

        /*
        Enemy enemyCam = new EnemyCam(400, 400);
        enemies.add(enemyCam);
        levelPane.addEnemy(enemyCam);
        */
        return enemies;
    }

    /**
     * Setter of the exitDoor of a level.
     *
     * @return Instance of exitDoor.
     */
    public static ExitDoor setExitDoor(){
        exitDoor = ExitDoor.getInstance();
        exitDoor.setPosition(width - 100, height/2 - 50);
        return exitDoor;
    }




}
