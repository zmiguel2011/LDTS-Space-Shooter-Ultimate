package com.group605.spaceshooterultimate.model.entity;

public class BurstShot extends Bullet {

    BurstShot(int x, int y) {
        super(x, y);
        this.setDamage(0.34);
    }
}