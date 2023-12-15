import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestingEnemyPokemon5 {
    private InputStream sysInBackup;

    @BeforeEach
    public void setUp() {
        sysInBackup = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    public void testAttack1_DodgeSuccessful() throws InterruptedException, ExecutionException {
        EnemyPokemon5 enemyPokemon = new EnemyPokemon5();
        Pokemon chosenPokemon = new PrizePokemon();
        enemyPokemon.setChosenPokemon(chosenPokemon);


        System.setIn(new ByteArrayInputStream("up\n".getBytes()));


        enemyPokemon.attack1();


        assertEquals(1000, chosenPokemon.getHealthPoints());
    }

    @Test
    public void testAttack1_DodgeFailed() throws InterruptedException, ExecutionException {
        EnemyPokemon5 enemyPokemon = new EnemyPokemon5();
        Pokemon chosenPokemon = new PrizePokemon();
        enemyPokemon.setChosenPokemon(chosenPokemon);


        System.setIn(new ByteArrayInputStream("down\n".getBytes()));


        enemyPokemon.attack1();


        assertEquals(905, chosenPokemon.getHealthPoints());
    }
}