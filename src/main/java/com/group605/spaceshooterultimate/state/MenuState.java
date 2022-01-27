package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.controller.MenuController;

import java.io.IOException;

public class MenuState extends GameState{
    private Game game;
    private Screen screen;
    private MenuController menuController;

    public MenuState(Game game, Screen screen) throws IOException {
        super(game, screen);
        this.menuController = new MenuController(game, screen);
    }

    @Override
    public void selectController() throws IOException{
        menuController.manageController();
    }

}
