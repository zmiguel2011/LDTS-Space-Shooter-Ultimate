package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Player extends Entity{
    int lives = 3;
    private boolean spawnProtection;

    public Player(int x, int y){
        super(x,y);
        this.spawnProtection = true;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lifes) {
        this.lives = lifes;
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY()-1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY()+1);
    }

    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }

    public Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }

    public boolean getSpawnProtection() {
        return spawnProtection;
    }

    public void setSpawnProtection(boolean spawnProtection) {
        this.spawnProtection = spawnProtection;
    }

    public void draw(TextGraphics graphics){
        if (spawnProtection) graphics.setForegroundColor(TextColor.Factory.fromString("#ffe600"));
        else if (spawnProtection == false) graphics.setForegroundColor(TextColor.Factory.fromString("#00fffb"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "s");
    }

    //Quick Easy Way to Display Player Amount of Lives Remaining in an Arcade Way
    public String displayLives(){
        return Integer.toString(getLives());
    }
}
