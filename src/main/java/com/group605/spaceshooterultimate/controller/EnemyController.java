package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.*;
import com.group605.spaceshooterultimate.model.space.Space;

import java.util.Random;

public class EnemyController {

    private Space space;

    public EnemyController(Space space){
        this.space = space;
    }

    public boolean isEnemyHit(Enemy enemy){
        for(SingleShot singleShot : space.getSingleShots()){
            if(singleShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - singleShot.getDamage());
                space.setScore(space.getScore()+100);
                return true;
            }
        }
        for(DoubleShot doubleShot : space.getDoubleShots()){
            if(doubleShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - doubleShot.getDamage());
                space.setScore(space.getScore()+100);
                return true;
            }
        }
        for(BurstShot burstShot : space.getBurstShots()){
            if(burstShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - burstShot.getDamage());
                space.setScore(space.getScore()+100);
                return true;
            }
        }
        return false;
    }

    public void EnemyShotFire(Spaceship spaceship) {
        Random random = new Random();
        int distance = random.nextInt(5);

        if (spaceship.getEnemyShots().size() == 0) {
            spaceship.addEnemyShot(spaceship.getPosition().getX(), spaceship.getPosition().getY()+1);
        }
        else if (spaceship.getEnemyShots().get(spaceship.getEnemyShots().size() - 1).getPosition().getY() - space.getPlayer().getPosition().getY() == distance) {
            spaceship.getEnemyShots().add(new EnemyShot(spaceship.getPosition().getX(), spaceship.getPosition().getY()+1));
        }

    }
}
