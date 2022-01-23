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
    private final ShootingController shootingController;

    public SpaceController(Game game, Screen screen, Space space) throws IOException{
        this.game = game;
        this.screen = screen;
        this.spaceViewer = new SpaceViewer(screen, space);
        this.playerController = new PlayerController(game, screen, space);
        this.asteroidController = new AsteroidController();
        this.explosionController = new ExplosionController(space, space.getPlayer());
        this.spaceshipController = new SpaceShipController(space);
        this.shootingController = new ShootingController(space, space.getPlayer());
    }


    public void manageController() throws IOException {
        spaceViewer.draw();
        playerController.manageController();
        shootingController.manageMovement();
        spaceshipController.manageSpaceships();
    }
}
