package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.Explosion;
import com.group605.spaceshooterultimate.model.entity.Spaceship;
import com.group605.spaceshooterultimate.model.space.Space;

import java.util.Random;

public class SpaceShipController {

    private Space space;
    private EnemyController enemyController;
    private ExplosionController explosionController;

    public SpaceShipController(Space space){
        this.space = space;
        this.enemyController = new EnemyController(space);
        this.explosionController = new ExplosionController(space, space.getPlayer());
    }

    public void moveSpaceship(){
        //This will randomize a SpaceShip and move it
        //As well as randomize the amount of times that movement will be repeated in order to avoid a very similar movement for every SpaceShip
        Random random1 = new Random();
        Random random2 = new Random();
        int bound = space.getSpaceships().size();
        for(int i = 0; i<random2.nextInt(space.getMAX_MOVEMENT_NUMBER()+1); i++){
            //System.out.println(bound);
        }
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

            if(spaceship.checkImpact(spaceship, space.getPlayer())){ //Verifica apenas se a Spaceship bate no Player, isPlayerHit (verifica se as balas da spaceship acertam no Player) é feito no PlayerController
                space.getSpaceships().remove(spaceship);
                space.getPlayer().setLives(space.getPlayer().getLives()-1);
                explosionController.PlayerDeathExplosion();
                break;
            }

            if(/* space.canEntityMove(spaceship.getPosition()) ||*/ spaceship.isDead()){
                space.getSpaceships().remove(spaceship);
                break;
            }

        }
    }
}
