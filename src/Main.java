import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Pokemon> arrayListOfChosenPokemon = new ArrayList<>();
    private static final Pokemon PokemonThePlayerCanChooseFrom1 = new Pikachu();
    private static final Pokemon PokemonThePlayerCanChooseFrom2 = new Charizard();
    private static final Pokemon PokemonThePlayerCanChooseFrom3 = new Snorlax();
    private static final Pokemon PokemonThePlayerCanChooseFrom4 = new Bulbasaur();
    private static final Pokemon PokemonThePlayerCanChooseFrom5 = new Gyarados();
    private static final PrizePokemon prizePokemon = new PrizePokemon();

    public static List<Pokemon> enemyPokemon = generateEnemies();

    public static void strengthModifyEnemyPokemon() {
        for (int i = 0; i < enemyPokemon.size(); i++) {
            enemyPokemon.get(i).strengthModifier();
        }
    }

    public static List<Pokemon> generateEnemies() {
        return List.of(new EnemyPokemon1(), new EnemyPokemon2(), new EnemyPokemon3(), new EnemyPokemon4(), new EnemyPokemon5());
    }
    public static boolean checkingWhetherOrNotThePrizePokemonHasBeenWon(){
        File file = new File("src/prizeCode.txt");
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNext()) {
                String fileContent = scanner.next();
                return "123".equals(fileContent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }
    public static void getThePrizePokemonPermanently(){
        try {
            FileWriter fileWriter = new FileWriter("src/prizeCode.txt");
            fileWriter.write("123");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Writing to file successful");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void playAudio() {
        try {
            System.out.println("The battles will begin after the hymn of pokemon.");
            String filePath = "src/pokemonHymn.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            Thread.sleep(205000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menu() {
        try {
            System.out.println("Welcome Player to the epic Pokémon League! \uD83C\uDF1F\n");
            Thread.sleep(2000);

            System.out.println("Get ready for an electrifying journey across 5 rounds of intense battles, \n" +
                    "where only the strongest will emerge victorious! \nYour skills as a Pokémon Trainer will be put to the ultimate test, and the thrill of victory awaits!\n");
            Thread.sleep(10000);

            System.out.println("\uD83C\uDFC6 Prize: The stakes are high, as the ultimate reward for your triumph\n" +
                    "will be a rare and powerful Pokémon that you can proudly add to your collection.\n");
            Thread.sleep(7000);

            System.out.println("\uD83D\uDD25 Team Selection: Choose wisely as you assemble your dream team from a pool of 5 incredible Pokémon. \n" +
                    "Each Pokémon comes with unique abilities and jaw-dropping ultimate moves or combos. \n" +
                    "Make strategic decisions to outsmart your opponents and claim victory!\n");
            Thread.sleep(13000);

            System.out.println("Now, let's meet your potential allies:\n");
            Thread.sleep(5000);

            System.out.println("Pikachu" +
                    "\nAbilities: Thunder shock, Thunderbolt\n" +
                    "Ultimate: Infinity lightning\n");
            Thread.sleep(5000);

            System.out.println("Charizard" +
                    "\nAbilities: Dragon's Breath, Inferno Blitz\n" +
                    "Ultimate: Breaking out\n");
            Thread.sleep(5000);

            System.out.println("Snorlax" +
                    "\nAbilities: Smash, Melee\n" +
                    "Secret Combo: ?\n");
            Thread.sleep(5000);

            System.out.println("Bulbasaur" +
                    "\nAbilities: Leech Seed, Double-Edge\n" +
                    "Ultimate: Solar Beam\n");
            Thread.sleep(5000);

            System.out.println("Gyarados" +
                    "\nAbilities: Rain Dance, Outrage\n" +
                    "Ultimate: Whirlpool\n");
            Thread.sleep(5000);
            if (prizePokemon.isThePlayerAllowedToUseThePrizePokemon()) {
                System.out.println("Arceus" +
                        "\nAbilities: death, dies\n" +
                        "Ultimate: unalive\n");
                Thread.sleep(5000);
            }

            System.out.println("May the best Trainer rise to the challenge, showcase their Pokémon mastery, and claim the title of Champion! \n" +
                    "May your journey be filled with excitement, strategy, and the thrill of becoming a Pokémon Master! Good luck! \uD83C\uDF08\uD83D\uDD25⚡\uFE0F\uD83C\uDF3F\uD83D\uDCA7\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void realMenu() {
        for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
            System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
        }
        System.out.println();
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

    public static void round4() throws InterruptedException {
        Pokemon chosenPokemon;
        Scanner sc = new Scanner(System.in);
        System.out.println("Round 4 begins!");
        int choiceForPokemonToBeginRoundWith;
        do {
            System.out.println("Choose a pokemon to begin the round with:");
            for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
                System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
            }

            choiceForPokemonToBeginRoundWith = sc.nextInt();
            chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
            if (chosenPokemon.getHealthPoints() < 0) {
                while (chosenPokemon.getHealthPoints() < 0) {
                    System.out.println("Enter a valid choice");
                    choiceForPokemonToBeginRoundWith = sc.nextInt();
                    chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
                }
            }
        } while (arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1).getHealthPoints() < 0);
        boolean isTheResultOfMiniGameGood = true;
        chosenPokemon.enemyPokemon = chosenPokemon;
        chosenPokemon.chooseAttack();
        if (chosenPokemon.getHealthPoints() <= 0) {
            chosenPokemon = handleFaintedPokemon();
        }
        chosenPokemon.enemyPokemon = enemyPokemon.get(3);
        if (!chosenPokemon.enemyPokemon.chooseAttack()) {
            isTheResultOfMiniGameGood = false;
            chosenPokemon.enemyPokemon = chosenPokemon;
            chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        }
        while (chosenPokemon.getHealthPoints() > 0 && chosenPokemon.enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();

            if (chosenPokemon.enemyPokemon.getHealthPoints() <= 0 && isTheResultOfMiniGameGood) {
                System.out.println("The enemy Pokemon has fainted. VICTORY!");
                finalRound();
                break;
            }
            if (chosenPokemon != chosenPokemon.enemyPokemon) {
                displayHealth(chosenPokemon, chosenPokemon.enemyPokemon);
                chosenPokemon.enemyPokemon.chooseAttack();
            } else if (chosenPokemon == chosenPokemon.enemyPokemon && chosenPokemon.getHealthPoints() > 0) {
                System.out.println("Your Pokemon's health: " + chosenPokemon.getHealthPoints());
            }
            System.out.println();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (chosenPokemon.getHealthPoints() <= 0 && !isTheResultOfMiniGameGood) {
                chosenPokemon = handleFaintedPokemon();
                chosenPokemon.enemyPokemon = chosenPokemon;
                chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
            }
        }
    }

    public static void finalRound() throws InterruptedException {
        Pokemon chosenPokemon;
        Scanner sc = new Scanner(System.in);
        System.out.println("Round 5 begins!");
        int choiceForPokemonToBeginRoundWith;
        do {
            System.out.println("Choose a pokemon to begin the round with:");
            for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
                System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
            }
            choiceForPokemonToBeginRoundWith = sc.nextInt();
            chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
            if (chosenPokemon.getHealthPoints() < 0) {
                while (chosenPokemon.getHealthPoints() < 0) {
                    System.out.println("Enter a valid choice");
                    choiceForPokemonToBeginRoundWith = sc.nextInt();
                    chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
                }
            }
        } while (arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1).getHealthPoints() < 0);
        chosenPokemon.enemyPokemon = enemyPokemon.get(4);
        chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
        while (chosenPokemon.getHealthPoints() > 0 && chosenPokemon.enemyPokemon.getHealthPoints() > 0) {
            chosenPokemon.chooseAttack();
            displayHealth(chosenPokemon, chosenPokemon.enemyPokemon);
            if (chosenPokemon.enemyPokemon.getHealthPoints() <= 0) {
                System.out.println("The enemy Pokemon has fainted. VICTORY!");
                //Here we will put the method for reward
                getThePrizePokemonPermanently();
                break;
            }
            displayHealth(chosenPokemon, chosenPokemon.enemyPokemon);
            chosenPokemon.enemyPokemon.chooseAttack();
            System.out.println();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (chosenPokemon.getHealthPoints() <= 0) {
                chosenPokemon = handleFaintedPokemon();
                chosenPokemon.enemyPokemon = chosenPokemon;
                chosenPokemon.enemyPokemon.setChosenPokemon(chosenPokemon);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (checkingWhetherOrNotThePrizePokemonHasBeenWon()){
            prizePokemon.makeThePrizePokemonAllowed();
        }
        strengthModifyEnemyPokemon();
        //playAudio();
        //this method turns on the pokemon song
        Scanner sc = new Scanner(System.in);
        int tries = 0;
        try {
            System.out.println("Choose 3 pokemon for your team.");
            menu();
            while (tries < 3) {
                Pokemon chosenPokemon = null;
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        chosenPokemon = PokemonThePlayerCanChooseFrom1;
                        break;
                    case 2:
                        chosenPokemon = PokemonThePlayerCanChooseFrom2;
                        break;
                    case 3:
                        chosenPokemon = PokemonThePlayerCanChooseFrom3;
                        break;
                    case 4:
                        chosenPokemon = PokemonThePlayerCanChooseFrom4;
                        break;
                    case 5:
                        chosenPokemon = PokemonThePlayerCanChooseFrom5;
                        break;
                    case 6:
                        if (prizePokemon.isThePlayerAllowedToUseThePrizePokemon()) {
                            chosenPokemon = prizePokemon;
                        } else {
                            System.out.println("This choice isn't available.");
                        }
                        break;
                    default:
                        throw new IllegalStateException("This choice is not available.");
                }

                if (!arrayListOfChosenPokemon.contains(chosenPokemon) && chosenPokemon != null) {
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
            System.out.println("Congratulations on assembling your team.");
            System.out.println("You have a few minutes to prepare for the battle while the hymn of pokemon is playing, \n" +
                    "do not waste them.");
            //NNNNNNNNNNNNNNNNNNNNNNNNNNNN
            //finalRound();
            //HHHHHHHHHHHHHHHHHHHHHHHHHH
            playAudio();
            //finalRound();
            outerLoop:
            for (int roundNumber = 1; roundNumber <= 3; roundNumber++) {
                System.out.println("Round " + roundNumber + " begins!");
                int choiceForPokemonToBeginRoundWith;

                //do {
                System.out.println("Choose a pokemon to begin the round with:");
                for (int i = 0; i < arrayListOfChosenPokemon.size(); i++) {
                    System.out.println((i + 1) + " - " + arrayListOfChosenPokemon.get(i).name);
                }
                choiceForPokemonToBeginRoundWith = sc.nextInt();
                if (choiceForPokemonToBeginRoundWith < 1 || choiceForPokemonToBeginRoundWith > arrayListOfChosenPokemon.size()) {
                    while (choiceForPokemonToBeginRoundWith < 1 || choiceForPokemonToBeginRoundWith > arrayListOfChosenPokemon.size()) {
                        System.out.println("Enter a valid choice.");
                        choiceForPokemonToBeginRoundWith = sc.nextInt();
                    }
                }
                try {
                    Pokemon chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
                    if (chosenPokemon.getHealthPoints() <= 0 || choiceForPokemonToBeginRoundWith < 1 || choiceForPokemonToBeginRoundWith > arrayListOfChosenPokemon.size()) {
                        while (chosenPokemon.getHealthPoints() <= 0 || choiceForPokemonToBeginRoundWith < 1 || choiceForPokemonToBeginRoundWith > arrayListOfChosenPokemon.size()) {
                            if (chosenPokemon.getHealthPoints() <= 0) {
                                System.out.println("Selected Pokemon has fainted. Re-selecting...");
                            }
                            choiceForPokemonToBeginRoundWith = sc.nextInt();
                            chosenPokemon = arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1);
                        }
                        //continue outerLoop;
                    }
                    round(chosenPokemon, enemyPokemon.get(roundNumber - 1));

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Enter a valid choice.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //} while (arrayListOfChosenPokemon.get(choiceForPokemonToBeginRoundWith - 1).getHealthPoints() > 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        round4();
    }

    public static Pokemon handleFaintedPokemon() {
        boolean allFainted = arrayListOfChosenPokemon.stream()
                .allMatch(obj -> obj.getHealthPoints() <= 0);
        if (allFainted) {
            System.out.println("You have lost.\n" +
                    "Well every great thing in life comes to an end\n" +
                    "sooner or later, but unlike those you can try again.");
            System.exit(0);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your Pokemon has fainted.");
        realMenu();
        int choice = scanner.nextInt();
        if (choice < 1 || choice > arrayListOfChosenPokemon.size()) {
            System.out.println("Enter a valid choice.");
            return handleFaintedPokemon();
        }
        Pokemon chosenPokemon = arrayListOfChosenPokemon.get(choice - 1);

        if (chosenPokemon.getHealthPoints() <= 0) {
            System.out.println("This Pokemon has already fainted. Choose another one.");
            return handleFaintedPokemon();
        } else {
            return chosenPokemon;
        }
    }

    public static void displayHealth(Pokemon chosenPokemon, Pokemon enemyPokemon) {
        if (chosenPokemon.getHealthPoints() > 0) {
            System.out.println("Your Pokemon's health: " + chosenPokemon.getHealthPoints());
        }
        if (enemyPokemon.getHealthPoints() > 0) {
            System.out.println("Enemy Pokemon's health: " + enemyPokemon.getHealthPoints());
        }
    }
}