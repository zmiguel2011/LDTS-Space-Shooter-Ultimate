package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Asteroid extends Enemy {
    String size;

    public Asteroid(int x, int y, double health, String size) {
        super(x, y, health);
        this.size = size;
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "A");
    }
}