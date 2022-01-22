package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.*;
import com.group605.spaceshooterultimate.model.space.Space;

public class ShootingController{

    private Space space;
    private Player player;

    public ShootingController(Space space, Player player){
        this.space = space;
        this.player = player;
    }

    public void singleShotFire(){
        space.getSingleShots().add(new SingleShot(player.getPosition().getX(), player.getPosition().getY()-1));
    }

    public void doubleShotFire(){
        space.getDoubleShots().add(new DoubleShot(player.getPosition().getX()+1, player.getPosition().getY()-1));
        space.getDoubleShots().add(new DoubleShot(player.getPosition().getX()-1, player.getPosition().getY()-1));
    }
    public void burstShotFire(){
        space.getBurstShots().add(new BurstShot(player.getPosition().getX(), player.getPosition().getY()-1));
        space.getBurstShots().add(new BurstShot(player.getPosition().getX()-1, player.getPosition().getY()-1));
        space.getBurstShots().add(new BurstShot(player.getPosition().getX()+1, player.getPosition().getY()-1));
    }

    public void manageMovement(){
        for(SingleShot singleShot : space.getSingleShots()){
            singleShot.move();
        }
        for(DoubleShot doubleShot : space.getDoubleShots()){
            doubleShot.move();
        }
        for(BurstShot burstShot : space.getBurstShots()){
            burstShot.move();
        }
    }


}
