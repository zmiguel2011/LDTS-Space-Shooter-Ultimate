package com.group605.spaceshooterultimate.model.entity;


public class Item extends Entity{
    protected int disappearTime;

    public Item(int x, int y, int dis){
        position = new Position(x,y);
        this.disappearTime = dis;
    }

    public Position getPosition(){
        return this.position;
    }
}
