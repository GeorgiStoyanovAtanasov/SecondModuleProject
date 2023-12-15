import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CharizardTesting {
    @Test
    void testChooseDragonBreath() throws InterruptedException {
        Charizard charizard = new Charizard();
        Pokemon enemyPokemon = new EnemyPokemon5();
        charizard.enemyPokemon = enemyPokemon;

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = charizard.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(460, enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseInfernoBlitz() throws InterruptedException {
        Charizard charizard = new Charizard();
        Pokemon enemyPokemon = new EnemyPokemon5();
        charizard.enemyPokemon = enemyPokemon;

        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = charizard.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(416, enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseUltimate() throws InterruptedException {
        Charizard charizard = new Charizard();
        Pokemon enemyPokemon = new EnemyPokemon5();
        charizard.enemyPokemon = enemyPokemon;
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = charizard.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(460, enemyPokemon.getHealthPoints());
        Assertions.assertEquals(20, charizard.getAddedStrength());
    }
    @Test
    void testChooseInfernoBlitzAfterUltimate() throws InterruptedException {
        Charizard charizard = new Charizard();
        Pokemon enemyPokemon = new EnemyPokemon5();
        charizard.enemyPokemon = enemyPokemon;
        charizard.ultimate();
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = charizard.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(432, enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseDragonsBreathAfterUltimate() throws InterruptedException {
        Charizard charizard = new Charizard();
        Pokemon enemyPokemon = new EnemyPokemon5();
        charizard.enemyPokemon = enemyPokemon;
        charizard.ultimate();

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = charizard.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(400, enemyPokemon.getHealthPoints());
    }
}