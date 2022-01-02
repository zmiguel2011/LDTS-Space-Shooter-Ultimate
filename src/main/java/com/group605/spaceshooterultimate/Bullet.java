package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet {
    private Position position;
    private int damage;

    void setDamage(int damage){
        this.damage = damage;
    }
    int getDamage(){
        return this.damage;
    }

    void checkCollision(Player player){
        if (player.position.equals(this.position)){
            player.lifes--;
        }
    }
    Position move(){
        return new Position(position.getX(),position.getY() - 1);
    }
    void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "|");
    }
}
