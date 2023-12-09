import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Pokemon> arrayListOfChosenPokemon = new ArrayList<>();

    private static List<Pokemon> enemyPokemon = generateEnemies();

    private static List<Pokemon> generateEnemies() {
        return List.of(new EnemyPokemon1(), new EnemyPokemon2(), new EnemyPokemon3(), new EnemyPokemon4(), new EnemyPokemon5());
    }

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

    public static void round(Pokemon chosenPokemon, Pokemon enemyPokemon) throws InterruptedException {
        int counter = 1;
        realMenu();
        chosenPokemon.enemyPokemon = enemyPokemon;
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        displayHealth(chosenPokemon, enemyPokemon);

        while (chosenPokemon.getHealthPoints() > 0 && enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();

            if (enemyPokemon.getHealthPoints() <= 0) {
                System.out.println("The enemy Pokemon has fainted. VICTORY!");
                counter++;
                switch (counter) {
                    case 1:
                        chosenPokemon.enemyPokemon = new EnemyPokemon1();
                        break;
                    case 2:
                        chosenPokemon.enemyPokemon = new EnemyPokemon2();
                        break;
                    case 3:
                        chosenPokemon.enemyPokemon = new EnemyPokemon3();
                        break;
                    case 4:
                        //round4(chosenPokemon);
                        break;
                }
                continue;
            }

            displayHealth(chosenPokemon, enemyPokemon);

            enemyPokemon.chooseAttack();
            System.out.println();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            displayHealth(chosenPokemon, enemyPokemon);

            if (chosenPokemon.getHealthPoints() <= 0) {
                chosenPokemon = handleFaintedPokemon();
                chosenPokemon.enemyPokemon = enemyPokemon;
                chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
                displayHealth(chosenPokemon, enemyPokemon);
            }
        }
    }

    public static void round4(Pokemon chosenPokemon) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        chosenPokemon.enemyPokemon = chosenPokemon;
        chosenPokemon.chooseAttack();
        chosenPokemon.enemyPokemon = new EnemyPokemon4();

        if (!chosenPokemon.enemyPokemon.chooseAttack()) {
            chosenPokemon.enemyPokemon = chosenPokemon;
        }

        chosenPokemon.enemyPokemon.chooseAttack();
        System.out.println(chosenPokemon.getHealthPoints());
        System.out.println(chosenPokemon.enemyPokemon.name + " " + chosenPokemon.enemyPokemon.getHealthPoints());
        System.out.println(chosenPokemon.name + " " + chosenPokemon.getHealthPoints());
        sc.next();  // You might want to use the input for something else, otherwise, this line can be removed.
    }

    public static void finalRound(Pokemon chosenPokemon) throws InterruptedException {
        Pokemon enemyPokemon = new EnemyPokemon5();
        chosenPokemon.enemyPokemon = enemyPokemon;
        chosenPokemon.strengthModifier();
        chosenPokemon.enemyPokemon.strengthModifier();
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        chosenPokemon.enemyPokemon.chooseAttack();
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
                        chosenPokemon = new Pikachu();
                        break;
                    case 2:
                        chosenPokemon = new Charizard();
                        break;
                    case 3:
                        chosenPokemon = new Snorlax();
                        break;
                    case 4:
                        chosenPokemon = new Bulbasaur();
                        break;
                    case 5:
                        chosenPokemon = new Gyarados();
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

            System.out.println("\nThe pokemon that you have chosen are: ");
            for (Pokemon chosen : arrayListOfChosenPokemon) {
                chosen.strengthModifier();
                System.out.print(chosen.name + ", ");
            }
            System.out.println();

            for (int roundNumber = 1; roundNumber <= 3; roundNumber++) {
                System.out.println("Round " + roundNumber + " begins!");
                int choiceForPokemonToBeginRoundWith;
                do {
                    System.out.println("Choose a pokemon to begin the round with:");
                    for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
                        System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
                    }

                    choiceForPokemonToBeginRoundWith = sc.nextInt();

                    try {
                        Pokemon chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
                        round(chosenPokemon, enemyPokemon.get(roundNumber - 1));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Enter a valid choice.");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (choiceForPokemonToBeginRoundWith < 1 || choiceForPokemonToBeginRoundWith > 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Pokemon handleFaintedPokemon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your Pokemon has fainted.");
        realMenu();
        int choice = scanner.nextInt();
        Pokemon chosenPokemon = arrayListOfChosenPokemon.get(choice - 1);

        if (chosenPokemon.getHealthPoints() <= 0) {
            System.out.println("This Pokemon has already fainted. Choose another one.");
            return handleFaintedPokemon();
        } else {
            return chosenPokemon;
        }
    }

    private static void displayHealth(Pokemon chosenPokemon, Pokemon enemyPokemon) {
        if (chosenPokemon.getHealthPoints() > 0) {
            System.out.println("Your Pokemon's health: " + chosenPokemon.getHealthPoints());
        }
        if (enemyPokemon.getHealthPoints() > 0) {
            System.out.println("Enemy Pokemon's health: " + enemyPokemon.getHealthPoints());
        }
    }
}