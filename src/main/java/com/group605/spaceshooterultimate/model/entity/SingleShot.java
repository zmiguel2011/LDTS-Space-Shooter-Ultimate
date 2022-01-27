package com.group605.spaceshooterultimate.model.entity;


public class SingleShot extends Bullet {

    public SingleShot(int x, int y) {
        super(x, y);
        this.setDamage(1);
    }
}