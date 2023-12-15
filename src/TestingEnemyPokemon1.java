import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestingEnemyPokemon1 {
    private EnemyPokemon1 enemyPokemon;
    private PrizePokemon prizePokemon;

    @BeforeEach
    public void setUp() {
        enemyPokemon = new EnemyPokemon1();
        prizePokemon = new PrizePokemon();
        prizePokemon.setChosenPokemon(enemyPokemon);
    }

    @Test
    public void testChooseAttack() {
        enemyPokemon.chosenPokemon = prizePokemon;
        assertDoesNotThrow(() -> {
            assertFalse(enemyPokemon.chooseAttack());
        });
    }

    @Test
    public void testAttack1() {
        enemyPokemon.chosenPokemon = prizePokemon;
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.attack1();
        });

        assertEquals(originalHealth - enemyPokemon.getAttackPoints(), prizePokemon.getHealthPoints());
        assertEquals(enemyPokemon.getLastChosenAttack(), 1);
    }

    @Test
    public void testAttack2() {
        enemyPokemon.chosenPokemon = prizePokemon;
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.attack2();
        });

        assertEquals(originalHealth - enemyPokemon.getAttackPoints(), prizePokemon.getHealthPoints());
        assertEquals(enemyPokemon.getLastChosenAttack(), 2);
    }
    @Test
    public void testUltimate() {
        enemyPokemon.chosenPokemon = prizePokemon;
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            enemyPokemon.ultimate();
        });

        assertTrue(enemyPokemon.isUltimateUsed());
        assertEquals(1000, prizePokemon.getHealthPoints());
    }
}
