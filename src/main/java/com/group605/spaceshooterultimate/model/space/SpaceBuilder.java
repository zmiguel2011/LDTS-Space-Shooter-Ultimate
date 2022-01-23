package com.group605.spaceshooterultimate.model.space;

import com.group605.spaceshooterultimate.model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpaceBuilder {

    private int height;
    private int width;

    public SpaceBuilder(int width, int height){
        this.width = width;
        this.height = height;
    }

    public Space createSpace(){
        Space space = new Space(width, height);
        space.setPlayer(createPlayer(width/2,15)); //width/2,height-5
        space.setSpaceships(createSpaceShips(3,width/2,1));
        space.setBorders(createBorders(width, height-5));
        space.setAsteroids(createAsteroids(5,1));
        space.setItems(createItems(space));
        space.setSingleShots(createSingleShots());
        space.setDoubleShots(createDoubleShots());
        space.setBurstShots(createBurstShots());
        space.setExplosions(createExplosions());
        space.setEnemyExplosions(createEnemyExplosions());
        space.setAmmotype(1);
        space.setASTEROID_NUMBER(5);
        space.setSPACESHIP_NUMBER(3);
        space.setMAX_MOVEMENT_NUMBER(5);
        space.setITEM_NUMBER(2);
        space.setItem_score(0);
        space.setScore(0);
        space.setHighScore(0);
        return space;
    }

    public List<Spaceship> createSpaceShips(int spaceShipAmmount, int pos, int health){
        Random random = new Random();
        List<Spaceship> spaceships = new ArrayList<>();

        for (int i=0; i<spaceShipAmmount; i++) {
            spaceships.add(new Spaceship(random.nextInt(pos),1, health));
        }
        return spaceships;
    }

    public List<Asteroid> createAsteroids(int x, int health) {
        Random random = new Random();

        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            asteroids.add(new Asteroid(random.nextInt(width), (height - height) + 1, health, "small"));
        }
        return asteroids;
    }

    public List<Item> createItems(Space space){
        List<Item> items = new ArrayList<>();
        if(space.canSpawnItem()){
            Random random = new Random();
            int spawnX;
            int spawnY;
            while(true){
                spawnX = random.nextInt(space.getWidth() - 5) + 5;
                spawnY = random.nextInt(space.getHeight() - 11) + 5;
                for (Item item : space.getItems()){
                    if(item.getPosition().getY() == spawnY && item.getPosition().getX() == spawnX){
                        break;
                    }
                }
                break;
            }
            int disTime = space.getScore() + random.nextInt(300 - 100) + 100;
            space.getItems().add(new Item(spawnX,spawnY,disTime));
        }
        return items;
    }

    public List<Explosion> createExplosions() { return new ArrayList<>(); }

    public List<Explosion> createEnemyExplosions() { return new ArrayList<>(); }

    public List<SingleShot> createSingleShots() {
        return new ArrayList<>();
    }

    public List<DoubleShot> createDoubleShots() {
        return new ArrayList<>();
    }

    public List<BurstShot> createBurstShots() {
        return new ArrayList<>();
    }

    private List<Border> createBorders(int width, int height) {
        List<Border> borders = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            borders.add(new Border(i, 0));
            borders.add(new Border(i, height - 1));
        }
        for (int j = 1; j < height - 1; j++) {
            borders.add(new Border(0, j));
            borders.add(new Border(width - 1, j));
        }
        return borders;
    }

    public Player createPlayer(int x, int y){
        return new Player(x, y);
    }


}