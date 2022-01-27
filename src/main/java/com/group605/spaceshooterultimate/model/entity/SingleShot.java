package com.group605.spaceshooterultimate.model.entity;

import com.group605.spaceshooterultimate.model.entity.Bullet;

public class SingleShot extends Bullet {

    public SingleShot(int x, int y) {
        super(x, y);
        this.setDamage(1);
    }
}