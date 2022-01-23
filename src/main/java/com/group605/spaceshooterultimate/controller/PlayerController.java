package com.group605.spaceshooterultimate.controller;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.entity.EnemyShot;
import com.group605.spaceshooterultimate.model.entity.Explosion;
import com.group605.spaceshooterultimate.model.entity.Player;
import com.group605.spaceshooterultimate.model.entity.Spaceship;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.state.GameOverState;
import com.group605.spaceshooterultimate.state.MenuState;

import java.io.IOException;

public class PlayerController {

    private Game game;
    private Player player;
    private Screen screen;
    private Space space;
    private ShootingController shootingController;
    private ExplosionController explosionController;

    public PlayerController(Game game, Screen screen, Space space){
        this.game = game;
        this.player = space.getPlayer();
        this.space = space;
        this.screen = screen;
        shootingController = new ShootingController(space, player);
        explosionController =  new ExplosionController(space, player);
    }

    public void manageKeyPress() throws IOException {
        Game.action action = game.handleKeyPress();
        switch (action) {
            case UP:
                player.moveUp();
                player.setMovementCounter(player.getMovementCounter()+1);
                break;
            case DOWN:
                player.moveDown();
                player.setMovementCounter(player.getMovementCounter()+1);
                break;
            case LEFT:
                player.moveLeft();
                player.setMovementCounter(player.getMovementCounter()+1);
                break;
            case RIGHT:
                player.moveRight();
                player.setMovementCounter(player.getMovementCounter()+1);
                break;
            case SHOOT:
                FireWeapon(); player.setShooting(true);
                break;
            case NEXT:
                break;
            case QUIT:
                game.closeTerminal();
                break;
            case SINGLE:
                space.setAmmotype(1);
                break;
            case DOUBLE:
                space.setAmmotype(2);
                break;
            case BURST:
                space.setAmmotype(3);
                break;
            default:
                break;
        }
    }

    private void FireWeapon(){
        if(space.getAmmotype() == 1){
            shootingController.singleShotFire();
        } else if (space.getAmmotype() == 2){
            shootingController.doubleShotFire();
        } else if (space.getAmmotype() == 3) {
            shootingController.burstShotFire();
        }
    }

    private boolean isPlayerHit(){  // Verifica se as balas de alguma Spaceship acertam no Player
        for (Spaceship spaceship : space.getSpaceships()) {
            for(EnemyShot enemyShot : spaceship.getEnemyShots()) {
                if (enemyShot.checkBulletImpact(player.getPosition())) {
                    spaceship.getEnemyShots().remove(enemyShot);
                    if (player.isSpawnProtectionON() == false) {
                        player.setLives(player.getLives()-1);
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    private void managePlayer() throws IOException{
        if(player.getMovementCounter() > 3) player.setSpawnProtection(false);
        if (isPlayerHit()){
            explosionController.PlayerDeathExplosion();
        }
        if(player.isPlayerDead()){
            game.changeGameState(new GameOverState(game, screen));
        }
    }

    public void manageController() throws IOException{
        manageKeyPress();
        managePlayer();
    }

}
