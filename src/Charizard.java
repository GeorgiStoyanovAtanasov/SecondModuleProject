public class Charizard extends Pokemon{
    Charizard() {
        this.name = "Charizard";
        this.appearance = "Dragon-like Pokémon";
        this.type = "Fire-type";
        this.size = "Normal";
        this.setHealthPoints(90);
        this.setAttackPoints(84);
        this.setDefensePoints(78);
    }
    @Override
    void strengthModifier() {

    }

    @Override
    void chooseAttack() {
        this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 20);
        // this.enemyPokemon.setHealthPoints(this.enemyPokemon.getHealthPoints() - 20);
    }
}
