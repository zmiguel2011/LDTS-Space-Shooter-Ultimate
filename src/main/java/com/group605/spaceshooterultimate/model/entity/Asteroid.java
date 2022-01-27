package com.group605.spaceshooterultimate.model.entity;

public class Asteroid extends Enemy {
    String size;

    public Asteroid(int x, int y, double health, String size) {
        super(x, y, health);
        this.size = size;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

}