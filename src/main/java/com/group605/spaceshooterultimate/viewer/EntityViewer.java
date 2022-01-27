package com.group605.spaceshooterultimate.viewer;
import com.googlecode.lanterna.screen.Screen;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.group605.spaceshooterultimate.model.entity.Entity;

public abstract class EntityViewer extends Entity{
    protected Screen screen;
    protected TextGraphics graphics;

    public EntityViewer(Screen screen){
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
    }

    public abstract void draw(Entity entity);
}
