import com.group605.spaceshooterultimate.Asteroid;
import com.group605.spaceshooterultimate.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class AsteroidTest {
    private Asteroid asteroid;

    @BeforeEach
    void setUp(){
        asteroid = new Asteroid(10,10,5,"small");
    }

    @Test
    void moveEnemy(){
        asteroid.moveEnemy();
        Assertions.assertEquals(11,asteroid.getPosition().getY());
    }

    @Test
    void CheckImpact(){
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(asteroid.getPosition());

        Assertions.assertEquals(true,asteroid.checkImpact(asteroid,player));
    }
}
