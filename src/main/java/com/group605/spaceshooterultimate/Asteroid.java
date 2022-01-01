package com.group605.spaceshooterultimate;

public class Asteroid extends Enemy {
    String size;

    Asteroid(int x, int y, int health) {
        super(x, y, health);
    }

    Asteroid(int x, int y, int health, String size) {
        super(x, y, health);
        this.size = size;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}
