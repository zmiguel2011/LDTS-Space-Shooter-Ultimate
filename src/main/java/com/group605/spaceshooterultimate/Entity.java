package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Entity {

    protected Position position;

    Entity(int x, int y){
        position = new Position(x, y);
    }

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public abstract void draw(TextGraphics graphics);
}
