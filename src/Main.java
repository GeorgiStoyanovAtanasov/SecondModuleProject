//before you read this code,  you should know that the plural for pokemon is pokemon not pokemons

import java.util.ArrayList;
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
    protected static ArrayList<Pokemon> arrayListOfChosenPokemon = new ArrayList<>(3);


    public static void menu() {
        System.out.println("1 - Pikachu.");
        System.out.println("2 - Charizard.");
        System.out.println("3 - Snorlax.");
        System.out.println("4 - Bulbasaur.");
        System.out.println("5 - Gyarados.");
    }

    public static void realMenu() {
        for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
            System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
        }
    }

    public static void round1(Pokemon chosenPokemon) throws InterruptedException {
        realMenu();
        Scanner sc = new Scanner(System.in);
        chosenPokemon.enemyPokemon = new EnemyPokemon1();
        chosenPokemon.enemyPokemon.strengthModifier();
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        displayHealth(chosenPokemon);
        System.out.println();

        while (chosenPokemon.getHealthPoints() > 0 && chosenPokemon.enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();
            if (chosenPokemon.enemyPokemon.getHealthPoints() <= 0) {
                System.out.println();
                System.out.println("The enemy pokemon has fallen. VICTORY");
                round2(chosenPokemon);
                break;
            }

            displayHealth(chosenPokemon);
            chosenPokemon.enemyPokemon.chooseAttack();
            System.out.println();
            Thread.sleep(2000);
            displayHealth(chosenPokemon);
            while (chosenPokemon.getHealthPoints() <= 0) {
                EnemyPokemon1 testPokemon = new EnemyPokemon1();
                int enemyPokemonHealthAfterNewChosenPokemonIsAssigned = chosenPokemon.enemyPokemon.getHealthPoints();
                System.out.println("Your Pokemon has fallen.");
                arrayListOfChosenPokemon.remove(chosenPokemon);
                realMenu();
                int choice = sc.nextInt();
                for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
                    if (choice == (i + 1)) {
                        chosenPokemon = arrayListOfChosenPokemon.get(i);
                    }
                }
                if (chosenPokemon.getHealthPoints() <= 0) {
                    System.out.println("This pokemon has already fallen, choose another one");
                } else {
                    chosenPokemon.enemyPokemon = new EnemyPokemon1();
                    chosenPokemon.enemyPokemon.setHealthPoints(enemyPokemonHealthAfterNewChosenPokemonIsAssigned);
                    chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
                    System.out.println("The health of your new pokemon is " + chosenPokemon.getHealthPoints());
                    System.out.println("The health of the enemy pokemon is " + chosenPokemon.enemyPokemon.getHealthPoints());
                }
            }
        }
    }


    public static void round2(Pokemon chosenPokemon) throws InterruptedException {
        realMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("A wild enemy Pokemon has appeared!");

        chosenPokemon.enemyPokemon = new EnemyPokemon2();
        chosenPokemon.enemyPokemon.strengthModifier();
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);

        displayHealth(chosenPokemon);
        System.out.println();

        while (chosenPokemon.getHealthPoints() > 0 && chosenPokemon.enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();

            if (chosenPokemon.enemyPokemon.getHealthPoints() <= 0) {
                System.out.println("The enemy Pokemon has fainted. VICTORY!");
                round3(chosenPokemon);
                break;
            }

            displayHealth(chosenPokemon);
            chosenPokemon.enemyPokemon.chooseAttack();
            System.out.println();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            displayHealth(chosenPokemon);

            if (chosenPokemon.getHealthPoints() <= 0) {
                EnemyPokemon2 enemyPokemon2 = (EnemyPokemon2) chosenPokemon.enemyPokemon;
                chosenPokemon = handleFaintedPokemon(chosenPokemon);
                chosenPokemon.enemyPokemon = enemyPokemon2;
                chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
            }
        }
    }

    public static void round3(Pokemon chosenPokemon) throws InterruptedException {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tries = 0;

        try {
            System.out.println("Choose 3 pokemon for your team.");
            menu();
            while (tries < 3) {
                Pokemon chosenPokemon;
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        chosenPokemon = pokemon1;
                        break;
                    case 2:
                        chosenPokemon = pokemon2;
                        break;
                    case 3:
                        chosenPokemon = pokemon3;
                        break;
                    case 4:
                        chosenPokemon = pokemon4;
                        break;
                    case 5:
                        chosenPokemon = pokemon5;
                        break;
                    default:
                        throw new IllegalStateException("This choice is not available.");
                }
                if (!arrayListOfChosenPokemon.contains(chosenPokemon)) {
                    arrayListOfChosenPokemon.add(chosenPokemon);
                    tries++;
                } else {
                    System.out.println("You cannot choose the same pokemon twice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("The pokemon that you have chosen are: ");
        for (Pokemon chosen : arrayListOfChosenPokemon) {
            chosen.strengthModifier();
            System.out.print(chosen.name + ", ");
        }
        System.out.println();
        int choiceForPokemonToBeginTheFirstRoundWith;
        do {
            System.out.println("Choose a pokemon to begin the tournament with:\n" +
                    "1 - for " + arrayListOfChosenPokemon.get(0).name + "\n" +
                    "2 - for " + arrayListOfChosenPokemon.get(1).name + "\n" +
                    "3 - for " + arrayListOfChosenPokemon.get(2).name);
            choiceForPokemonToBeginTheFirstRoundWith = sc.nextInt();

            try {
                switch (choiceForPokemonToBeginTheFirstRoundWith) {
                    case 1:
                        round1(arrayListOfChosenPokemon.get(0));
                        break;
                    case 2:
                        round1(arrayListOfChosenPokemon.get(1));
                        break;
                    case 3:
                        round1(arrayListOfChosenPokemon.get(2));
                        break;
                    default:
                        System.out.println("Enter a valid choice.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (choiceForPokemonToBeginTheFirstRoundWith < 1 || choiceForPokemonToBeginTheFirstRoundWith > 3);
    }

    private static Pokemon handleFaintedPokemon(Pokemon chosenPokemon) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your Pokemon has fainted.");
        realMenu();
        int choice = scanner.nextInt();
        chosenPokemon = arrayListOfChosenPokemon.get(choice - 1);

        if (chosenPokemon.getHealthPoints() <= 0) {
            System.out.println("This Pokemon has already fainted. Choose another one.");
            return handleFaintedPokemon(chosenPokemon);
        } else {
            return chosenPokemon;
        }
    }

    private static void displayHealth(Pokemon chosenPokemon) {
        System.out.println("Your Pokemon's health: " + chosenPokemon.getHealthPoints());
        System.out.println("Enemy Pokemon's health: " + chosenPokemon.enemyPokemon.getHealthPoints());
    }
}