package com.group605.spaceshooterultimate;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application{
    public static void main(String[] args) throws IOException, InterruptedException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.run();
    }
}
