package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.controller.GameOverController;

import java.io.IOException;

public class GameOverState extends GameState{

    private GameOverController gameOverController;

    public GameOverState(Game game, Screen screen, int highscore, int score) throws IOException {
        super(game, screen);
        this.gameOverController = new GameOverController(game, screen, highscore, score);
    }

    @Override
    public void selectController() throws IOException{
        gameOverController.manageController();
    }

}
