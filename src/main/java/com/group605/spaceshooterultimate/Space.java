package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Space {

    private int width, height;
    private Player player;

    //TEXT OFFSET VALUES
    private int LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
    private int LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE = height-10;

    Space(int width, int height){
        this.width = width;
        this.height = height;
        this.player = new Player(10,10);

        LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE = height-10;
    }

    public void draw(TextGraphics graphics) throws IOException {
        //Draws Game Background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#737373"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        //Draws Character
        player.draw(graphics);

        //Lifes Remaining Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE, LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE), player.displayLifes());
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp:
                movePlayer(player.moveUp());
                break;
            case ArrowDown:
                movePlayer(player.moveDown());
                break;
            case ArrowRight:
                movePlayer(player.moveRight());
                break;
            case ArrowLeft:
                movePlayer(player.moveLeft());
                break;
            case F1: //Added for testing if player lifes display is working correctly
                player.deductPlayerLifes();
                break;
            case F2:
                player.addLifes();
                break;
        }
    }

    public void movePlayer(Position position){
        player.setPosition(position);
    }

}
