package com.group605.spaceshooterultimate.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.MenuModel;
import com.group605.spaceshooterultimate.state.GameOverState;
import com.group605.spaceshooterultimate.viewer.MenuViewer;

import java.io.IOException;

public class MenuController {

    private final Game game;
    private final Screen screen;
    private final MenuViewer menuViewer;
    private final MenuModel menuModel;
    private int i;

    public MenuController(Game game, Screen screen) throws IOException {
        this.game = game;
        this.screen = screen;
        this.menuViewer = new MenuViewer(screen);
        this.menuModel = new MenuModel();
        this.i = 1; //The place where text will be highlighted first -> 1- PLAY , 2-QUIT
    }

    public void manageKeyPress() throws IOException{
        Game.action action = game.handleKeyPress();
        switch (action){
            case UP:
                if(i == 0)
                    i++;
                menuViewer.changeCursor(i);
                break;
            case DOWN:
                if(i == 1)
                    i--;
                menuViewer.changeCursor(i);
                break;
            case NEXT:
                if(i==1)
                    game.changeGameState(new GameOverState(game, screen));
                else
                    game.closeTerminal();
                break;
            default:
                break;

        }
    }

    public void manageController() throws IOException{
        manageKeyPress();
        menuViewer.draw();
    }
}