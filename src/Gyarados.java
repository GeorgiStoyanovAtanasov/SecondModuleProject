import java.util.Scanner;

public class Gyarados extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    private int whirlpoolPressesMinimal = 20;

    Gyarados() {
        this.name = "Gyarados";
        this.appearance = "Piscine, draconic Pokémon with a long serpentine body covered in slightly overlapping scales";
        this.type = "Water";
        this.size = "Large";
        //this.setHealthPoints(95);
        //this.setAttackPoints(125);
        this.setHealthPoints(150);
        this.setAttackPoints(55);
        this.setDefensePoints(79);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() + (this.getHealthPoints() / 5));
        this.setAttackPoints(this.getAttackPoints() + (this.getAttackPoints() / 5));
        this.setDefensePoints((this.getDefensePoints() + (this.getDefensePoints() / 5)));
    }

    public int getWhirlpoolPressesMinimal() {
        return whirlpoolPressesMinimal;
    }


    public int getLastChosenAttack() {
        return lastChosenAttack;
    }

    public boolean isUltimateUsed() {
        return ultimateUsed;
    }

    @Override
    public void setUltimateUsed(boolean ultimateUsed) {
        this.ultimateUsed = ultimateUsed;
    }

    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Rain Dance, 2 - Outrage, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
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
        System.out.println("Rain Dance");
        System.out.println(this.name + " has summoned rain, therefore " + this.name + "' attack power has increased.");
        this.setAttackPoints(getAttackPoints() + 20);
        this.whirlpoolPressesMinimal = this.whirlpoolPressesMinimal - 5;
        lastChosenAttack = 1;
    }

    void attack2() throws InterruptedException {
        System.out.println("Outrage");
        Thread.sleep(1000);
        System.out.println(this.enemyPokemon.name + "is hit by Ice Fang");
        this.enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - (this.getAttackPoints() / 5));
        if (this.enemyPokemon.getHealthPoints() > 0) {
            System.out.println("The health of the enemy pokemon is " + this.enemyPokemon.getHealthPoints());
        }
        Thread.sleep(1000);
        System.out.println(this.enemyPokemon.name + "is hit by Aqua Tail");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - (this.getAttackPoints() / 5));
        if (this.enemyPokemon.getHealthPoints() > 0) {
            System.out.println("The health of the enemy pokemon is " + this.enemyPokemon.getHealthPoints());
        }
        Thread.sleep(1500);
        System.out.println(this.enemyPokemon.name + "is hit by Hydro Pump");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - (this.getAttackPoints() / 5));
        if (this.enemyPokemon.getHealthPoints() > 0) {
            System.out.println("The health of the enemy pokemon is " + this.enemyPokemon.getHealthPoints());
        }
        lastChosenAttack = 2;
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        Thread.sleep(500);
        System.out.println("Whirlpool");
        Thread.sleep(500);
        System.out.println("The whirlpool will continue for only 5 seconds.");
        Thread.sleep(500);
        System.out.println("Rapidly press the 'Enter' to drown " + this.enemyPokemon.name + ".");
        System.out.println("Get ready...");
        Thread.sleep(1000);
        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);
        System.out.println("GO");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        int enterCount = 0;
        int timeThreshold = 5000;
        while (System.currentTimeMillis() - startTime < timeThreshold) {
            String userInput = scanner.nextLine();
            System.out.println("User input: " + userInput);
            if (userInput.isEmpty()) {
                enterCount++;
            }
        }
        System.out.println("Time's up!");
        if (enterCount > whirlpoolPressesMinimal) {
            System.out.println(this.name + " has drown " + enemyPokemon.name);
            this.enemyPokemon.setHealthPoints(0);
        } else {
            System.out.println(enemyPokemon.name + " wasn't drown, ");
            this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - enterCount);
        }
        ultimateUsed = true;
        System.out.println();
    }
}

