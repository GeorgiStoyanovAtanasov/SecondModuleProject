import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EnemyPokemon5 extends Pokemon {
    EnemyPokemon5() {
        this.name = "Greninja";
        this.appearance = "It resembles Toxicroak and has gained the appearance of a ninja. It is dark blue in color and has its tongue wrapped around its neck.";
        this.type = "Water/Dark-type";
        this.size = "Normal";
        //this.setHealthPoints(72);
        this.setHealthPoints(500);
        this.setAttackPoints(95);
        this.setDefensePoints(67);
    }

    @Override
    void strengthModifier() {
    }

    @Override
    boolean chooseAttack() throws InterruptedException {
        attack1();
        return false;
    }

    private static final String[] directions = {"up", "down", "left", "right"};

    public void attack1() throws InterruptedException {
        Set<String> attackDirections = pokemonAttack();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> userInputFuture = executorService.submit(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a direction to dodge to (up, down, left, right), you have 3 seconds to dodge:");
            String userDirection = scanner.nextLine().trim();
            return userDirection;
        });

        try {
            Thread.sleep(3000);
            if (userInputFuture.isDone()) {
                String userDirection = userInputFuture.get();
                if (isSafeDirection(userDirection, attackDirections)) {
                    System.out.println("You dodged the attack! You're safe!");
                } else {
                    System.out.println(chosenPokemon.name + "is it by a shuriken.");
                    chosenPokemon.setHealthPoints(chosenPokemon.getHealthPoints() - this.getAttackPoints());
                }
            } else {
                try {
                    Scanner sc = new Scanner(System.in);
                    System.out.println(chosenPokemon.name + " could not react, " + chosenPokemon.name + "is it by a shuriken.");
                    chosenPokemon.setHealthPoints(chosenPokemon.getHealthPoints() - this.getAttackPoints());
                    userInputFuture.cancel(true);
                } catch(Exception e){
                    System.out.println(chosenPokemon.name + " could not react, " + chosenPokemon.name + "is it by a shuriken.");
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Set<String> pokemonAttack() throws InterruptedException {
        Random random = new Random();
        System.out.println(this.name + " is attacking!");
        Set<String> attackDirections = new HashSet<>();
        attackDirections.add("towards you");
        Thread.sleep(1000);
        System.out.println("Shuriken thrown towards you!");
        while (attackDirections.size() < 4) {
            String direction = randomDirection(random);
            if (!direction.equals("towards you") && attackDirections.add(direction)) {
                System.out.println("Shuriken thrown " + direction + "!");
                Thread.sleep(1000);
            }
        }

        return attackDirections;
    }
    private static String randomDirection(Random random) {
        return directions[random.nextInt(directions.length)];
    }

    private static boolean isSafeDirection(String userDirection, Set<String> attackDirections) {
        for (String direction : directions) {
            if (!attackDirections.contains(direction) && userDirection.equalsIgnoreCase(direction)) {
                return true;
            }
        }
        return false;
    }
}
