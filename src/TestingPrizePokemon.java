import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestingPrizePokemon {
    private PrizePokemon prizePokemon;

    @BeforeEach
    public void setUp() {
        prizePokemon = new PrizePokemon();
        prizePokemon.enemyPokemon = new EnemyPokemon5();
    }

    @Test
    public void testIsThePlayerAllowedToUseThePrizePokemon() {
        assertFalse(prizePokemon.isThePlayerAllowedToUseThePrizePokemon());

        prizePokemon.makeThePrizePokemonAllowed();

        assertTrue(prizePokemon.isThePlayerAllowedToUseThePrizePokemon());
    }

    @Test
    public void testChooseAttack() {
        prizePokemon.enemyPokemon = new EnemyPokemon5();

        System.setIn(new java.io.ByteArrayInputStream("4\n".getBytes()));
        try {
            assertFalse(prizePokemon.chooseAttack());
        } catch (NoSuchElementException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.setIn(new java.io.ByteArrayInputStream("1\n".getBytes()));
        try {
            assertFalse(prizePokemon.chooseAttack());
        } catch (InterruptedException e) {
            fail("Unexpected InterruptedException: " + e.getMessage());
        }
    }

    @Test
    public void testAttack1() throws InterruptedException {
        int originalHealth = prizePokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            prizePokemon.attack1();
        });

        assertEquals(prizePokemon.getHealthPoints(), prizePokemon.getOriginalHealthPoints());
        assertEquals(prizePokemon.getLastChosenAttack(), 1);
    }

    @Test
    public void testAttack2() throws InterruptedException {
        int originalEnemyHealth = prizePokemon.enemyPokemon.getHealthPoints();

        assertDoesNotThrow(() -> {
            prizePokemon.attack2();
        });

        assertEquals(400, prizePokemon.enemyPokemon.getHealthPoints());
        assertEquals(prizePokemon.getLastChosenAttack(), 2);
    }

}
