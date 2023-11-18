public class Gyarados extends Pokemon{
    Gyarados() {
        this.name = "Gyarados";
        this.appearance = "Piscine, draconic Pokémon with a long serpentine body covered in slightly overlapping scales";
        this.type = "Water";
        this.size = "Large";
        this.setHealthPoints(95);
        this.setAttackPoints(125);
        this.setDefensePoints(79);
    }
    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() + (this.getHealthPoints()/5));
        this.setAttackPoints(this.getAttackPoints() + (this.getAttackPoints()/5));
        this.setDefensePoints((this.getDefensePoints() + (this.getDefensePoints()/5)));
    }
    @Override
    void chooseAttack() {

    }
}
