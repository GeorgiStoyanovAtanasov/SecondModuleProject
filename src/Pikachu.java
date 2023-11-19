import java.util.Scanner;

public class Pikachu extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;

    Pikachu() {
        super();
        this.name = "Pikachu";
        this.appearance = "Mouse-like Pokémon";
        this.type = "Electric-type";
        this.size = "Small";
        this.setHealthPoints(60);
        this.setAttackPoints(50);
        this.setDefensePoints(40);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }

    void chooseAttack() throws InterruptedException {
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
            //System.exit(0);
        }
    }


    void attack1() throws InterruptedException {
        lastChosenAttack = 1;
        System.out.println(this.enemyPokemon.name + " is attacked by Thunder shock.");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 20);
        System.out.println("The health of the enemy pokemon is " + enemyPokemon.getHealthPoints());
        if(enemyPokemon.getHealthPoints() > 0) {
            System.out.println("The enemy pokemon is stunned, you can attack again");
            chooseAttack2();
        }
    }

    void attack2() {
        lastChosenAttack = 2;
        System.out.println("Thunderbolt.");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - this.getAttackPoints());
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        System.out.println("Infinity lightning");
        int limitForWhileLoop = enemyPokemon.getHealthPoints() / 10;
        while (enemyPokemon.getHealthPoints() > limitForWhileLoop) {
            this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 1);
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
        if (choice == 2 && lastChosenAttack != 2) {
            attack2();
        } else if (choice == 3 && !ultimateUsed) {
            ultimate();
        } else {
            System.out.println("You are trying to use an an illegal move.");
            chooseAttack2();
            //System.exit(0);
        }
    }
}
