package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.FileReader;

import java.io.IOException;
import java.util.List;

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
        //graphics.putString(screen.getTerminalSize().getColumns()/2, screen.getTerminalSize().getRows()/2, "SPACE SHOOTER ULTIMATE"); OLD TITLE
        graphics.putString(screen.getTerminalSize().getColumns()/2, screen.getTerminalSize().getRows()/2 + TEXT_OFFSET, cursorstr1);
        graphics.putString(screen.getTerminalSize().getColumns()/2, screen.getTerminalSize().getRows()/2 + 2*TEXT_OFFSET, cursorstr2);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        drawTitle(new FileReader().readFile("resources/SpaceShooterTitle"), "#ffffff");
        screen.refresh();
    }

    private void drawTitle(List<String> title, String hexColor) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));
        for(int i=0;i<title.size();i++){
            if(title.get(i) != " "){
                graphics.putString(screen.getTerminalSize().getColumns()/4,10+i,title.get(i), SGR.BOLD);
            }
        }
    }
}
