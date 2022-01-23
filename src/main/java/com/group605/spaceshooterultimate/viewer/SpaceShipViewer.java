package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.group605.spaceshooterultimate.model.entity.Entity;
import com.googlecode.lanterna.screen.Screen;


public class SpaceShipViewer extends EntityViewer {

    public SpaceShipViewer(Screen screen){
        super(screen);
    }

    @Override
    public void draw(Entity entity){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "e");
    }
}
