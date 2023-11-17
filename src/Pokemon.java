import java.util.ArrayList;

public abstract class Pokemon extends Battle{
    protected String name;
    protected String appearance;
    protected String type;
    protected String size;
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;
    ArrayList<Pokemon> listOfPokemon = new ArrayList<>();
    public Pokemon enemyPokemon;
    public Pokemon chosenPokemon;

    void setChosenPokemon(Pokemon pokemon) {
        chosenPokemon = pokemon;
    }

    public void round1() {
        enemyPokemon = new EnemyPokemon1();
        System.out.println(enemyPokemon.name);
        chosenPokemon.chooseAttack();
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
    abstract void chooseAttack();
}
