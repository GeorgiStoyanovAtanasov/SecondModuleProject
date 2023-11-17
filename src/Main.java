import java.util.Scanner;

public class Main {

    //so far the code only works if the user chooses pokemon 1 and the only attack they can use is the first one because the other pokemon classes and attack methods are still in development
    protected static Pokemon pokemon1 = new Pikachu();
    protected static Pokemon pokemon2 = new Charizard();
    protected static Pokemon pokemon3 = new Snorlax();
    protected static Pokemon pokemon4 = new Bulbasaur();
    protected static Pokemon pokemon5 = new Gyarados();
    protected static Pokemon chosenPokemon1;
    protected static Pokemon chosenPokemon2;
    protected static Pokemon chosenPokemon3;

    public static void menu() {
        System.out.println("1 - Pikachu.");
        System.out.println("2 - Charizard.");
        System.out.println("3 - Snorlax.");
        System.out.println("4 - Bulbasaur.");
        System.out.println("5 - Gyarados.");
    }

    public static void round1(Pokemon chosenPokemon) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // System.out.println("Choose a pokemon to begin round the first round of the tournament:");
       // menu();

        chosenPokemon.enemyPokemon = new EnemyPokemon1();
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        System.out.println("The health of your pokemon is " + chosenPokemon.getHealthPoints());
        System.out.println("The health of the enemy pokemon is " + chosenPokemon.enemyPokemon.getHealthPoints());

        while (chosenPokemon.getHealthPoints() > 0 && chosenPokemon.enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();
            if (chosenPokemon.enemyPokemon.getHealthPoints() <= 0) {
                System.out.println("The enemy pokemon has fallen. VICTORY");
                round2(chosenPokemon);
                break;
            }

            System.out.println("The health of your pokemon is " + chosenPokemon.getHealthPoints());
            System.out.println("The health of the enemy pokemon is " + chosenPokemon.enemyPokemon.getHealthPoints());
            chosenPokemon.enemyPokemon.chooseAttack();
            System.out.println();
            Thread.sleep(3000);
            System.out.println("The health of your pokemon is " + chosenPokemon.getHealthPoints());
            System.out.println("The health of the enemy pokemon is " + chosenPokemon.enemyPokemon.getHealthPoints());
            //if (chosenPokemon.getHealthPoints() <= 0) {
                while (chosenPokemon.getHealthPoints() <= 0) {
                    EnemyPokemon1 testPokemon = new EnemyPokemon1();
                    int difference = chosenPokemon.enemyPokemon.getHealthPoints();
                    System.out.println("Your Pokemon has fallen.");
                    menu();
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> chosenPokemon = pokemon1;
                        case 2 -> chosenPokemon = pokemon2;
                        case 3 -> chosenPokemon = pokemon3;
                        case 4 -> chosenPokemon = pokemon4;
                        case 5 -> chosenPokemon = pokemon5;
                        default -> System.out.println("This choice is not available.");
                    }
                    if(chosenPokemon.getHealthPoints() <= 0){
                        System.out.println("This pokemon has already fallen, choose another one");
                    } else {
                        chosenPokemon.enemyPokemon = new EnemyPokemon1();
                        chosenPokemon.enemyPokemon.setHealthPoints(difference);
                        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
                        System.out.println("The health of your new pokemon is " + chosenPokemon.getHealthPoints());
                        System.out.println("The health of the enemy pokemon is " + chosenPokemon.enemyPokemon.getHealthPoints());
                    }
                }
            //}
        }
    }
    public static void round2(Pokemon chosenPokemon){
        //here we change the object enemyPokemon frm EnemyPokemon1 to EnemyPokemon2 and make the round2 method
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Pokemon chosenPokemon;
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> chosenPokemon = pokemon1;
                case 2 -> chosenPokemon = pokemon2;
                case 3 -> chosenPokemon = pokemon3;
                case 4 -> chosenPokemon = pokemon4;
                case 5 -> chosenPokemon = pokemon5;
                default -> throw new IllegalStateException("This choice is not available.");
            }
            round1(chosenPokemon);
        } catch(Exception e){
            throw new NullPointerException();
        }
    }
}