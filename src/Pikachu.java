import java.util.Scanner;

public class Pikachu extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    private int lastNonUltimateAttack = 0;

    Pikachu() {
        super();
        this.name = "Pikachu";
        this.appearance = "Mouse-like Pokémon";
        this.type = "Electric-type";
        this.size = "Small";
        //this.setHealthPoints(60);
        this.setHealthPoints(90);
        this.setAttackPoints(50);
        this.setDefensePoints(40);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }

    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Thunder shock, 2 - Thunderbolt, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
        byte choice = sc.nextByte();

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
        lastNonUltimateAttack = 1;
        System.out.println("The enemy is attacked by Thunder shock.");
        enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - 20);
        if(enemyPokemon.getHealthPoints() > 0) {
            System.out.println("The health of the enemy pokemon is " + enemyPokemon.getHealthPoints());
            System.out.println("The enemy pokemon is stunned, you can attack again");// Update last non-ultimate attack
            chooseAttack2();
        }
    }

    void attack2() {
        lastChosenAttack = 2;
        System.out.println("Thunderbolt.");
        enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - this.getAttackPoints());
        lastNonUltimateAttack = 2;
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        Thread.sleep(1000);
        System.out.println("Infinity lightning");
        Thread.sleep(1000);
        System.out.println("Pika");
        Thread.sleep(1000);
        System.out.println("Pika");
        Thread.sleep(1000);
        System.out.println("chuuuu");
        int limitForWhileLoop = enemyPokemon.getHealthPoints() / 10;
        while (enemyPokemon.getHealthPoints() > limitForWhileLoop) {
            this.enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - 6);
            System.out.println("chuuuu");
            System.out.println("The health of the enemy Pokemon is " + enemyPokemon.getHealthPoints());
            Thread.sleep(500);
        }
        ultimateUsed = true;
        System.out.println();
    }

    void chooseAttack2() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 2 - Thunderbolt, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
        byte choice = sc.nextByte();

        if ((choice == 2 && lastChosenAttack != 2) || (choice == 2 && ultimateUsed) || (choice == 1 && lastNonUltimateAttack != 1)) {
            lastChosenAttack = 1;
            attack2();
        } else if (choice == 3 && !ultimateUsed) {
            lastChosenAttack = 1;
            ultimate();
        } else {
            System.out.println("You are trying to use an an illegal move.");
            chooseAttack2();
        }
    }
}
