package com.group605.spaceshooterultimate.model.entity;

import com.group605.spaceshooterultimate.model.entity.Position;

public abstract class Entity {

    protected Position position;

    Entity(int x, int y){
        position = new Position(x, y);
    }

    public Entity() {}

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }
}
