package com.group605.spaceshooterultimate.controller;

import com.group605.spaceshooterultimate.model.space.Space;

public class SessionInfoController {
    private Space space;

    public SessionInfoController(Space space){
        this.space = space;
    }

    public void manageScore(){
        if (space.getScore() > space.getHighScore()) space.setHighScore(space.getScore());
    }
}
