package com.group605.spaceshooterultimate.model.entity;

import com.group605.spaceshooterultimate.model.entity.Bullet;

public class DoubleShot extends Bullet {

    DoubleShot(int x, int y) {
        super(x, y);
        this.setDamage(0.5);
    }
}