import com.group605.spaceshooterultimate.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp(){
        player = new Player(10,10);
        player.setLives(3);
    }

    @Test
    void moveUp(){
        Assertions.assertEquals(9,player.moveUp().getY());
    }

    @Test
    void moveDown(){
        Assertions.assertEquals(11,player.moveDown().getY());
    }

    @Test
    void moveRight(){
        Assertions.assertEquals(11,player.moveRight().getX());
    }

    @Test
    void moveLeft(){
        Assertions.assertEquals(9,player.moveLeft().getX());
    }

    @Test
    void displayLives(){
        Assertions.assertEquals("3", player.displayLives());
    }

}
