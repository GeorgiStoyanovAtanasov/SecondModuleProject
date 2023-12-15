import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void checkingWhetherOrNotThePrizePokemonHasBeenWonTest() {
        assertFalse(Main.checkingWhetherOrNotThePrizePokemonHasBeenWon());
        Main.getThePrizePokemonPermanently();
        assertTrue(Main.checkingWhetherOrNotThePrizePokemonHasBeenWon());
    }

    @Test
    public void generateEnemiesTest() {
        assertNotNull(Main.generateEnemies());
        assertEquals(5, Main.generateEnemies().size());
    }

    @Test
    public void getThePrizePokemonPermanentlyTest() {
        Main.getThePrizePokemonPermanently();
        assertTrue(Main.checkingWhetherOrNotThePrizePokemonHasBeenWon());
    }

    @Test
    public void playAudioTest() {
        try {
            Main.playAudio();
        } catch (Exception e) {
            fail("playAudio method should not throw exceptions");
        }
    }

    @Test
    public void menuTest() {
        try {
            Main.menu();
        } catch (Exception e) {
            fail("menu method should not throw exceptions");
        }
    }

    @Test
    public void realMenuTest() {
        try {
            Main.realMenu();
        } catch (Exception e) {
            fail("realMenu method should not throw exceptions");
        }
    }

}
