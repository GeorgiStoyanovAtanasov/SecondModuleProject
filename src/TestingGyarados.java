import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestingGyarados {
    @Test
    void testStrengthModifier() {
        // GIVEN
        Gyarados gyarados = new Gyarados();

        // WHEN
        gyarados.strengthModifier();

        // THEN
        int expectedHealth = 150 + (150 / 5);
        int expectedAttack = 55 + (55 / 5);
        int expectedDefense = 79 + (79 / 5);

        Assertions.assertEquals(expectedHealth, gyarados.getHealthPoints());
        Assertions.assertEquals(expectedAttack, gyarados.getAttackPoints());
        Assertions.assertEquals(expectedDefense, gyarados.getDefensePoints());
    }

    @Test
    void testChooseAttackWithValidInput1AndNotPreviouslyUsed() throws InterruptedException {
        // GIVEN
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;
        gyarados.setUltimateUsed(false);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // WHEN
        boolean result = gyarados.chooseAttack();

        // THEN
        Assertions.assertFalse(result);
        Assertions.assertEquals(1, gyarados.getLastChosenAttack());
        Assertions.assertEquals(75, gyarados.getAttackPoints());
        Assertions.assertEquals(15, gyarados.getWhirlpoolPressesMinimal());
    }

    @Test
    void testChooseAttackWithValidInput2AndNotPreviouslyUsed() throws InterruptedException {
        // GIVEN
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;
        gyarados.setUltimateUsed(false);

        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // WHEN
        boolean result = gyarados.chooseAttack();

        // THEN
        Assertions.assertFalse(result);
        Assertions.assertEquals(2, gyarados.getLastChosenAttack());
        // Attack2 decreases the enemy's health points, testing that is left to another test if
        //anyone is reading this
    }

    @Test
    void testChooseAttackWithInvalidInputAndNotPreviouslyUsed() throws InterruptedException {
        // GIVEN
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;
        gyarados.setUltimateUsed(false);


        try {
            String input = "4\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);


            // WHEN
            boolean result = gyarados.chooseAttack();
            Assertions.assertFalse(result);
        } catch (Exception e) {

        }
        // THEN
        Assertions.assertEquals(0, gyarados.getLastChosenAttack());
    }

    @Test
    void testAttack1() throws InterruptedException {
        // GIVEN
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;

        // WHEN
        gyarados.attack1();

        // THEN

        Assertions.assertEquals(1, gyarados.getLastChosenAttack());
    }

    @Test
    void testAttack2() throws InterruptedException {
        // GIVEN
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;

        // WHEN
        gyarados.attack2();

        // THEN

        Assertions.assertEquals(2, gyarados.getLastChosenAttack());
    }

    @Test
    void testUltimate() {
        Gyarados gyarados = new Gyarados();
        Pokemon enemyPokemon = new EnemyPokemon5();
        gyarados.enemyPokemon = enemyPokemon;
        String input = "\n\n\n";
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            System.setIn(inputStream);
            gyarados.ultimate();
            assertTrue(gyarados.isUltimateUsed());
        } catch (NoSuchElementException e) {
            System.setIn(originalSystemIn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.setIn(originalSystemIn);
        }
    }
}
