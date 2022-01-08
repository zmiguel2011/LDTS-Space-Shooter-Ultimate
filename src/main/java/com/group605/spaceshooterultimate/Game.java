package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class Game {

    private Screen screen;

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
        while(true){
            draw(); //Function that draws the objects on the screen
            asteroids();
            if(space.getPlayer().getLifes() == 0){
                System.out.println("GAME OVER! YOU LOST!");
                closeTerminal();
            }
            KeyStroke key = screen.pollInput(); //Reads the Key input; NOTE: pollInput()-> If no Input was read then it returns null
            if(key == null)
                continue;
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')   { //Verifies if it was 'q'
                closeTerminal();
            }
            if (key.getKeyType() == KeyType.EOF) { //Verifies if EOF got reached
                break;
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

