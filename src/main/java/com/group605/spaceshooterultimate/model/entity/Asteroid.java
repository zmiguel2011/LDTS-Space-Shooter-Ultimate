package com.group605.spaceshooterultimate.model.entity;

public class Asteroid extends Enemy {
    String size;

    public Asteroid(int x, int y, double health, String size) {
        super(x, y, health);
        this.size = size;
    }
}