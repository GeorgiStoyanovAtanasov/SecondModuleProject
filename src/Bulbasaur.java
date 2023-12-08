import java.util.Scanner;

public class Bulbasaur extends Pokemon{
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    Bulbasaur() {
        this.name = "Bulbasaur";
        this.appearance = "Blue-green bodies with darker blue-green spots";
        this.type = "Grass-type";
        this.size = "Small";
        //this.setHealthPoints(45);
        this.setHealthPoints(90);
        this.setAttackPoints(25);
        this.setDefensePoints(49);
    }
    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints()/10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints()/10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints()/10)));
    }

    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Leech Seed, 2 - Double-Edge, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
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
        System.out.println(enemyPokemon.name + " has been attacked by Leech Seed.");
        Thread.sleep(1000);
        System.out.println(this.name + "'s seed has drained a part of " + enemyPokemon.name + " health \n " + "and transferred it to " + this.name);
            this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - this.getAttackPoints());
            this.setHealthPoints(this.getHealthPoints() + this.getAttackPoints());
        lastChosenAttack = 1;
        Thread.sleep(1000);
    }

    void attack2() throws InterruptedException {
        System.out.println("Double-Edge.");
        Thread.sleep(1000);
        System.out.println(this.name + "is charging at " + enemyPokemon.name);
        Thread.sleep(1500);
        System.out.println(this.name + "has slammed into" + enemyPokemon.name);
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 50);
        //bulbasaur's health is lowered because of the recoil
        this.setHealthPoints(this.getHealthPoints() - 10);
        lastChosenAttack = 2;
    }

    void ultimate() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        Thread.sleep(1000);
        System.out.println("Solar Beam");
        Thread.sleep(1000);
        int power = 0;
        while(power < 100){
            System.out.println("Ultimate attack is " + power + "% charged.");
            System.out.println("Press 'Enter' to charge ultimate attack!");
            String userInput = sc.nextLine();
            if (userInput.isEmpty()) {
                power = power + 5;
            }
        }
        enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - power);
        if(enemyPokemon.getHealthPoints() > 0) {
            System.out.println(enemyPokemon.getHealthPoints());
        }
        ultimateUsed = true;
        Thread.sleep(1000);
    }
}
