import java.util.Random;

public class EnemyPokemon1 extends Pokemon {
    EnemyPokemon1() {
        this.name = "Eevee";
        this.appearance = "mammalian creature with brown fur, a bushy tail that has a cream-colored tip, and a furry collar that is also cream-colored.";
        this.type = "Ordinary";
        this.size = "Small";
        this.setHealthPoints(55);
        this.setAttackPoints(55);
        this.setDefensePoints(50);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }
    @Override
    void chooseAttack() {
        //this is the random function for choosing an attack, which we will use when we make more than one attack
        //Random random = new Random();
        //int randomNumber = random.nextInt(3) + 1;
        attack1();
    }
    void attack1() {
        System.out.println(chosenPokemon.name + " has been attack by the first attack of Eevee");
            chosenPokemon.setHealthPoints(chosenPokemon.getHealthPoints() - 20);
    }
    //.
}
