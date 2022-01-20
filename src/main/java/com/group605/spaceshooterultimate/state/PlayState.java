package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;

public class PlayState extends GameState{

    private Game game;
    private Screen screen;

    public PlayState(Game game, Screen screen){
        super(game, screen);
    }

    @Override
    public void selectController() {

    }

}
