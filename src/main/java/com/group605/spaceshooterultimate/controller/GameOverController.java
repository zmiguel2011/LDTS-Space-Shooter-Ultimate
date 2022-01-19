package com.group605.spaceshooterultimate.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.GameOverModel;
import com.group605.spaceshooterultimate.viewer.GameOverViewer;
import com.group605.spaceshooterultimate.viewer.MenuViewer;

import java.io.IOException;

public class GameOverController {

    private final GameOverViewer gameOverViewer;
    private final GameOverModel gameOverModel;
    private int i;

    public GameOverController(Screen screen) throws IOException {
        this.gameOverViewer = new GameOverViewer(screen);
        this.gameOverModel = new GameOverModel();
        this.i = 1; //The place where text will be highlighted first -> 1- PLAY , 2-QUIT
    }

    public GameOverViewer getGameOverViewer() {
        return gameOverViewer;
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                if(i == 0)
                    i++;
                gameOverViewer.changeCursor(i);
                break;
            case ArrowDown:
                if(i == 1)
                    i--;
                gameOverViewer.changeCursor(i);
                break;
            case Enter:
                if(i==0)
                    break;
        }
    }
}
