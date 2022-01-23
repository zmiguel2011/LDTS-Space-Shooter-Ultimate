package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.Explosion;
import com.group605.spaceshooterultimate.model.entity.Player;
import com.group605.spaceshooterultimate.model.space.Space;

public class ExplosionController {

    private Space space;
    private Player player;

    public ExplosionController(Space space, Player player){
        this.space = space;
        this.player = player;
    }

    public void PlayerDeathExplosion(){
        space.getExplosions().add(new Explosion(player.getPosition().getX(), player.getPosition().getY()));
        player.setShooting(false);
        player.setSpawnProtection(true);
        player.setMovementCounter(0);
        if (player.getLives() > 0) player.setPosition(player.getRespawnPosition());
    }
}
