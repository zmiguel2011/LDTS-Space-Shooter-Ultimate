package com.group605.spaceshooterultimate.controller;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.viewer.SpaceViewer;

import java.io.IOException;

@SuppressWarnings("unused")
public class SpaceController {
    private final Game game;
    private final Screen screen;
    private final Space space;
    private final SpaceViewer spaceViewer;
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private ExplosionController explosionController;
    private final SpaceShipController spaceshipController;
    private final ShootingController shootingController;
    private final SessionInfoController sessionInfoController;
    private final ItemController itemController;

    public SpaceController(Game game, Screen screen, Space space) throws IOException{
        this.game = game;
        this.screen = screen;
        this.space = space;
        this.spaceViewer = new SpaceViewer(screen, space);
        this.playerController = new PlayerController(game, screen, space);
        this.asteroidController = new AsteroidController(space);
        this.explosionController = new ExplosionController(space, space.getPlayer());
        this.spaceshipController = new SpaceShipController(space);
        this.shootingController = new ShootingController(space, space.getPlayer());
        this.explosionController = new ExplosionController(space,space.getPlayer());
        this.sessionInfoController = new SessionInfoController(game, space);
        this.itemController = new ItemController(space);
    }

    public void manageController() throws IOException, InterruptedException {
        spaceViewer.draw();
        sessionInfoController.manageScore();
        playerController.manageController();
        shootingController.manageMovement();
        spaceshipController.manageSpaceships();
        asteroidController.manageAsteroids();
        explosionController.manageExplosions();
        itemController.manageItems();
    }
}
