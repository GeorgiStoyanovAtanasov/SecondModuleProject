import java.util.Scanner;

public class EnemyPokemon4 extends Pokemon {
    private boolean isReflectionBroken = false;

    EnemyPokemon4() {
        this.name = "Ditto";
        this.appearance = "Gum-like Pokémon";
        this.type = "Reflection-type";
        this.size = "Small";
        //this.setHealthPoints(90);
        this.setHealthPoints(120);
        this.setAttackPoints(84);
        this.setDefensePoints(78);
    }

    @Override
    void strengthModifier() {
        this.setHealthPoints(this.getHealthPoints() - (this.getHealthPoints() / 10));
        this.setAttackPoints(this.getAttackPoints() - (this.getAttackPoints() / 10));
        this.setDefensePoints((this.getDefensePoints() - (this.getDefensePoints() / 10)));
    }

    public int playMiniGame() throws InterruptedException {
        System.out.println("Break through the reflection! Rapidly press the 'Enter' key.");
        System.out.println("Get ready...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3...");
        Thread.sleep(1000);
        System.out.println("2...");
        Thread.sleep(1000);
        System.out.println("1...");
        Thread.sleep(1000);
        System.out.println("GO");
        long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        int enterCount = 0;
        int timeThreshold = 5000;
        while (System.currentTimeMillis() - startTime < timeThreshold) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                enterCount++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time's up!");
        System.out.println("Number of times 'Enter' pressed: " + enterCount);
        return enterCount;
    }

    void printInfoForMiniGame() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        //System.out.println(chosenPokemon.enemyPokemon.chosenPokemon.getHealthPoints());
        System.out.println("Ditto is by many considered the strongest pokemon in the pokemon verse because it can reflect any attack thrown at it.");
        System.out.println("You are probably wondering, if it can reflect any attack, how am I supposed to beat it.");
        System.out.println("The only way to beat Ditto is by obtaining the Reflect Bypass.");
        System.out.println("It can be won only by completing a mini game.");
        System.out.println("You have only one attempt.");
        System.out.println("The game will be Rapid Key Press. Do you accept the challenge(yes or no).");
        String choice = sc.next();
        if (choice.equals("yes")) {
            int count = playMiniGame();
            if (count > 20) {
                //chosenPokemon.enemyPokemon = new EnemyPokemon4();
                this.isReflectionBroken = true;
            } else {
                System.out.println("Well, it looks like you won't be beating the game this time. You can still play through to the end, but there would be no point.");
                System.out.println("Do you want to resign(yes or not).");
                String choice2 = sc.next();
                if (choice2.equals("yes")) {
                    System.exit(0);
                }

            }
        }
    }

    @Override
    boolean chooseAttack() throws InterruptedException {
        if (this.isReflectionBroken) {
            System.out.println("Ditto cannot attack.");
        } else {
            printInfoForMiniGame();
        }
        return isReflectionBroken;
    }
}
