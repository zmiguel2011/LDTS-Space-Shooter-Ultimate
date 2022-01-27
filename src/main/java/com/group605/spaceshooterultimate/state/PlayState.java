package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.controller.PlayController;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.model.space.SpaceBuilder;

import java.io.IOException;

public class PlayState extends GameState{

    private Space space;
    private PlayController playController;

    public PlayState(Game game, Screen screen) throws IOException{
        super(game, screen);
        this.space = new SpaceBuilder(game.getWidth(), game.getHeight(), game.getHighscore()).createSpace();
        this.playController = new PlayController(game, screen, space);
    }

    @Override
    public void selectController() throws IOException, InterruptedException {
        playController.manageController();
    }

}
