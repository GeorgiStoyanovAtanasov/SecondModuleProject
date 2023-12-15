import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestingEnemyPokemon3 {
    private EnemyPokemon3 enemyPokemon;
    private PrizePokemon prizePokemon;

    @BeforeEach
    public void setUp() {
        enemyPokemon = new EnemyPokemon3();
        prizePokemon = new PrizePokemon();
        prizePokemon.setChosenPokemon(enemyPokemon);
    }

    @Test
    public void testChooseAttack() {
        enemyPokemon.chosenPokemon = prizePokemon;
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> {
            assertFalse(enemyPokemon.chooseAttack());
        });
    }

    @Test
    public void testAttack1() {
        enemyPokemon.chosenPokemon = prizePokemon;
        int originalHealth = prizePokemon.getHealthPoints();

        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        assertDoesNotThrow(() -> {
            enemyPokemon.attack1();
        });


        System.setIn(sysInBackup);

        assertEquals(955, prizePokemon.getHealthPoints());
        assertEquals(enemyPokemon.getLastChosenAttack(), 1);
    }

    @Test
    public void testUltimate() {
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.ultimate();
        });

        assertTrue(enemyPokemon.isUltimateUsed());
        assertEquals(enemyPokemon.getUltimateHealth(), enemyPokemon.getHealthPoints());
    }
}
