package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.controller.GameOverController;

import java.io.IOException;

public class GameOverState extends GameState{

    private Game game;
    private Screen screen;
    private GameOverController gameOverController;

    public GameOverState(Game game, Screen screen) throws IOException {
        super(game, screen);
        this.gameOverController = new GameOverController(game, screen);
    }

    @Override
    public void selectController() throws IOException{
        gameOverController.manageController();
    }

}
