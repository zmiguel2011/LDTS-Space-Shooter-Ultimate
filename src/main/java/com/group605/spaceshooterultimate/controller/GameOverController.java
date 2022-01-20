package com.group605.spaceshooterultimate.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.GameOverModel;
import com.group605.spaceshooterultimate.state.GameOverState;
import com.group605.spaceshooterultimate.state.MenuState;
import com.group605.spaceshooterultimate.viewer.GameOverViewer;
import com.group605.spaceshooterultimate.viewer.MenuViewer;

import java.io.IOException;

public class GameOverController {

    private final Game game;
    private final Screen screen;
    private final GameOverViewer gameOverViewer;
    private final GameOverModel gameOverModel;
    private int i;

    public GameOverController(Game game, Screen screen) throws IOException {
        this.game = game;
        this.screen = screen;
        this.gameOverViewer = new GameOverViewer(screen);
        this.gameOverModel = new GameOverModel();
        this.i = 1; //The place where text will be highlighted first -> 1- PLAY , 2-QUIT
    }

    public GameOverViewer getGameOverViewer() {
        return gameOverViewer;
    }

    public void manageKeyPress() throws IOException{
        Game.action action = game.handleKeyPress();
        switch (action){
            case UP:
                if(i == 0)
                    i++;
                gameOverViewer.changeCursor(i);
                break;
            case DOWN:
                if(i == 1)
                    i--;
                gameOverViewer.changeCursor(i);
                break;
            case NEXT:
                if(i==1)
                    game.changeGameState(new MenuState(game, screen));
                break;
            default:
                break;

        }
    }

    public void manageController() throws IOException{
        manageKeyPress();
        gameOverViewer.draw();
    }
}
