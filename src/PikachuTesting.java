import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PikachuTesting {
    static Pikachu pikachu = new Pikachu();
    static Pokemon enemyPokemon = new EnemyPokemon5();

    @AfterEach
    void restoreSystemInput() {
        System.setIn(System.in);
    }

    @BeforeEach
    void g() {
        pikachu = new Pikachu();
        enemyPokemon = new EnemyPokemon5();
        pikachu.enemyPokemon = enemyPokemon;
    }

    @Test
    void checkWhetherOrNotTheStrengthModifierForPikachuWorks() {
        //GIVEN
        Pokemon pikachu = new Pikachu();
        pikachu.strengthModifier();
        //WHEN
        int resultOne = 77;
        int resultTwo = 45;
        int resultTree = 36;
        //THEN
        Assertions.assertEquals(pikachu.getHealthPoints(), resultOne);
        Assertions.assertEquals(pikachu.getAttackPoints(), resultTwo);
        Assertions.assertEquals(pikachu.getDefensePoints(), resultTree);
    }

    @Test
    void testChooseAttackWithValidInput1And2() throws InterruptedException {
        Pikachu pikachu = new Pikachu();
        Pokemon enemyPokemon = new EnemyPokemon5();
        pikachu.enemyPokemon = enemyPokemon;
        pikachu.setUltimateUsed(false);
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String input2 = "2\n";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);

        boolean result = pikachu.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(2, pikachu.getLastChosenAttack());
        Assertions.assertEquals(2, pikachu.getLastNonUltimateAttack());
        Assertions.assertEquals(450, pikachu.enemyPokemon.getHealthPoints());
    }
    @Test
    void testChooseAttackWithValidInput1And3() throws InterruptedException {
        Pikachu pikachu = new Pikachu();
        Pokemon enemyPokemon = new EnemyPokemon5();
        pikachu.enemyPokemon = enemyPokemon;
        pikachu.setUltimateUsed(false);

        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String input2 = "3\n";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);

        boolean result = pikachu.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(3, pikachu.getLastChosenAttack());
        Assertions.assertEquals(50, pikachu.enemyPokemon.getHealthPoints());
    }
    @Test
    void testChooseAttackWithValidInput2() throws InterruptedException {
        pikachu.setUltimateUsed(false);
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = pikachu.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertEquals(2, pikachu.getLastChosenAttack());
        Assertions.assertEquals(2, pikachu.getLastNonUltimateAttack());
        Assertions.assertEquals(450, pikachu.enemyPokemon.getHealthPoints());
    }

    @Test
    void testChooseAttackWithUltimateWhenIsUsedIsFalse() throws InterruptedException {
        //Pikachu pikachu = new Pikachu();
        //pikachu.setUltimateUsed(false);
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        boolean result = pikachu.chooseAttack();

        Assertions.assertFalse(result);
        Assertions.assertTrue(pikachu.isUltimateUsed());
        Assertions.assertEquals(3, pikachu.getLastChosenAttack());
        Assertions.assertEquals(50, pikachu.enemyPokemon.getHealthPoints());
    }
    @Test
    void testChooseAttack2WithValidInput2() throws InterruptedException {
        Pikachu pikachu = new Pikachu();
        Pokemon enemyPokemon = new EnemyPokemon5();
        pikachu.enemyPokemon = enemyPokemon;
        pikachu.setUltimateUsed(false);

        String input2 = "2\n";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);

        boolean result = pikachu.chooseAttack2();

        Assertions.assertFalse(result);
        Assertions.assertEquals(2, pikachu.getLastChosenAttack());
        Assertions.assertEquals(2, pikachu.getLastNonUltimateAttack());
        Assertions.assertEquals(450, pikachu.enemyPokemon.getHealthPoints());
    }
    @Test
    void testChooseAttack2WithValidInput3() throws InterruptedException {
        Pikachu pikachu = new Pikachu();
        Pokemon enemyPokemon = new EnemyPokemon5();
        pikachu.enemyPokemon = enemyPokemon;
        pikachu.setUltimateUsed(false);

        String input2 = "3\n";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);

        boolean result = pikachu.chooseAttack2();

        Assertions.assertFalse(result);
        Assertions.assertEquals(3, pikachu.getLastChosenAttack());
        Assertions.assertEquals(50, pikachu.enemyPokemon.getHealthPoints());
    }
    @Test
    void testAfterUltimateUsedLastChosenAttackIs2AndAttack1IsUsedAndWantsToCallAttack2() throws InterruptedException {
        pikachu.setUltimateUsed(true);
        pikachu.setLastChosenAttack(2);
        String input4 = "2\n";
        InputStream in4 = new ByteArrayInputStream(input4.getBytes());
        System.setIn(in4);
        pikachu.chooseAttack2();
        System.setIn(System.in);
        Assertions.assertEquals(2, pikachu.getLastNonUltimateAttack());
        Assertions.assertEquals(2, pikachu.getLastNonUltimateAttack());
    }
}
