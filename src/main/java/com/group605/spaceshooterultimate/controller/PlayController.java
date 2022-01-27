package com.group605.spaceshooterultimate.controller;


import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.space.Space;

import java.io.IOException;

public class PlayController {

    private final Game game;
    private final Space space;
    private final Screen screen;
    private final SpaceController spaceController;

    public PlayController(Game game, Screen screen, Space space) throws IOException {
        this.game = game;
        this.space = space;
        this.screen = screen;
        this.spaceController = new SpaceController(game, screen, space);
    }

    public void manageController() throws IOException, InterruptedException {
        spaceController.manageController();
    }
}
