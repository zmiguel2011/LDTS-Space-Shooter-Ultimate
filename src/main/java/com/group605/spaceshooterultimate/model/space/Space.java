package com.group605.spaceshooterultimate.model.space;

import com.group605.spaceshooterultimate.model.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Space{
    private int width, height;
    private Player player;
    private List<Border> borders;
    private List<SingleShot> singleShots;
    private List<DoubleShot> doubleShots;
    private List<BurstShot> burstShots;
    private List<Asteroid> asteroids;
    private List<Spaceship> spaceships;
    private List<Explosion> explosions;
    private List<Explosion> enemyExplosions;
    private List<Item> items;
    private int ASTEROID_NUMBER; //Sets how many Asteroids will spawn together
    private int SPACESHIP_NUMBER; //Sets how many SpaceShip will spawn together
    private int MAX_MOVEMENT_NUMBER;
    private int ITEM_NUMBER;
    private int ammotype; //1- Single, 2-Double, 3-Burst
    private int item_score; // checks if item for certain score has already spawned;
    private int score;
    private int highScore;

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setItem_score(int item_score) {
        this.item_score = item_score;
    }

    public void setAmmotype(int ammotype) {
        this.ammotype = ammotype;
    }

    public void setASTEROID_NUMBER(int ASTEROID_NUMBER) {
        this.ASTEROID_NUMBER = ASTEROID_NUMBER;
    }

    public void setAsteroids(List<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }

    public void setBorders(List<Border> borders) {
        this.borders = borders;
    }

    public void setBurstShots(List<BurstShot> burstShots) {
        this.burstShots = burstShots;
    }

    public void setDoubleShots(List<DoubleShot> doubleShots) {
        this.doubleShots = doubleShots;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSingleShots(List<SingleShot> singleShots) {
        this.singleShots = singleShots;
    }

    public void setSpaceships(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setSPACESHIP_NUMBER(int SPACESHIP_NUMBER) {
        this.SPACESHIP_NUMBER = SPACESHIP_NUMBER;
    }

    public void setMAX_MOVEMENT_NUMBER(int MAX_MOVEMENT_NUMBER) {
        this.MAX_MOVEMENT_NUMBER = MAX_MOVEMENT_NUMBER;
    }

    public void setITEM_NUMBER(int ITEM_NUMBER) {
        this.ITEM_NUMBER = ITEM_NUMBER;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Border> getBorders() {
        return borders;
    }

    public List<SingleShot> getSingleShots() {
        return singleShots;
    }

    public List<DoubleShot> getDoubleShots() {
        return doubleShots;
    }

    public List<BurstShot> getBurstShots() {
        return burstShots;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public List<Spaceship> getSpaceships() {
        return spaceships;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getASTEROID_NUMBER() {
        return ASTEROID_NUMBER;
    }

    public int getSPACESHIP_NUMBER() {
        return SPACESHIP_NUMBER;
    }

    public int getMAX_MOVEMENT_NUMBER() {
        return MAX_MOVEMENT_NUMBER;
    }

    public int getITEM_NUMBER() {
        return ITEM_NUMBER;
    }

    public int getAmmotype() {
        return ammotype;
    }

    public boolean canEntityMove(Position position){
        for(Border border : borders){
            if(position.equals(border.getPosition())){
                return false;
            }
        }
        return true;
    }

    public boolean canSpawnItem(){
        if(score % 100 == 0 && score != 0 && item_score != score && items.size() < ITEM_NUMBER){
            item_score = score;
            return true;
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getItem_score() {
        return item_score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void ScoreIncrement(int inc) {
        score = score + inc;
    }

    public List<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(List<Explosion> explosions) {
        this.explosions = explosions;
    }

    public List<Explosion> getEnemyExplosions() {
        return enemyExplosions;
    }

    public void setEnemyExplosions(List<Explosion> enemyExplosions) {
        this.enemyExplosions = enemyExplosions;
    }
}
