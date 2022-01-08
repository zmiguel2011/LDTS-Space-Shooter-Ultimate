import com.group605.spaceshooterultimate.Position;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    private Position position;
    private int moveSize;

    @BeforeEach
    void setUp() {
        position = new Position(10, 10);
        moveSize = 1;
    }

    @AfterEach
    void cleanUp(){
        position=null;
    }

    @Test
    void PlayerMoveLeft() { assertEquals(10 - moveSize, position.getX()); }

    @Test
    void PlayerMoveRight() { assertEquals(10 + moveSize, position.getX()); }

    @Test
    void PlayerMoveUp() { assertEquals(10 + moveSize, position.getY()); }

    @Test
    void PlayerMoveDown() {
        assertEquals(10 - moveSize, position.getY());
    }

    @Test
    void PlayergetX() {
        assertEquals(10 , position.getX());
    }

    @Test
    void PlayergetY() {
        assertEquals(10 , position.getY());
    }


}