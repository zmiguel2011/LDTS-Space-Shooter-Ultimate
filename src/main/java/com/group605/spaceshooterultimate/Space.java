package com.group605.spaceshooterultimate;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.EscapeSequenceCharacterPattern;
import com.googlecode.lanterna.input.KeyStroke;

import javax.sql.ConnectionPoolDataSource;
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
    private List<Asteroid> asteroids;
    private List<Spaceship> spaceships;
    private List<Item> items;
    private List<Explosion> explosions;
    private int ASTEROID_NUMBER = 5; //Sets how many Asteroids will spawn together
    private int SPACESHIP_NUMBER = 3; //Sets how many SpaceShip will spawn together
    private final int MAX_MOVEMENT_NUMBER = 5;
    private int ITEM_NUMBER = 2;
    private int item_score; // checks if item for certain score has already spawned;
    private int score = 0;
    private int highScore = 0;
    private Position RespawnPosition = new Position(50,30);
    private boolean shooting = false;

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
        this.player = new Player(50,30);
        this.borders = createBorders();
        this.singleShots = new ArrayList<>();
        this.doubleShots = new ArrayList<>();
        this.burstShots = new ArrayList<>();
        this.ammotype = 1; //Sets Single as Default Ammo Type
        this.asteroids = new ArrayList<>();
        this.spaceships = new ArrayList<>();
        this.items = new ArrayList<>();
        this.explosions = new ArrayList<>();
        this.item_score = 0;

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

        //Draw Items
        for (Item item : items){
            item.draw(graphics);
        }

        //Draw Explosions
        for (Explosion explosion : explosions){
            explosion.draw(graphics);
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
        for (Spaceship spaceship :  spaceships) {
            for(EnemyShot enemyShot : spaceship.getEnemyShots()){
                if(canEntityMove(enemyShot.position)){
                    enemyShot.move();
                }
                else {
                    spaceship.getEnemyShots().remove(enemyShot);
                    break;
                }
                enemyShot.draw(graphics);
            }
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
                shooting = true;
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
            if (score <= 2500) asteroids.add(new Asteroid(random.nextInt(width), (height-height)+1, 0.3, "small"));
            else if (score >= 2500 & score <= 5000) asteroids.add(new Asteroid(random.nextInt(width), (height-height)+1, 0.5, "medium"));
            else asteroids.add(new Asteroid(random.nextInt(width), (height-height)+1, 1, "large"));
        }
    }

    public void createSpaceships(){
        Random random = new Random();

        while(spaceships.size() < SPACESHIP_NUMBER){
            if (score <= 3500) spaceships.add(new Spaceship(random.nextInt(width), (height-height)+1, 1));
            else spaceships.add(new Spaceship(random.nextInt(width), (height-height)+1, 2));
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

    private List<Explosion> Death(){
        explosions.add(new Explosion(player.position.getX(), player.position.getY()));
        shooting = false;
        if (player.lives > 0) player.setPosition(RespawnPosition);
        return explosions;
    }


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

    private void EnemyShotFire(Spaceship spaceship) {
        Random random = new Random();
        int distance = random.nextInt(5);

        if (spaceship.getEnemyShots().size() == 0) {
            spaceship.addEnemyShot(spaceship.position.getX(), spaceship.position.getY()+1);
        }
        else if (spaceship.getEnemyShots().get(spaceship.getEnemyShots().size() - 1).position.getY() - player.position.getY() == distance) {
            spaceship.getEnemyShots().add(new EnemyShot(spaceship.position.getX(), spaceship.position.getY()+1));
        }

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

    private boolean isEnemyHit(Enemy enemy){
        for(SingleShot singleShot : singleShots){
            if(singleShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - singleShot.getDamage());
                ScoreIncrement(100);
                return true;
            }
        }
        for(DoubleShot doubleShot : doubleShots){
            if(doubleShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - doubleShot.getDamage());
                ScoreIncrement(100);
                return true;
            }
        }
        for(BurstShot burstShot : burstShots){
            if(burstShot.checkBulletImpact(enemy.getPosition())) {
                enemy.setHealth(enemy.getHealth() - burstShot.getDamage());
                ScoreIncrement(100);
                return true;
            }
        }
        return false;
    }


    private boolean isPlayerHit(Position position){
        for (Spaceship spaceship : spaceships) {
            for(EnemyShot enemyShot : spaceship.getEnemyShots()) {
                if (enemyShot.checkBulletImpact(position)) {
                    spaceship.getEnemyShots().remove(enemyShot);
                    player.lives--;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean canSpawnItem(){
        if(score % 100 == 0 && score != 0 && item_score != score && items.size() < ITEM_NUMBER){
            item_score = score;
            return true;
        }
        return false;
    }
    public void createItem(){

        if(canSpawnItem()){
            Random random = new Random();
            int spawnX;
            int spawnY;
            while(true){
                spawnX = random.nextInt(98 - 2) + 2;
                spawnY = random.nextInt(38 - 2) + 2;
                for (Item item : items){
                    if(item.getPosition().getY() == spawnY && item.getPosition().getX() == spawnX){
                        break;
                    }
                }
                break;
            }
            int disTime = score + random.nextInt(300 - 100) + 100;
            items.add(new Item(spawnX, spawnY,disTime));

        }
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
            isEnemyHit(asteroid);
            asteroid.moveEnemy();
            if(canEntityMove(asteroid.getPosition()) == false || asteroid.isDead()){
                asteroids.remove(asteroid);
                break;
            }
            if(asteroid.checkImpact(asteroid, player)){
                Death();
                asteroids.remove(asteroid);
                break;
            }
        }
    }

    public void manageSpaceship() {
        //This will randomize a SpaceShip and move it
        //As well as randomize the amount of times that movement will be repeated in order to avoid a very similar movement for every SpaceShip
        Random random1 = new Random();
        Random random2 = new Random();
        for(int i = 0; i<random2.nextInt(MAX_MOVEMENT_NUMBER+1); i++){
            spaceships.get(random1.nextInt(spaceships.size())).moveEnemy();
        }
        //

        for(Spaceship spaceship : spaceships){
            EnemyShotFire(spaceship);
            isEnemyHit(spaceship);
            if(canEntityMove(spaceship.getPosition()) == false || spaceship.isDead()){
                spaceships.remove(spaceship);
                break;
            }
            if (spaceship.checkImpact(spaceship, player)){
                Death();
                spaceships.remove(spaceship);
                break;
            }
            if(isPlayerHit(player.getPosition())){
                Death();
            }
        }
    }

    public void manageItems(){
        for(Item item : items){
            if(item.checkCollision(player.getPosition())){
                if(player.lives < 3) player.lives++;
                items.remove(item);
                break;
            }
            if(item.disappearTime <= score){
                items.remove(item);
                break;
            }
        }
    }

    public void manageExplosions() throws InterruptedException {
        for(Explosion explosion : explosions){
            if ((player.getPosition() != RespawnPosition) || shooting == true) {
                explosions.remove(explosion);
                break;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

}