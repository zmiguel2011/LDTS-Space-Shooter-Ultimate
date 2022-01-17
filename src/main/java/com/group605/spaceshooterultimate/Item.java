package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Item {
    private Position position;
    private int scoreDisappear;

    Item(int x, int y, int scoreDisappear){
        position = new Position(x,y);
        this.scoreDisappear = scoreDisappear;
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
    public void move(){
         this.position.setY(this.position.getY() + 1);
    }
    void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "L");
    }
}
