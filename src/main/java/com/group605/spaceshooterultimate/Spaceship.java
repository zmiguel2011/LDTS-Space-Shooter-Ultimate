package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spaceship extends Enemy {

    private List<EnemyShot> enemyShots;

    Spaceship(int x, int y, double health) {
        super(x, y, health);
        this.enemyShots = new ArrayList<>();
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "e");
    }

    public List<EnemyShot> getEnemyShots(){
        return enemyShots;
    }

    public void addEnemyShot(int x, int y){
        enemyShots.add(new EnemyShot(x, y));
    }


    @Override
    public void moveEnemy(){
        Random random = new Random();
        int val = random.nextInt(3);
        if(val == 2){
            //It will move right
            this.position.setX(this.position.getX()+ 1);
        } else if(val == 1){
            //It will move left
            this.position.setX(this.position.getX()- 1);
        } else {
            //It will stay still
            this.position.setX(this.position.getX());
        }
    }

}