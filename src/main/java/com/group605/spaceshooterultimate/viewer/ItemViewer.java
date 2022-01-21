package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.Entity;

public class ItemViewer extends EntityViewer {

    public ItemViewer(Screen screen){
        super(screen);
    }
    //O ZE MIGUEL NAO CONSEGUE ABRIR FILIPE AHAHJAHAAHHA
    //GANDA TONO
    //AHAHAHHAHAH O ZE AINDA NAO CONSEGUE VER QUE NABO XDDDDDDDDDDDDDDDDDDDDDD
    //BOT
    @Override
    public void draw(Entity entity){
        graphics.setForegroundColor(TextColor.Factory.fromString("#ff007b"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "h");
    }
}
