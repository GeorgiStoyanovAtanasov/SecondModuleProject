import java.util.Scanner;

public class Charizard extends Pokemon{
    private int addedStrength = 0;
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    Charizard() {
        this.name = "Charizard";
        this.appearance = "Dragon-like Pokémon";
        this.type = "Fire-type";
        this.size = "Normal";
        this.setHealthPoints(180);
        this.setAttackPoints(84);
        this.setDefensePoints(78);
    }


    @Override
    void strengthModifier() {

    }

    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Dragon's Breath, 2 - Inferno Blitz, 3 - Ultimate(can be used by a pokemon only once during the tournament).");
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
        System.out.println(enemyPokemon.name + " has been attacked by Dragon's Breath.");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 40 - this.addedStrength);
        lastChosenAttack = 1;
    }

    void attack2() {
        System.out.println("Inferno Blitz.");
        if(lastChosenAttack == 0){
            lastChosenAttack = 1;
        }
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - (this.getAttackPoints()/lastChosenAttack));
        lastChosenAttack = 2;
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        System.out.println("Breaking out");
        enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - 40);
        if(enemyPokemon.getHealthPoints() > 0) {
            System.out.println(enemyPokemon.getHealthPoints());
            this.addedStrength = 20;
            System.out.println("Every time " + this.name + " attacks, there will be additional 20 damage.");
        }
        ultimateUsed = true;
        System.out.println();
    }
}
