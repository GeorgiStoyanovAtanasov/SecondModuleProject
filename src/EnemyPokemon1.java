import java.util.Random;

public class EnemyPokemon1 extends Pokemon {
    private boolean ultimateUsed = false;
    private int lastChosenAttack = 0;
    EnemyPokemon1() {
        this.name = "Eevee";
        this.appearance = "mammalian creature with brown fur, a bushy tail that has a cream-colored tip, and a furry collar that is also cream-colored.";
        this.type = "Ordinary-type";
        this.size = "Small";
        //this.setHealthPoints(55);
        this.setHealthPoints(110);
        //this.setAttackPoints(25);
        this.setAttackPoints(55);
        this.setDefensePoints(50);
    }
    public void setUltimateUsed(boolean ultimateUsed){
        this.ultimateUsed = ultimateUsed;
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }
    @Override
    boolean chooseAttack() throws InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;

        if (randomNumber == 1 && lastChosenAttack != 1) {
            attack1();
        } else if (randomNumber == 2 && lastChosenAttack != 2 && ultimateUsed) {
            attack2();
        } else if (randomNumber == 3 && !ultimateUsed) {
            ultimate();
        } else {
            chooseAttack();
        }
        return false;
    }
    void attack1() {
        lastChosenAttack = 1;
        System.out.println(chosenPokemon.name + " has been attacked by Quick Attack");
            chosenPokemon.setHealthPoints(chosenPokemon.getHealthPoints() - getAttackPoints());
    }
    void attack2() throws InterruptedException {
        lastChosenAttack = 2;
        System.out.println("Psychic Impact.");
        chosenPokemon.setHealthPoints(chosenPokemon.getHealthPoints() - getAttackPoints());
        if(chosenPokemon.getHealthPoints()>0) {
            System.out.println(chosenPokemon.name + " has procured cognitive overload.");
            System.out.println("The system will decide whether or not " + chosenPokemon.name + " can continue fighting.");
            int loops = 0;
            while(loops < 10) {
                System.out.println("Loading...");
                Thread.sleep(1500);
                loops++;
            }
            if(chosenPokemon.getHealthPoints() > this.getAttackPoints()){
                System.out.println("The system has concluded that " + chosenPokemon.name + " is able to keep on fighting.");
            } else {
                System.out.println("The system has concluded that " + chosenPokemon.name + " is not able to keep on fighting.");
                chosenPokemon.setHealthPoints(0);
            }
        }
    }
    void ultimate(){
        lastChosenAttack = 3;
        System.out.println("Evolution.");
        final String oldName = this.name;
        this.name = "Umbreon";
        this.appearance = "mammalian creature which resembles a black cat or rabbit.";
        this.type = "Dark-type";
        this.size = "Small";
        //this.setHealthPoints(80);
        this.setHealthPoints(160);
        this.setAttackPoints(55);
        this.setDefensePoints(100);
        strengthModifier();
        ultimateUsed = true;
        System.out.println(oldName + " has evolved into Umbreon.");
        System.out.println();
    }
}
