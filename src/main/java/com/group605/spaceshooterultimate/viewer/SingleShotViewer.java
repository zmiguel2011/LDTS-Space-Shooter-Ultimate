package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.Entity;


public class SingleShotViewer extends EntityViewer{

    public SingleShotViewer(Screen screen){
        super(screen);
    }

    @Override
    public void draw(Entity entity) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#6f00ff"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "|");
    }
}
