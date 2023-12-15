import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestingBulbasaur {
    @Test
    void testStrengthModifier() {
        Bulbasaur bulbasaur = new Bulbasaur();
        bulbasaur.strengthModifier();
        int expectedHealthPoints = 81;
        int expectedAttackPoints = 23;
        int expectedDefensePoints = 45;

        Assertions.assertEquals(expectedHealthPoints, bulbasaur.getHealthPoints());
        Assertions.assertEquals(expectedAttackPoints, bulbasaur.getAttackPoints());
        Assertions.assertEquals(expectedDefensePoints, bulbasaur.getDefensePoints());
    }

    @Test
    void testChooseAttackWithValidInput1() throws InterruptedException {
        Bulbasaur bulbasaur = new Bulbasaur();
        Pokemon enemyPokemon = new EnemyPokemon5();
        bulbasaur.enemyPokemon = enemyPokemon;
        bulbasaur.setUltimateUsed(false);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = bulbasaur.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(1, bulbasaur.getLastChosenAttack());
        Assertions.assertEquals(475, enemyPokemon.getHealthPoints());
        Assertions.assertEquals(115, bulbasaur.getHealthPoints());
    }

    @Test
    void testChooseAttackWithValidInput2() throws InterruptedException {
        Bulbasaur bulbasaur = new Bulbasaur();
        Pokemon enemyPokemon = new EnemyPokemon5();
        bulbasaur.enemyPokemon = enemyPokemon;
        bulbasaur.setUltimateUsed(false);

        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = bulbasaur.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(2, bulbasaur.getLastChosenAttack());
        Assertions.assertEquals(450, enemyPokemon.getHealthPoints());
        Assertions.assertEquals(80, bulbasaur.getHealthPoints());
    }

    @Test
    void testChooseAttackWithUltimateWhenIsUsedIsFalse() throws InterruptedException, IOException {
        Bulbasaur bulbasaur = new Bulbasaur();
        Pokemon enemyPokemon = new EnemyPokemon5();
        bulbasaur.enemyPokemon = enemyPokemon;
        bulbasaur.setUltimateUsed(false);
        String input2 = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);
        bulbasaur.ultimate();
        Assertions.assertTrue(bulbasaur.isUltimateUsed());
        Assertions.assertEquals(3, bulbasaur.getLastChosenAttack());
        Assertions.assertEquals(400, enemyPokemon.getHealthPoints());
    }
}
