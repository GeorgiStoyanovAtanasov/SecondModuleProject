import java.util.Random;
import java.util.Scanner;

public class EnemyPokemon3 extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    private final int ultimateHealth;

    EnemyPokemon3() {
        super();
        this.name = "Alakazam";
        this.appearance = "Gum-like Pokémon";
        this.type = "Psychic-type";
        this.size = "Normal";
        this.setHealthPoints(55);
        this.setAttackPoints(50);
        this.setDefensePoints(45);
        this.ultimateHealth = 2*getHealthPoints();
    }
    @Override
    void strengthModifier() {

    }

    void chooseAttack() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(2) + 1;

        if (randomNumber == 1 && lastChosenAttack != 1) {
            attack1();
        } else if (randomNumber == 2 && lastChosenAttack != 2) {
            ultimate();
        } else {
            chooseAttack();
        }
    }


    void attack1() throws InterruptedException {
        lastChosenAttack = 1;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int randomNumber = random.nextInt(2) + 1;
        System.out.println(name + " has used Psycho cut. You can dodge it if you correctly guess a random number which is either 1 or 2. Good luck.");
        System.out.println("Enter Your guess: ");
        int guess = sc.nextInt();
        if (guess != randomNumber) {
            if (chosenPokemon.getHealthPoints() > 0) {
                System.out.println("You guessed wrong," + chosenPokemon.name + " is cut through by Psycho Cut.");
                this.chosenPokemon.setHealthPoints(chosenPokemon.getDefensePoints() - getAttackPoints());
            }
        } else {
            System.out.println("You guessed right.");
        }
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 2;
        System.out.println("Ultimate attack!");
        System.out.println("Calm Mind.");
        System.out.println("Alakazam's health points will increase drastically.");
        while (getHealthPoints() < ultimateHealth) {
            setHealthPoints(getHealthPoints() + 10);
            System.out.println(name + "'s health points are " + getHealthPoints());
            Thread.sleep(1000);
        }
        ultimateUsed = true;
    }
}


