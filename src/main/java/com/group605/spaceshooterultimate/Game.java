package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.group605.spaceshooterultimate.controller.GameOverController;
import com.group605.spaceshooterultimate.controller.MenuController;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static java.awt.Cursor.CROSSHAIR_CURSOR;

public class Game {

    private final TerminalScreen screen;
    private final int width;
    private final int height;

    //FPS Variables
    private boolean running = false;
    private final int FPS = 30;
    private double averageFPS;

    Space space = new Space(100,40);
    private MenuController menuController;
    private GameOverController gameOverController;

    public Game(int width, int height) throws IOException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSpaceShooterUltimateFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        addCloseScreenListener();
        this.height=height;
        this.width=width;
        this.menuController = new MenuController(screen);
        this.gameOverController = new GameOverController(screen);
    }

    public Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);

        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private void addCloseScreenListener(){
        ((AWTTerminalFrame) screen.getTerminal()).addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                ((AWTTerminalFrame) screen.getTerminal()).dispose();
                System.exit(0);
            }
        });
    }

    public AWTTerminalFontConfiguration loadSpaceShooterUltimateFont() throws FontFormatException, IOException {
        File fontFile = new File("resources/Font/SpaceShooterUltimateGameFont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.PLAIN, 22);

        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    public Screen getScreen(){
        return screen;
    }


    public TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen terminalScreen;
        terminalScreen = new TerminalScreen(terminal);

        terminalScreen.setCursorPosition(null);
        terminalScreen.startScreen();
        terminalScreen.doResizeIfNecessary();
        return terminalScreen;
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
        gameOverController.getGameOverViewer().draw();
        //menuController.getMenuViewer().draw();
        //space.draw(screen.newTextGraphics()); //Calls the function responsible to draw the objects into the arena
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException{
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
            spaceships();
            items();
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
            if(space.getPlayer().getLives() <= 0){
                System.out.println("GAME OVER! YOU LOST!");
                if (space.getScore() > space.getHighScore()) space.SetHighScore();
                System.out.println("YOU SET A NEW HIGH SCORE: " +space.getHighScore());
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

    private void spaceships() throws InterruptedException {
        space.createSpaceships();
        space.manageSpaceship();
    }

    private void items() throws InterruptedException{
        space.createItem();
        space.manageItems();
    }
    private void processKey(KeyStroke key){
          gameOverController.processKey(key);
         //menuController.processKey(key);
        //space.processKey(key);
    }
}

