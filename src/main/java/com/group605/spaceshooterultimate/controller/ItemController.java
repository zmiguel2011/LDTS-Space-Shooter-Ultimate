package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.entity.Item;
import com.group605.spaceshooterultimate.model.entity.Player;
import com.group605.spaceshooterultimate.model.space.Space;
import com.group605.spaceshooterultimate.model.space.SpaceBuilder;

public class ItemController {
    private Space space;
    private SpaceBuilder builder;

    public ItemController(Space space){
        this.space = space;
        this.builder = new SpaceBuilder(space.getWidth(),space.getHeight(), space.getHighScore());
    }

    public void checkCollision(Player player){
        for(Item item : space.getItems()){
            if(item.getPosition().equals(player.getPosition())){
                if(player.getLives() == 3){
                    space.setScore(space.getScore() + 100);
                    space.getItems().remove(item);
                    break;
                }
                player.setLives(player.getLives() + 1);
                space.getItems().remove(item);
                break;
            }
        }
    }

    public void refill(){
        if(space.getItems().size()  < space.getITEM_NUMBER()){
            builder.createItems(space);
        }
    }
    public void manageItems(){
        refill();
        checkCollision(space.getPlayer());
    }
}
