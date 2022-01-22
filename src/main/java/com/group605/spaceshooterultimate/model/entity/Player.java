package com.group605.spaceshooterultimate.model.entity;


public class Player extends Entity {
    int lives = 3;

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

    //Quick Easy Way to Display Player Amount of Lives Remaining in an Arcade Way
    public String displayLives(){
        return Integer.toString(getLives());
    }
}
