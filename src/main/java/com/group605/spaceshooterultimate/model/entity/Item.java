package com.group605.spaceshooterultimate.model.entity;

import com.group605.spaceshooterultimate.model.entity.Position;

public class Item extends Entity{
    protected int disappearTime;

    public Item(int x, int y, int dis){
        position = new Position(x,y);
        this.disappearTime = dis;
    }


    boolean checkCollision(Position position){
        if(position.equals(this.position)){
            return true;
        }
        return false;
    }
    public Position getPosition(){
        return this.position;
    }
}
