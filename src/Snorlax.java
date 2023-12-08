import java.util.Scanner;

public class Snorlax extends Pokemon {
    private int lastChosenAttack = 0;
    private boolean secretComboUsed = false;

    Snorlax() {
        this.name = "Snorlax";
        this.appearance = "Noseless ursid-like Pokémon that has a cream-colored face, hands, and feet, while the rest of its body is teal in color";
        this.type = "Ordinary";
        this.size = "Large";
        //this.setHealthPoints(160);
        this.setHealthPoints(220);
        this.setAttackPoints(110);
        this.setDefensePoints(160);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() + (this.getHealthPoints() / 5));
        this.setAttackPoints(this.getAttackPoints() + (this.getAttackPoints() / 5));
        this.setDefensePoints((this.getDefensePoints() + (this.getDefensePoints() / 5)));
    }

    @Override
    boolean chooseAttack() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        if(!secretComboUsed) {
            System.out.println("Snorlax has a secret combo, you may want to try and find it, hint: think about both attacks.");
        }
        System.out.println("Choose attack: 1 - Smash, 2 - Melee.");
        String choice = sc.nextLine();
        System.out.println();
        String trim = choice.trim();
        if (choice.equals("1") && lastChosenAttack != 1) {
            attack1();
        } else if (choice.equals("2") && lastChosenAttack != 2) {
            attack2();
        } else if (trim.equals("21") && !secretComboUsed) {
            secretCombo();
        } else {
            System.out.println("You are trying to use an an illegal move.");
            chooseAttack();
        }
        return false;
    }

    void attack1() throws InterruptedException {
        lastChosenAttack = 1;
        System.out.println(enemyPokemon.name + " is hit by Smash.");
        System.out.println(enemyPokemon.name + " is sent flying.");
        if (enemyPokemon.getHealthPoints()>0) {
            enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - (getAttackPoints() / 2) + enemyPokemon.getDefensePoints());
            if (enemyPokemon.getHealthPoints() > 0) {
                Thread.sleep(7000);
                System.out.println(enemyPokemon.name + " has hit the ground.");
                enemyPokemon.setHealthPoints(enemyPokemon.getHealthPoints() - (getAttackPoints() / 2));
                System.out.println();
            }
        }
    }

    void attack2() throws InterruptedException {
        lastChosenAttack = 2;
        System.out.println("Melee.");
        int punches = 0;
        while (punches < 3) {
            if(enemyPokemon.getHealthPoints() > 0) {
                this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 15);
                System.out.println("The health of " + enemyPokemon.name + " is " + enemyPokemon.getHealthPoints());
                Thread.sleep(500);
            }
            punches++;
        }
    }
    void secretCombo() throws InterruptedException {
        attack1();
        if(enemyPokemon.getHealthPoints() > 0) {
            System.out.println(enemyPokemon.getHealthPoints());
            attack2();
        }
        secretComboUsed = true;
    }
}
