package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.Player;

public class PlayerViewer {

    protected Screen screen;
    protected TextGraphics graphics;

    public PlayerViewer(Screen screen){
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
    }

    public void draw(Player player){
        if (player.isSpawnProtectionON()) graphics.setForegroundColor(TextColor.Factory.fromString("#ffd500"));
        else graphics.setForegroundColor(TextColor.Factory.fromString("#00fffb"));
        graphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "s");
    }
}
