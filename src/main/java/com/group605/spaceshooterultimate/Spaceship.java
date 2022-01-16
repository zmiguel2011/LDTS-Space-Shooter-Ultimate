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

    Spaceship(int x, int y, int health) {
        super(x, y, health);
        this.enemyShots = new ArrayList<>();
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "S");
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
        this.position.setX(this.position.getY()+ random.nextInt(100+1));
    }


}
