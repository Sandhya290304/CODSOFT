import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int currentRound = 0;
        int totalScore = 0;

        System.out.println("Get ready for the Number Guessing Challenge!!");

        do {
            int targetNumber = generateRandomNumber(LOWER_BOUND, UPPER_BOUND, random);
            int roundScore = playRound(targetNumber, MAX_ATTEMPTS, scanner, currentRound);

            if (roundScore > 0) {
                totalScore += roundScore;
            }

            System.out.println("Score check: " + totalScore);
            currentRound++;

        } while (playAgain(scanner));

        System.out.println("\nThanks for playing! You're a guessing genius. ");
        System.out.println("Final score revealed! : " + totalScore);
        scanner.close();
    }

    private static int generateRandomNumber(int lowerBound, int upperBound, Random random) {
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static int playRound(int targetNumber, int maxAttempts, Scanner scanner, int currentRound) {
        System.out.println("\n--------------------------");
        System.out.println("Guessing? Round up No: " + (currentRound + 1));
        System.out.println("Guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND);
        System.out.println("--------------------------");

        int attempts = 0;
        int userGuess;

        while (attempts < maxAttempts) {
            userGuess = getUserGuess(scanner);
            attempts++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                return maxAttempts - attempts + 1;
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        System.out.println("Oops! Attempts exhausted. Correct number was  " + targetNumber);
        return 0;
    }

    private static int getUserGuess(Scanner scanner) {
        System.out.print("Drop your guess: ");
        return scanner.nextInt();
    }

    private static boolean playAgain(Scanner scanner) {
        System.out.print("Ready for another round? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();
        return playAgain.equals("yes");
    }
}
