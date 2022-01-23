package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.Entity;

public class EnemyShotViewer {

    protected Screen screen;
    protected static TextGraphics graphics;

    public EnemyShotViewer(Screen screen) {
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
    }

    public static void draw(Entity entity) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "|");
    }
}
