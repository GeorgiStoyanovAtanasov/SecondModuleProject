public class Snorlax extends Pokemon{
    Snorlax() {
        this.name = "Snorlax";
        this.appearance = "Noseless ursid-like Pokémon that has a cream-colored face, hands, and feet, while the rest of its body is teal in color";
        this.type = "Ordinary";
        this.size = "Large";
        this.setHealthPoints(160);
        this.setAttackPoints(110);
        this.setDefensePoints(160);
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
