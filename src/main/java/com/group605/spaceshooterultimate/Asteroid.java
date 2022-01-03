package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

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

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "A");
    }
}
