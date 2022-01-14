package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;

public class Game {

    private Screen screen;

    //FPS Variables
    private boolean running = false;
    private int FPS = 30;
    private double averageFPS;


    Space space = new Space(100,40);

    public Game(){
        try{
            TerminalSize terminalSize = new TerminalSize(200, 60);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen getScreen(){
        return screen;
    }


    public void createTerminal() {
        try{
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTerminal() {
        try{
            screen.close(); // screen must be started
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() throws IOException{
        screen.clear();
        space.draw(screen.newTextGraphics()); //Calls the function responsible to draw the objects into the arena
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException{
        createTerminal();
        running = true;

        long startTime;
        long URDTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000 / FPS;

        // GAME LOOP
        while(running){

            startTime = System.nanoTime();

            draw(); //Function that draws the objects on the screen
            asteroids();
            URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMillis;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e){
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == maxFrameCount){
                averageFPS = 1000.0 / ((totalTime / frameCount) / 100000000);
                frameCount = 0;
                totalTime = 0;

            }
            if(space.getPlayer().getLives() == 0){
                System.out.println("GAME OVER! YOU LOST!");
                closeTerminal();
            }
            KeyStroke key = screen.pollInput(); //Reads the Key input; NOTE: pollInput()-> If no Input was read then it returns null
            if(key == null)
                continue;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')   { //Verifies if it was 'q'
                closeTerminal();
                running = false;
            }
            if (key.getKeyType() == KeyType.EOF) { //Verifies if EOF got reached
                running = false;
            }
            processKey(key);
        }
    }

    private void asteroids() throws InterruptedException {
        space.createAsteroids();
        space.manageAsteroid();
    }


    private void processKey(KeyStroke key){
        space.processKey(key);
    }
}

