package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MenuViewer{

    protected Screen screen;
    protected TextGraphics graphics;
    protected int TEXT_OFFSET;
    protected String cursorstr1 = ">>> PLAY p", cursorstr2 ="QUIT";

    public MenuViewer(Screen screen)throws IOException{
       this.screen = screen;
       this.graphics = screen.newTextGraphics();
       TEXT_OFFSET = 3;
    }

    public void changeCursor(int i){
        if(i==1){
            cursorstr1 = ">>>" + "PLAY p";
            cursorstr2 = "QUIT";
        }
        else{
            cursorstr1 = "PLAY p";
            cursorstr2 = ">>>" + "QUIT";
        }
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.putString(screen.getTerminalSize().getColumns()/2, screen.getTerminalSize().getRows()/2, "SPACE SHOOTER ULTIMATE");
        graphics.putString(screen.getTerminalSize().getColumns()/2+TEXT_OFFSET/2, screen.getTerminalSize().getRows()/2 + TEXT_OFFSET, "SESSION HIGH SCORE: ");
        graphics.putString(screen.getTerminalSize().getColumns()/2+2*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + 2*TEXT_OFFSET, cursorstr1);
        graphics.putString(screen.getTerminalSize().getColumns()/2+2*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + 3*TEXT_OFFSET, cursorstr2);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        screen.refresh();
    }
}
