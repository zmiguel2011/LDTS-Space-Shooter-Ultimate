package com.group605.spaceshooterultimate.model.entity;


public class Player extends Entity {
    private int lives = 3;
    private boolean shooting = false;
    private boolean SpawnProtection = true;
    private int movementCounter = 0;
    private Position RespawnPosition = new Position(60,15);
    private Position playerTracker;

    public Player(int x, int y){
        super(x,y);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lifes) {
        this.lives = lifes;
    }

    public void moveUp(){ setPosition(new Position(position.getX(), position.getY()-1));}

    public void moveDown(){ setPosition(new Position(position.getX(), position.getY()+1));}

    public void moveRight(){
        setPosition(new Position(position.getX()+1, position.getY()));
    }

    public void moveLeft(){ setPosition(new Position(position.getX()-1, position.getY()));}

    public boolean isPlayerDead() {
        if(this.getLives() == 0) return true;

        return false;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean isSpawnProtectionON() {
        return SpawnProtection;
    }

    public void setSpawnProtection(boolean spawnProtection) {
        SpawnProtection = spawnProtection;
    }

    public int getMovementCounter() {
        return movementCounter;
    }

    public void setMovementCounter(int movementCounter) {
        this.movementCounter = movementCounter;
    }

    public Position getRespawnPosition() {
        return RespawnPosition;
    }

    public Position getPlayerTracker() {
        return playerTracker;
    }

    public void setPlayerTracker(Position playerTracker) {
        this.playerTracker = playerTracker;
    }

    //Quick Easy Way to Display Player Amount of Lives Remaining in an Arcade Way
    public String displayLives(){
        return Integer.toString(getLives());
    }
}
