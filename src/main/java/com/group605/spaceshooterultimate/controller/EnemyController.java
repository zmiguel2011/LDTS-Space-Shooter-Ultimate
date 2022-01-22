package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.BurstShot;
import com.group605.spaceshooterultimate.model.entity.DoubleShot;
import com.group605.spaceshooterultimate.model.entity.Enemy;
import com.group605.spaceshooterultimate.model.entity.SingleShot;
import com.group605.spaceshooterultimate.model.space.Space;

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
}
