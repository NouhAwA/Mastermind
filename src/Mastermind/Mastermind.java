package Mastermind;

import java.util.Scanner;

public class Mastermind {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] colors = {"red", "green", "orange", "yellow", "purple", "blue"};
        String[] answer = new String[4];
        boolean gameOver = false;
        int turn = 1;

        // Genereer een willekeurige code
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * colors.length);
            answer[i] = colors[rand];
        }

        // (optioneel) print de juiste code om te testen
        System.out.println("DEBUG (secret code): " + String.join(" ", answer));

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Mastermind!");
        System.out.println("Each round you get 2 hints:");
        System.out.println("'Black' = correct color in correct place");
        System.out.println("'White' = correct color in wrong place");
        System.out.println("You can choose from the colors: Red, Green, Yellow, Orange, Purple, Blue");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("<---- GUESS THE CODE AND GOODLUCK ---->");
        System.out.println();

        while (!gameOver && turn <= 10) {
            System.out.println("Turn " + turn + ": Choose 4 colors separated by spaces");
            String input = scanner.nextLine();
            String[] guess = input.trim().split("\\s+");

            if (guess.length != 4) {
                System.out.println("Please enter exactly 4 colors!\n");
                continue;
            }

            int black = 0;
            int white = 0;
            boolean[] answerMatched = new boolean[4];
            boolean[] guessMatched = new boolean[4];

            // Eerst: zwarte pinnen (juiste kleur, juiste plek)
            for (int i = 0; i < 4; i++) {
                if (guess[i].equalsIgnoreCase(answer[i])) {
                    black++;
                    answerMatched[i] = true;
                    guessMatched[i] = true;
                    System.out.println("Position " + (i + 1) + " (" + guess[i] + ") is BLACK");
                }
            }

            // Dan: witte pinnen (juiste kleur, verkeerde plek)
            for (int i = 0; i < 4; i++) {
                if (!guessMatched[i]) {
                    boolean foundWhite = false;
                    for (int j = 0; j < 4; j++) {
                        if (!answerMatched[j] && guess[i].equalsIgnoreCase(answer[j])) {
                            white++;
                            answerMatched[j] = true;
                            foundWhite = true;
                            System.out.println("Position " + (i + 1) + " (" + guess[i] + ") is WHITE");
                            break;
                        }
                    }
                    if (!foundWhite) {
                        System.out.println("Position " + (i + 1) + " (" + guess[i] + ") is NONE");
                    }
                }
            }

            // Samenvatting van de beurt
            System.out.println("\nBlack pins: " + black);
            System.out.println("White pins: " + white + "\n");

            // Win check
            if (black == 4) {
                System.out.println("<---- You have guessed the correct sequence! ---->");
                gameOver = true;
            } else if (turn == 10) {
                System.out.println("GAME OVER! You have used all your turns!");
                System.out.println("The correct sequence was: " + String.join(" ", answer));
                gameOver = true;
            }

            turn++;
        }

        scanner.close();
    }
}