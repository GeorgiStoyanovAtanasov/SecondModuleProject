import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestingEnemyPokemon2 {
    private EnemyPokemon2 enemyPokemon;
    private PrizePokemon prizePokemon;

    @BeforeEach
    public void setUp() {
        enemyPokemon = new EnemyPokemon2();
        prizePokemon = new PrizePokemon();
        enemyPokemon.chosenPokemon = prizePokemon;
    }

    @Test
    public void testChooseAttack() {
        assertDoesNotThrow(() -> {
            assertFalse(enemyPokemon.chooseAttack());
        });
    }

    @Test
    public void testAttack1() {
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.attack1();
        });

        assertEquals(originalHealth - enemyPokemon.getAttackPoints(), prizePokemon.getHealthPoints());
        assertEquals(enemyPokemon.getLastChosenAttack(), 1);
    }

    @Test
    public void testAttack2() {
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.attack2();
        });

        assertEquals(originalHealth - 30, prizePokemon.getHealthPoints());
        assertEquals(enemyPokemon.getLastChosenAttack(), 2);
    }

    @Test
    public void testUltimate() {
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.ultimate();
        });

        assertTrue(enemyPokemon.isUltimateUsed());
        assertEquals(originalHealth - 70, prizePokemon.getHealthPoints());
    }
}
