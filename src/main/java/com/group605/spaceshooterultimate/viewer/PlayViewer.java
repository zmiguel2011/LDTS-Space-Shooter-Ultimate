package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class PlayViewer {
    protected Screen screen;
    protected TextGraphics graphics;
    protected int TEXT_OFFSET;

    public PlayViewer(Screen screen)throws IOException {
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
        this.TEXT_OFFSET = 3;
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.putString(screen.getTerminalSize().getColumns()/2, screen.getTerminalSize().getRows()/2, "SPACEBAR- FIRE; 1- SINGLE; 2- DOUBLE; 3- BURST");
        screen.refresh();
    }
}
