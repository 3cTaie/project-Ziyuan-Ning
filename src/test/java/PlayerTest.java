import com.teamsolid.javaproject.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testMoveBasic() {
        player.move(3);
        int[] expectedPosition = {3, 0};
        assertArrayEquals(expectedPosition, player.getPosition());
    }

    @Test
    public void testMoveBoundary() {
        player.move(8);
        int[] expectedPosition = {7, 1};
        assertArrayEquals(expectedPosition, player.getPosition());

        player.move(7);
        expectedPosition = new int[]{6, 7};
        assertArrayEquals(expectedPosition, player.getPosition());
    }

    @Test
    public void testMoveBeyondBoundary() {
        player.move(64);
        int[] expectedPosition = {3, 4};
        assertArrayEquals(expectedPosition, player.getPosition());
    }

    @Test
    public void testTeleportToRandomLocation() {
        player.teleportToRandomLocation();
        int[] position = player.getPosition();
        assertTrue(position[0] >= 0 && position[0] < 8);
        assertTrue(position[1] >= 0 && position[1] < 8);
    }

    @Test
    public void testIncrementThrowCount() {
        player.incrementThrowCount();
        assertEquals(1, player.getThrowCount());
    }

    @Test
    public void testCalculateDistance() {
        assertEquals(1, player.calculateDistance(0, 0));
        assertEquals(28, player.calculateDistance(0, 1));
        assertEquals(2, player.calculateDistance(1, 0));
        assertEquals(29, player.calculateDistance(1, 1));
    }
}