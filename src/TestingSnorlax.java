import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TestingSnorlax {
    @Test
    void testStrengthModifier() {
        Snorlax snorlax = new Snorlax();
        snorlax.strengthModifier();
        int expectedHealthPoints = 180;
        int expectedAttackPoints = 108;
        int expectedDefensePoints = 192;

        Assertions.assertEquals(expectedHealthPoints, snorlax.getHealthPoints());
        Assertions.assertEquals(expectedAttackPoints, snorlax.getAttackPoints());
        Assertions.assertEquals(expectedDefensePoints, snorlax.getDefensePoints());
    }

    @Test
    void testChooseAttackWithValidInput1() throws InterruptedException {
        Snorlax snorlax = new Snorlax();
        Pokemon enemyPokemon = new EnemyPokemon5();
        snorlax.enemyPokemon = enemyPokemon;
        snorlax.setSecretComboUsed(false);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = snorlax.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(1, snorlax.getLastChosenAttack());
        Assertions.assertEquals(477, enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseAttackWithValidInput2() throws InterruptedException {
        Snorlax snorlax = new Snorlax();
        Pokemon enemyPokemon = new EnemyPokemon5();
        snorlax.enemyPokemon = enemyPokemon;
        snorlax.setSecretComboUsed(false);

        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = snorlax.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(2, snorlax.getLastChosenAttack());
        Assertions.assertEquals(455, enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseAttackWithSecretCombo() throws InterruptedException {
        Snorlax snorlax = new Snorlax();
        Pokemon enemyPokemon = new EnemyPokemon5();
        snorlax.enemyPokemon = enemyPokemon;
        snorlax.setSecretComboUsed(false);

        String input = "21\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = snorlax.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertTrue(snorlax.isSecretComboUsed());
        Assertions.assertEquals(2, snorlax.getLastChosenAttack());
        Assertions.assertEquals(432, enemyPokemon.getHealthPoints());
    }
}
