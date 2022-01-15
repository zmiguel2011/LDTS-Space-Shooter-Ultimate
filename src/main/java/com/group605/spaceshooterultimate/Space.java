package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Space {

    private int width, height;
    private int ammotype; //1- Single, 2-Double, 3-Burst
    private Player player;
    private List<Border> borders;
    private List<SingleShot> singleShots;
    private List<DoubleShot> doubleShots;
    private List<BurstShot> burstShots;
    private List<EnemyShot> enemyShots;
    private List<Asteroid> asteroids;
    private List<Spaceship> spaceships;
    private int ASTEROID_NUMBER = 5; //Sets how many Asteroids will spawn together
    private int SPACESHIP_NUMBER = 3; //Sets how many Asteroids will spawn together
    private int score = 0;
    private int highScore = 0;

    //TEXT OFFSET VALUES
    private int LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE;
    private int LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE;
    private int CYCLEAMMO_TEXT_DISPLAY_X_OFFSET_VALUE;
    private int CYCLEAMMO_TEXT_DISPLAY_Y_OFFSET_VALUE;
    private int SESSIONINFO_TEXT_DISPLAY_X_OFFSET_VALUE;
    private int SESSIONINFO_TEXT_DISPLAY_Y_OFFSET_VALUE;
    private int SCORE_TEXT_DISPLAY_X_OFFSET_VALUE;
    private int SCORE_TEXT_DISPLAY_Y_OFFSET_VALUE;
    private int HIGHSCORE_TEXT_DISPLAY_X_OFFSET_VALUE;
    private int HIGHSCORE_TEXT_DISPLAY_Y_OFFSET_VALUE;



    Space(int width, int height){
        this.width = width;
        this.height = height;
        this.player = new Player(10,10);
        this.borders = createBorders();
        this.singleShots = new ArrayList<>();
        this.doubleShots = new ArrayList<>();
        this.burstShots = new ArrayList<>();
        this.enemyShots = new ArrayList<>();
        this.ammotype = 1; //Sets Single as Default Ammo Type
        this.asteroids = new ArrayList<>();
        this.spaceships = new ArrayList<>();

        LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE = height-10;
        CYCLEAMMO_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        CYCLEAMMO_TEXT_DISPLAY_Y_OFFSET_VALUE = height-13;
        SCORE_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        SCORE_TEXT_DISPLAY_Y_OFFSET_VALUE = height-16;
        HIGHSCORE_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        HIGHSCORE_TEXT_DISPLAY_Y_OFFSET_VALUE = height-19;
        SESSIONINFO_TEXT_DISPLAY_X_OFFSET_VALUE = width+10;
        SESSIONINFO_TEXT_DISPLAY_Y_OFFSET_VALUE = height-22;
    }

    public void draw(TextGraphics graphics) throws IOException {
        //Draws Game Background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        //Draw Map Borders
        for(Border border : borders){
            border.draw(graphics);
        }

        //Draw Single Shot Bullets
        for(SingleShot singleShot : singleShots){
            singleShot.draw(graphics);
            singleShot.move();
        }

        //Draw Double Shot Bullets
        for(DoubleShot doubleShot : doubleShots){
            doubleShot.draw(graphics);
            doubleShot.move();
        }

        //Draw Burst Shot Bullets
        for(BurstShot burstShot : burstShots){
            burstShot.draw(graphics);
            burstShot.move();
        }

        //Draws Asteroids
        for(Asteroid asteroid : asteroids){
            asteroid.draw(graphics);
        }

        //Draws Spaceships
        for(Spaceship spaceship : spaceships){
            spaceship.draw(graphics);
        }

        //Draw Enemy Shot Bullets
        for(EnemyShot enemyShot : enemyShots){
            if(canEntityMove(enemyShot.position)){
                enemyShot.move();
            }
            else {
                enemyShots.remove(enemyShot);
                break;
            }
            enemyShot.draw(graphics);
        }

        //Draws Character
        player.draw(graphics);

        //Session Info Type Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(SESSIONINFO_TEXT_DISPLAY_X_OFFSET_VALUE, SESSIONINFO_TEXT_DISPLAY_Y_OFFSET_VALUE), "SESSION INFO: ");

        //HighScore Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(HIGHSCORE_TEXT_DISPLAY_X_OFFSET_VALUE, HIGHSCORE_TEXT_DISPLAY_Y_OFFSET_VALUE), displayHighScore());

        //Score Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(SCORE_TEXT_DISPLAY_X_OFFSET_VALUE, SCORE_TEXT_DISPLAY_Y_OFFSET_VALUE), displayScore());

        //Lives Remaining Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(LIFESREMAINING_TEXT_DISPLAY_X_OFFSET_VALUE, LIFESREMAINING_TEXT_DISPLAY_Y_OFFSET_VALUE), "LIVES REMAINING: " + player.displayLives());

        //Cycle Ammo Type Text
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(CYCLEAMMO_TEXT_DISPLAY_X_OFFSET_VALUE, CYCLEAMMO_TEXT_DISPLAY_Y_OFFSET_VALUE), "ESC- FIRE; F1- SINGLE; F2- DOUBLE; F3- BURST");

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
            case Escape:
                FireWeapon();
                break;
            case F1:
                ammotype = 1;
                break;
            case F2:
                ammotype = 2;
                break;
            case F3:
                ammotype = 3;
                break;
        }
    }

    public void createAsteroids(){
        Random random = new Random();

        //TO BE ADDED : ABILITY TO GENERATE ASTEROIDS WITH DIFFERENT SIZES
        /*
        String auxstr;
        int aux = random.nextInt(3);

        if(aux == 3){
            auxstr = "large";
        } else if (aux == 2){
            auxstr = "medium";
        } else{
            auxstr = "small";
        }
         */


        while(asteroids.size() < ASTEROID_NUMBER){
            asteroids.add(new Asteroid(random.nextInt(width+1), (height-height)+1, 100, "auxstr"));
        }
    }

    public void createSpaceships(){
        Random random = new Random();

        //TO BE ADDED : ABILITY TO GENERATE ASTEROIDS WITH DIFFERENT SIZES
        /*
        String auxstr;
        int aux = random.nextInt(3);

        if(aux == 3){
            auxstr = "large";
        } else if (aux == 2){
            auxstr = "medium";
        } else{
            auxstr = "small";
        }
         */


        while(spaceships.size() < SPACESHIP_NUMBER){
            spaceships.add(new Spaceship(random.nextInt(width+1), (height-height)+1, 100));
        }
    }


    private void FireWeapon(){
        if(ammotype == 1){
            singleShotFire();
        } else if (ammotype == 2){
            doubleShotFire();
        } else if (ammotype == 3) {
            burstShotFire();
        }
    }


    //TO DO: Add a timer between Enemy Shots!!!

    /*
    private void SpaceshipFire(Spaceship spaceship){
        while(){
            //Thread.Sleep(); ???
            EnemyShotFire(spaceship);
        }
    }
    */

    private List<SingleShot> singleShotFire(){
        singleShots.add(new SingleShot(player.position.getX(), player.position.getY()-1));
            return singleShots;
    }

    private List<DoubleShot> doubleShotFire(){
        doubleShots.add(new DoubleShot(player.position.getX()+1, player.position.getY()-1));
        doubleShots.add(new DoubleShot(player.position.getX()-1, player.position.getY()-1));
        return doubleShots;
    }
    private List<BurstShot> burstShotFire(){
        burstShots.add(new BurstShot(player.position.getX(), player.position.getY()-1));
        burstShots.add(new BurstShot(player.position.getX()-1, player.position.getY()-1));
        burstShots.add(new BurstShot(player.position.getX()+1, player.position.getY()-1));
        return burstShots;
    }

    private List<EnemyShot> EnemyShotFire(Spaceship spaceship){
        enemyShots.add(new EnemyShot(spaceship.position.getX(), spaceship.position.getY()+2));
        return enemyShots;
    }



    public void movePlayer(Position position){
        if(canEntityMove(position) == true){
            player.setPosition(position);
        }
    }

    private List<Border> createBorders() {
        List<Border> borders = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            borders.add(new Border(i, 0));
            borders.add(new Border(i, height - 1));
        }
        for (int j = 1; j < height - 1; j++) {
            borders.add(new Border(0, j));
            borders.add(new Border(width - 1, j));
        }
        return borders;
    }

    public boolean canEntityMove(Position position){
        for(Border border : borders){
            if(position.equals(border.getPosition())){
                return false;
            }
        }
        return true;
    }

    private boolean isHeightExceeded(Position position){
        if(position.getY() >= height)
            return true;
        else
            return false;
    }

    //TO DO: ADD BULLET DAMAGE
    //IDEA : RETURN BULLET DAMAGE INSTEAD OF TRUE OR FALSE AND THEN DEAL THAT DAMAGE TO THE ASTEROID

    private boolean isEntityHit(Position position){
        for(SingleShot singleShot : singleShots){
            if(singleShot.checkBulletImpact(position)) {
                ScoreIncrement(100);
                return true;
            }
        }
        for(DoubleShot doubleShot : doubleShots){
            if(doubleShot.checkBulletImpact(position)) {
                ScoreIncrement(100);
                return true;
            }
        }
        for(BurstShot burstShot : burstShots){
            if(burstShot.checkBulletImpact(position)) {
                ScoreIncrement(100);
                return true;
            }
        }
        return false;
    }

    private boolean isPlayerHit(Position position){
        for(EnemyShot enemyShot : enemyShots) {
            if (enemyShot.checkBulletImpact(position)) {
                enemyShots.remove(enemyShot);
                return true;
            }
        }
        return false;
    }

    private void ScoreIncrement(int inc) {
        score = score + inc;
    }
    protected void SetHighScore() {
        highScore = score;
    }

    protected int getHighScore() {
        return highScore;
    }
    protected int getScore() {
        return score;
    }

    private String displayHighScore() {
        String highScoreText;
        highScoreText = String.valueOf(highScore);
        return "HIGHSCORE: " +highScoreText;
    }
    
    private String displayScore() {
        String scoreText;
        scoreText = String.valueOf(score);
        return "SCORE: " +scoreText;
    }

    public void manageAsteroid() throws InterruptedException {
        for(Asteroid asteroid : asteroids){
            asteroid.moveEnemy();
            if(asteroid.checkImpact(asteroid, player) || isHeightExceeded(asteroid.getPosition()) || isEntityHit(asteroid.getPosition())){
                asteroids.remove(asteroid);
                break;
            }
        }
    }

    public void manageSpaceship() throws InterruptedException {
        for(Spaceship spaceship : spaceships){
            //if(canEntityMove(spaceship.position)) spaceship.moveEnemy();
            EnemyShotFire(spaceship);
            if(spaceship.checkImpact(spaceship, player) || isEntityHit(spaceship.getPosition())){
                spaceships.remove(spaceship);
                break;
            }
            if(isPlayerHit(player.getPosition())){
                player.lives--;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

}
