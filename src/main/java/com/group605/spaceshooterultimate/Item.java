package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Item {
    private Position position;

    Item(int x, int y){
        position = new Position(x,y);
    }

    void checkCollision(Player player){
        if(player.position.equals(position)){
            player.lives++;
        }
    }
    void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "L");
    }
}
