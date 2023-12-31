import java.util.Random;

public class EnemyPokemon2 extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;

    EnemyPokemon2() {
        super();
        this.name = "Jigglypuff";
        this.appearance = "Gum-like Pokémon";
        this.type = "Gum-type";
        this.size = "Small";
        //this.setHealthPoints(55);
        this.setHealthPoints(110);
        this.setAttackPoints(40);
        this.setDefensePoints(35);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }

    public boolean isUltimateUsed() {
        return ultimateUsed;
    }

    public int getLastChosenAttack() {
        return lastChosenAttack;
    }

    boolean chooseAttack() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;

        if (randomNumber == 1 && lastChosenAttack != 1) {
            attack1();
        } else if (randomNumber == 2 && lastChosenAttack != 2) {
            attack2();
        } else if (randomNumber == 3 && !ultimateUsed) {
            ultimate();
        } else {
            chooseAttack();
        }
        return false;
    }


    void attack1() throws InterruptedException {
        lastChosenAttack = 1;
        System.out.println(chosenPokemon.name + " is attacked by Pound.");
        this.chosenPokemon.setHealthPoints(this.chosenPokemon.getHealthPoints() - getAttackPoints());
    }

    void attack2() throws InterruptedException {
        lastChosenAttack = 2;
        System.out.println("Play Rough.");
        int punches = 0;
        while (punches < 3) {
            this.chosenPokemon.setHealthPoints(this.chosenPokemon.getHealthPoints() - 10);
            if(chosenPokemon.getHealthPoints() > 0) {
                System.out.println("The health of the your Pokemon is " + chosenPokemon.getHealthPoints());
                Thread.sleep(500);
            }
            punches++;
        }
    }

    void ultimate() throws InterruptedException {
        lastChosenAttack = 3;
        System.out.println("Ultimate attack!");
        System.out.println("Hypnosis");
        System.out.println(chosenPokemon.name +" is hypnotised.");
        ultimateUsed = true;
        chooseAttack2();
        System.out.println();
    }
    void chooseAttack2() throws InterruptedException {
        chooseAttack2Recursive(0);
    }

    private void chooseAttack2Recursive(int count) throws InterruptedException {
        if (count < 2) {
            //TODO
            Random random = new Random();
            int randomNumber = random.nextInt(2) + 1;

            if (randomNumber == 1 && lastChosenAttack != 1) {
                attack1();
            } else  {
                attack2();
            }
                chooseAttack2Recursive(count + 1);
        }
    }
}


