package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Item {
    private Position position;
    protected int disappearTime;

    Item(int x, int y, int dis){
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
    void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "L");
    }
}
