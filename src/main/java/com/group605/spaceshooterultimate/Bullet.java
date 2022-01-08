package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Bullet {
    protected Position position;
    protected int damage;

    Bullet(int x, int y){
        this.position = new Position(x, y);
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getDamage(){
        return this.damage;
    }

    public void checkBulletImpact(Position position){

    }

    public void move(){
        this.position.setY(this.position.getY()-1);
    }

    public abstract void draw(TextGraphics graphics);
}
