package com.group605.spaceshooterultimate.viewer;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.entity.*;
import com.group605.spaceshooterultimate.model.space.Space;

import java.io.IOException;

public class SpaceViewer {
    protected Screen screen;
    protected TextGraphics graphics;
    protected Space space;
    protected AsteroidViewer asteroidViewer;
    protected BorderViewer borderViewer;
    protected BurstShotViewer burstShotViewer;
    protected DoubleShotViewer doubleShotViewer;
    protected SingleShotViewer singleShotViewer;
    protected ItemViewer itemViewer;
    protected PlayerViewer playerViewer;
    protected SpaceShipViewer spaceShipViewer;
    protected ExplosionViewer explosionViewer;
    protected SessionInfoViewer sessionInfoViewer;
    protected EnemyShotViewer enemyShotViewer;

    public SpaceViewer(Screen screen, Space space){
        this.screen = screen;
        this.graphics = screen.newTextGraphics();
        this.space = space;
        asteroidViewer = new AsteroidViewer(screen);
        borderViewer = new BorderViewer(screen);
        burstShotViewer = new BurstShotViewer(screen);
        doubleShotViewer = new DoubleShotViewer(screen);
        itemViewer = new ItemViewer(screen);
        playerViewer = new PlayerViewer(screen);
        singleShotViewer = new SingleShotViewer(screen);
        spaceShipViewer = new SpaceShipViewer(screen);
        explosionViewer = new ExplosionViewer(screen);
        sessionInfoViewer = new SessionInfoViewer(screen);
        enemyShotViewer = new EnemyShotViewer(screen);
    }

    public void draw() throws IOException {
        screen.clear();
        //Draw Player
        playerViewer.draw(space.getPlayer());
        //draw asteroids
        for(Asteroid asteroid : space.getAsteroids()) asteroidViewer.draw(asteroid);
        //draw borders
        for (Border border: space.getBorders()) borderViewer.draw(border);
        //draw BurstShots
        for (BurstShot burstShot : space.getBurstShots()) burstShotViewer.draw(burstShot);
        //Draw DoubleShots
        for(DoubleShot doubleshot : space.getDoubleShots()) doubleShotViewer.draw(doubleshot);
        //Draw Items
        for(Item item : space.getItems()) itemViewer.draw(item);
        //Draw SingleShots
        for(SingleShot singleShot: space.getSingleShots()) singleShotViewer.draw(singleShot);
        //Draw Spaceships
        for(Spaceship spaceShip : space.getSpaceships()){
            spaceShipViewer.draw(spaceShip);
            //Draw EnemyShots
            for(EnemyShot enemyShot: spaceShip.getEnemyShots()) {
                EnemyShotViewer.draw(enemyShot);
            }
        }
        //Draw Explosions
        for(Explosion explosion : space.getExplosions()) explosionViewer.draw(explosion);
        //Draw Enemy Explosions
        for(Explosion explosion : space.getEnemyExplosions()) explosionViewer.draw(explosion);
        //Draw Session Info
        sessionInfoViewer.draw(space);
        screen.refresh();
    }
}
