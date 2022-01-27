package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.Asteroid;
import com.group605.spaceshooterultimate.model.entity.Spaceship;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.model.space.SpaceBuilder;

import java.util.Random;

public class AsteroidController {
    private Space space;
    private SpaceBuilder builder;
    private EnemyController enemyController;
    private ExplosionController explosionController;

    public AsteroidController(Space space) {
        this.space = space;
        this.builder = new SpaceBuilder(space.getWidth(), space.getHeight(), space.getHighScore());
        this.enemyController = new EnemyController(space);
        this.explosionController = new ExplosionController(space, space.getPlayer());
    }

    public void refill(){
        Random random = new Random();

        if(space.getAsteroids().size() < space.getASTEROID_NUMBER()){
            if (space.getScore() <= 2500) space.getAsteroids().add(new Asteroid(random.nextInt(space.getWidth()), 1, 0.3, "small"));
            else if (space.getScore() >= 2500 & space.getScore() <= 5000) space.getAsteroids().add(new Asteroid(random.nextInt(space.getWidth()), +1, 0.5, "medium"));
            else space.getAsteroids().add(new Asteroid(random.nextInt(space.getWidth()), 1, 1, "large"));
        }
    }

    public void manageAsteroids()
    {
        refill();
        for(Asteroid asteroid : space.getAsteroids()){
            enemyController.isEnemyHit(asteroid);
            asteroid.moveEnemy();
            if(space.canEntityMove(asteroid.getPosition()) == false){
                space.getAsteroids().remove(asteroid);
                break;
            }
            if(asteroid.isDead()){
                space.getAsteroids().remove(asteroid);
                explosionController.PlayerDeathExplosion();
                break;
            }
            if(asteroid.checkImpact(asteroid, space.getPlayer())){
                if (space.getPlayer().isSpawnProtectionON() == false) {
                    space.getPlayer().setLives(space.getPlayer().getLives()-1);
                    explosionController.PlayerDeathExplosion();
                }
                space.getAsteroids().remove(asteroid);
                break;
            }
        }
    }
}
