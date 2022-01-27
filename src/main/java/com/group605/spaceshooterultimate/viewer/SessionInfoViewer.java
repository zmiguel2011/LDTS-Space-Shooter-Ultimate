package com.group605.spaceshooterultimate.viewer;


import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.space.Space;


public class SessionInfoViewer {

    protected Screen screen;
    protected TextGraphics graphics;

    public SessionInfoViewer(Screen screen) {
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
    }

    public void draw(Space space) {
        graphics.putString(70, 27, "SPACEBAR- FIRE; 1- SINGLE; 2- DOUBLE; 3- BURST");
        graphics.putString(35, 27, "SESSION SCORE: " + space.getScore());
        graphics.putString(5, 27, "SESSION HIGH SCORE: "+ space.getHighScore());
        graphics.putString(57, 27, "LIVES: " + space.getPlayer().getLives());
    }
}
