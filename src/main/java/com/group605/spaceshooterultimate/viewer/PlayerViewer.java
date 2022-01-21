package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.Entity;

public class PlayerViewer extends EntityViewer {

    public PlayerViewer(Screen screen){ super(screen); }

    @Override
    public void draw(Entity entity){
        graphics.setForegroundColor(TextColor.Factory.fromString("#00fffb"));
        graphics.putString(new TerminalPosition(entity.getPosition().getX(), entity.getPosition().getY()), "s");
    }
}
