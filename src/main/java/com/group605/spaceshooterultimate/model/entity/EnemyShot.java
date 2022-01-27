package com.group605.spaceshooterultimate.model.entity;


public class EnemyShot extends Bullet {

    public EnemyShot(int x, int y){
        super(x, y);
    }

    @Override
    public void move(){
        this.position.setY(this.position.getY()+1);
    }
}
