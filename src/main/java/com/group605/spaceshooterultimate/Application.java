package com.group605.spaceshooterultimate;

import java.awt.*;
import java.io.IOException;

public class Application{
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException {
        Game game = new Game(120, 30);
        game.run();
    }
}
