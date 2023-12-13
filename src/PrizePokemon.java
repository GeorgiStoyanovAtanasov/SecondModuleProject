import java.util.Scanner;

public class PrizePokemon extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    private boolean isThePlayerAllowedToUseThePrizePokemon;
    private int originalHealthPoints;

    PrizePokemon() {
        this.name = "Arceus";
        this.appearance = "Arceus looks like a fusion of Palkia and Dialga, resembling a wingless dragon of some sort. It's appearance also bears some resemblance to a llama.";
        this.type = "God-type";
        this.size = "Normal";
        this.setHealthPoints(1000);
        this.setAttackPoints(100);
        this.setDefensePoints(100);
        this.isThePlayerAllowedToUseThePrizePokemon = false;
        this.originalHealthPoints = this.getHealthPoints();
    }

    public boolean isThePlayerAllowedToUseThePrizePokemon() {
        return isThePlayerAllowedToUseThePrizePokemon;
    }

    void makeThePrizePokemonAllowed() {
        this.isThePlayerAllowedToUseThePrizePokemon = true;
    }

    @Override
    void strengthModifier() {

    }

    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Recover, 2 - Earthquake, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
        int choice = sc.nextInt();

        if (choice == 1 && lastChosenAttack != 1) {
            attack1();
        } else if (choice == 2 && lastChosenAttack != 2) {
            attack2();
        } else if (choice == 3 && !ultimateUsed) {
            ultimate();
        } else {
            System.out.println("You are trying to use an an illegal move.");
            chooseAttack();
        }
        return false;
    }

    void attack1() throws InterruptedException {
        System.out.println("Recover.");
        this.setHealthPoints(this.originalHealthPoints);
        lastChosenAttack = 1;
    }

    void attack2() throws InterruptedException {
        System.out.println("Earthquake.");
        int counter = 0;
        while (counter < 10) {
            if(this.enemyPokemon.getHealthPoints() > 0) {
                Thread.sleep(1000);
            }
            this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - (this.getAttackPoints() / 10));
            if (this.enemyPokemon.getHealthPoints() > 0) {
                System.out.println("The health of the enemy pokemon is " + this.enemyPokemon.getHealthPoints());
            }
            counter++;
        }
        lastChosenAttack = 2;
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        System.out.println("Divine Judgement");
        enemyPokemon.setHealthPoints(0);
        ultimateUsed = true;
        System.out.println();
    }
}
