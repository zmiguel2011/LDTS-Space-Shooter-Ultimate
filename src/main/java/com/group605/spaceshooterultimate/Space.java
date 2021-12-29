package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class Space {

    private int width, height;

    Space(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) throws IOException {
        //Draws Character
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(10, 10), "X");
    }

}
