package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;

import java.io.IOException;

public abstract class GameState {

    private Game game;
    private Screen screen;

    public GameState(Game game, Screen screen){
        this.game = game;
        this.screen = screen;
    }

    public abstract void selectController() throws IOException, InterruptedException;
}
