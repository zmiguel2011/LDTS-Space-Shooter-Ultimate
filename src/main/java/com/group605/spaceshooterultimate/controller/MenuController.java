package com.group605.spaceshooterultimate.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.group605.spaceshooterultimate.model.MenuModel;
import com.group605.spaceshooterultimate.viewer.MenuViewer;

import java.io.IOException;

public class MenuController {

    private final MenuViewer menuViewer;
    private final MenuModel menuModel;
    private int i;

    public MenuController(Screen screen) throws IOException {
        this.menuViewer = new MenuViewer(screen);
        this.menuModel = new MenuModel();
        this.i = 1; //The place where text will be highlighted first -> 1- PLAY , 2-QUIT
    }

    public MenuViewer getMenuViewer() {
        return menuViewer;
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                if(i == 0)
                    i++;
                menuViewer.changeCursor(i);
                break;
            case ArrowDown:
                if(i == 1)
                    i--;
                menuViewer.changeCursor(i);
                break;
            case Enter:
                if(i==0)
                break;
        }
    }
}
