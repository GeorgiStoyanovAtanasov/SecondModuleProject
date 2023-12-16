import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestingEnemyPokemon4 {
    private EnemyPokemon4 enemyPokemon;

    @BeforeEach
    public void setUp() {
        enemyPokemon = new EnemyPokemon4();
    }
    @Test
    public void testPlayMiniGame() throws InterruptedException {
        String input = "yes\n\n\n";
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        try {
            System.setIn(inputStream);
            int enterCount = enemyPokemon.playMiniGame();
            assertTrue(enterCount >= 0);
        } catch (NoSuchElementException e){
            System.setIn(originalSystemIn);
        }
        finally {
            System.setIn(originalSystemIn);
        }
    }
}
