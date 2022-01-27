package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.EnemyShot;
import com.group605.spaceshooterultimate.model.entity.Explosion;
import com.group605.spaceshooterultimate.model.entity.Spaceship;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.model.space.SpaceBuilder;
import com.group605.spaceshooterultimate.viewer.EnemyShotViewer;

import java.util.Random;


public class SpaceShipController {

    private Space space;
    private SpaceBuilder builder;
    private EnemyController enemyController;
    private ExplosionController explosionController;

    public SpaceShipController(Space space){
        this.space = space;
        this.builder = new SpaceBuilder(space.getWidth(), space.getHeight(), space.getHighScore());
        this.enemyController = new EnemyController(space);
        this.explosionController = new ExplosionController(space, space.getPlayer());
    }

    public void moveSpaceship(){
        Random random1 = new Random();
        Spaceship spaceship = space.getSpaceships().get(random1.nextInt(space.getSpaceships().size())); //Randomizes a spaceship in order to choose which one to move
        if(space.canEntityMove(spaceship.getPosition()) == false){
            if(spaceship.getPosition().getX()-space.getWidth() == 1) //Right Border Solver
                spaceship.getPosition().setX(spaceship.getPosition().getX()-1);
            else{
                spaceship.getPosition().setX(spaceship.getPosition().getX()+1);//Left Border Solver
            }
        }
        else
            spaceship.moveEnemy();
    }

    /*
    public void manageSpaceship() {
        for(Spaceship spaceship : space.getSpaceships()){
            ///EnemyShotFire(spaceship);
            //isEnemyHit(spaceship); //Feito
            //if(spaceship.checkImpact(spaceship, player) || canEntityMove(spaceship.getPosition()) == false || spaceship.isDead()){
                //spaceships.remove(spaceship);
                break;
            }
            //if(isPlayerHit(player.getPosition())) // Feito em PlayerController!
                //player.lives--;
            }
        }
    }
    */

    public void manageSpaceships(){
        moveSpaceship();
        for (Spaceship spaceship : space.getSpaceships()){
            enemyController.isEnemyHit(spaceship);
            for(EnemyShot enemyShot: spaceship.getEnemyShots()) {
                if (space.canEntityMove(enemyShot.getPosition())) {
                    enemyShot.move();
                } else {
                    spaceship.getEnemyShots().remove(enemyShot);
                    break;
                }
            }
            enemyController.EnemyShotFire(spaceship);
            if(spaceship.isDead()){
                space.getEnemyExplosions().add(new Explosion(spaceship.getPosition().getX(), spaceship.getPosition().getY()));
                space.getSpaceships().remove(spaceship);
                space.setSpaceships(builder.createSpaceShips(3,space.getWidth()/2,1));
                space.getPlayer().setPlayerTracker(space.getPlayer().getPosition());
                break;
            }

            if(spaceship.checkImpact(spaceship, space.getPlayer())){ //Verifica apenas se a Spaceship bate no Player, isPlayerHit (verifica se as balas da spaceship acertam no Player) é feito no PlayerController
                space.getSpaceships().remove(spaceship);
                space.setSpaceships(builder.createSpaceShips(3,space.getWidth()/2,1));
                space.getPlayer().setLives(space.getPlayer().getLives()-1);
                explosionController.PlayerDeathExplosion();
                break;
            }
        }
    }
}
