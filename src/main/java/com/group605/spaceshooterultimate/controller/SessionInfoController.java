package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.Game;
import com.group605.spaceshooterultimate.model.space.Space;

public class SessionInfoController {
    private Game game;
    private Space space;

    public SessionInfoController(Game game, Space space){
        this.game = game;
        this.space = space;
    }

    public void manageScore(){
        if (space.getScore() > game.getHighscore()) {
            game.setHighscore(space.getScore()); //Updates for every state
            space.setHighScore(space.getScore()); //Updates Real Time
        }
    }
}
