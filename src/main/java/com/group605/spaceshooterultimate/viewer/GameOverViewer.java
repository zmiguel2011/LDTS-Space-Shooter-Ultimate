package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.FileReader;

import java.io.IOException;
import java.util.List;

public class GameOverViewer{

    protected Screen screen;
    protected TextGraphics graphics;
    protected int TEXT_OFFSET;
    protected String cursorstr1 = ">>> PLAY p", cursorstr2 ="QUIT";
    protected int highscore, score;
    protected String str;

    public GameOverViewer(Screen screen, int highscore, int score)throws IOException{
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
        this.highscore = highscore;
        this.score = score;
        TEXT_OFFSET = 3;
    }

    public void changeCursor(int i){
        if(i==1){
            cursorstr1 = ">>>" + "PLAY AGAIN p";
            cursorstr2 = "QUIT";
        }
        else{
            cursorstr1 = "PLAY AGAIN p";
            cursorstr2 = ">>>" + "QUIT";
        }
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.putString(screen.getTerminalSize().getColumns()/2+TEXT_OFFSET, screen.getTerminalSize().getRows()/2, "GAME OVER g YOU LOST!");
        graphics.putString(screen.getTerminalSize().getColumns()/2+2*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + TEXT_OFFSET, "SESSION HIGH SCORE: " + highscore);
        graphics.putString(screen.getTerminalSize().getColumns()/2+3*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + 2*TEXT_OFFSET, "SESSION SCORE: " + score);
        graphics.putString(screen.getTerminalSize().getColumns()/2+4*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + 3*TEXT_OFFSET, cursorstr1);
        graphics.putString(screen.getTerminalSize().getColumns()/2+4*TEXT_OFFSET, screen.getTerminalSize().getRows()/2 + 4*TEXT_OFFSET, cursorstr2);
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        drawSpaceship(new FileReader().readFile("resources/TextImages/Spaceship"), "#ffffff");
        screen.refresh();
    }

    private void drawSpaceship(List<String> spaceship, String hexColor) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString(hexColor));
        for(int i=0;i<spaceship.size();i++){
            if(spaceship.get(i) != " "){
                graphics.putString(1,10+i,spaceship.get(i), SGR.BOLD);
            }
        }
    }

}
