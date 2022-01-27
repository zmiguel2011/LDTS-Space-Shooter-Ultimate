package com.group605.spaceshooterultimate.model.entity;


public abstract class Bullet extends Entity {
    private double damage;

    Bullet(int x, int y){
        this.position = new Position(x, y);
    }

    public void setDamage(double damage){
        this.damage = damage;
    }
    public double getDamage(){
        return this.damage;
    }

    public boolean checkBulletImpact(Position position){
        if (this.position.equals(position)) return true;
        return false;
    }

    public void move(){
        this.position.setY(this.position.getY()-1);
    }
}