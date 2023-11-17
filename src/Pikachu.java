import java.util.Scanner;

public class Pikachu extends Pokemon {
    Pikachu() {
        super();
        this.name = "Pikachu";
        this.appearance = "Mouse-like Pokémon";
        this.type = "Electric-type";
        this.size = "Small";
        this.setHealthPoints(20);
        //this.setHealthPoints(60);
        this.setAttackPoints(50);
        this.setDefensePoints(40);
    }


    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }

    @Override
    void chooseAttack() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose attack: 1 - Thunder shock, 2 - Thunderbolt, 3 - ultimate(can be used only once the tournament).");
        byte choice = sc.nextByte();
        if (choice == 1) {
            attack1();
        } else if (choice == 2) {
            attack2();
        } else if (choice == 3) {
            ultimate();
        } else {
            System.out.println("You have used an illegal skill, you are disqualified.");
            System.exit(0);
        }
    }


    void attack1() {
        System.out.println(this.enemyPokemon.name + " is attacked by Thunder shock.");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 20);
        // plus this method will make the opponent skip an attack because of the shock
        //System.out.println("Your opponent is stunned, you can attack again.");


    }

    void attack2() {
        System.out.println("Thunderbolt.");
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - this.getAttackPoints());
    }

    void ultimate() {
        //can be used only once during a battle
    }
}
