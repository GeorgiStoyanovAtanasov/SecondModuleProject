public class Bulbasaur extends Pokemon{
    Bulbasaur() {
        this.name = "Bulbasaur";
        this.appearance = "Blue-green bodies with darker blue-green spots";
        this.type = "Grass-type";
        this.size = "Small";
        this.setHealthPoints(45);
        this.setAttackPoints(49);
        this.setDefensePoints(49);
    }
    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints()/10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints()/10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints()/10)));
    }

    @Override
    void chooseAttack() {

    }
}
