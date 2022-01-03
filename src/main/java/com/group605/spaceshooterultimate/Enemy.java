package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Enemy extends Entity {
    int health;

    Enemy(int x, int y, int health) {
        super(x,y);
        this.health = health;
    }


    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }


    public void draw(TextGraphics graphics){ }

    public Position moveEnemy(){
        return new Position(position.getX(), position.getY()+1);
    }

    public void checkImpact(Enemy enemy, Player player) {
        if(enemy.position == player.position) {
            player.lifes--;
        }
    }


}
