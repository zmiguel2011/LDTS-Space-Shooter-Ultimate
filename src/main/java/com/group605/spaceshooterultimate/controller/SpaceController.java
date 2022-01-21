package com.group605.spaceshooterultimate.controller;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.viewer.SpaceViewer;

import java.io.IOException;

public class SpaceController {

    private final Game game;
    private final Screen screen;
    private final SpaceViewer spaceViewer;
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private final ExplosionController explosionController;
    private final SpaceShipController spaceshipController;

    public SpaceController(Game game, Screen screen, Space space) throws IOException{
        this.game = game;
        this.screen = screen;
        this.spaceViewer = new SpaceViewer(screen, space);
        this.playerController = new PlayerController(game, space.getPlayer());
        this.asteroidController = new AsteroidController();
        this.explosionController = new ExplosionController();
        this.spaceshipController = new SpaceShipController();
    }


    public void manageController() throws IOException {
        spaceViewer.draw();
        playerController.manageKeyPress();
    }
}
