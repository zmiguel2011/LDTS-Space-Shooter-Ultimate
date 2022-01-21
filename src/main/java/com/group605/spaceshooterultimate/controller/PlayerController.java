package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.entity.Player;
import com.group605.spaceshooterultimate.state.MenuState;

import java.io.IOException;

public class PlayerController {

    private Game game;
    private Player player;

    public PlayerController(Game game, Player player){
        this.game = game;
        this.player = player;
    }

    public void manageKeyPress() throws IOException {
        Game.action action = game.handleKeyPress();
        switch (action) {
            case UP: { player.moveUp(); break; }
            case DOWN: { player.moveDown(); break; }
            case LEFT: { player.moveLeft(); break; }
            case RIGHT: { player.moveRight(); break; }
            case SHOOT: break;
            case NEXT: break;
            case QUIT: { game.closeTerminal(); break; }
            default:
                break;
        }
    }

    /*
    private void FireWeapon(){
        if(ammotype == 1){
            singleShotFire();
        } else if (ammotype == 2){
            doubleShotFire();
        } else if (ammotype == 3) {
            burstShotFire();
        }
    }

    private List<SingleShot> singleShotFire(){
        singleShots.add(new SingleShot(player.position.getX(), player.position.getY()-1));
        return singleShots;
    }

    private List<DoubleShot> doubleShotFire(){
        doubleShots.add(new DoubleShot(player.position.getX()+1, player.position.getY()-1));
        doubleShots.add(new DoubleShot(player.position.getX()-1, player.position.getY()-1));
        return doubleShots;
    }
    private List<BurstShot> burstShotFire(){
        burstShots.add(new BurstShot(player.position.getX(), player.position.getY()-1));
        burstShots.add(new BurstShot(player.position.getX()-1, player.position.getY()-1));
        burstShots.add(new BurstShot(player.position.getX()+1, player.position.getY()-1));
        return burstShots;
    }
    */

    public void manageController() throws IOException{
        manageKeyPress();
    }
}
