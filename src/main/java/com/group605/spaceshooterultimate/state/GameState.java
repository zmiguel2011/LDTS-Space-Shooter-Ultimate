package com.group605.spaceshooterultimate.state;

import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.Game;

import java.io.IOException;

public abstract class GameState {

    private Game game;
    private Screen screen;

    public GameState(Game game, Screen screen){
        this.game = game;
        this.screen = screen;
    }

    //ABOUT STATE PATTERN INTEGRATION:
    //GameState is the interface which will be a prototype for our states
    //So we have to define which Functions we will manage/handle
    //We basically want the State to act like a middle man between our Controllers acting like a router
    //So, the MenuState should call its controller (MenuController) and so on and so forth
    //Then, MenuController will handle its View and Model which will be responsible for handle the rest of the logic and display
    //This isn't the best way of doing this because it can generate mutations, but once we are racing against the time we decided to do it this way

    public abstract void selectController() throws IOException, InterruptedException;
}
