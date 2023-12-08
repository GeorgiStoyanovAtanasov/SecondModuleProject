import java.util.ArrayList;

public abstract class Pokemon{
    protected String name;
    protected String appearance;
    protected String type;
    protected String size;
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;
    ArrayList<Pokemon> listOfPokemon = new ArrayList<>();
    public boolean ultimateUsed;
    public Pokemon enemyPokemon;
    public Pokemon chosenPokemon;

    void setChosenPokemon(Pokemon pokemon) {
        chosenPokemon = pokemon;
    }
    void setUltimateUsed(boolean ultimateUsed){
        this.ultimateUsed = ultimateUsed;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    abstract void strengthModifier();
    abstract boolean chooseAttack() throws InterruptedException;
    //abstract void chooseAttackAfterUltimate();
}
