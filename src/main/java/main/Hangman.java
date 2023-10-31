package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        try {
            run();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void run() throws FileNotFoundException {


        Scanner scanner = new Scanner(new File("dictionaries.txt"));
        Scanner keyboard = new Scanner(System.in);


        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random random = new Random();

        String[] partsOfLine = words.get(random.nextInt(words.size())).split(",");
        String word = partsOfLine[0].trim();
        String vietnameseMeaning = partsOfLine[1].trim();

        List<Character> letterPlayerGuess = new ArrayList<>();


        int wrongCount = 0;
        while(true) {

            printState(word, letterPlayerGuess);
            printHangman(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word + ".");
                System.out.println("\n");
                break;
            }

            if (!playerGuessing(keyboard, word, letterPlayerGuess)) {
                wrongCount++;
            }

            if (printState(word, letterPlayerGuess)) {
                System.out.println("That's right, you win!");
                System.out.println("\n");
                break;
            }

            if (wrongCount >= 6) {
                System.out.println("Hint: The word means \"" + vietnameseMeaning + "\"");
            }

            System.out.println("You can try to guess what the word is: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("That's right, you win!");
                System.out.println("\n");
                break;
            } else {
                System.out.println("Nope! You can try again.");
                System.out.println("\n");
            }
        }

    }

    private static void printHangman(int wrongCount) {
        System.out.println("____________________");
        System.out.println("     #*#*#*#*#*#");
        System.out.println("     ##       | ");

        if (wrongCount >= 1) {
            System.out.println("     ##       O ");
        } else System.out.println("     ##        ");
        if (wrongCount >= 2) {
            System.out.print("     ##      \\ ");
        } else {
            System.out.println("     ##        ");
        }
        if (wrongCount >= 3) {
            System.out.println("/ ");
        } else {
            if (wrongCount == 2) System.out.println();
            System.out.println("     ##        ");
        }
        if (wrongCount >= 4) {
            System.out.println("     ##       | ");
        } else System.out.println("     ##        ");
        if (wrongCount >= 5) {
            System.out.print("     ##      / ");
        } else System.out.println("     ##        ");
        if (wrongCount >= 6) {
            System.out.println("\\ ");
        } else {
            if (wrongCount == 5) System.out.println();
        }
        System.out.println("_____##_____________");
    }

    private static boolean playerGuessing(Scanner keyboard, String word,
                                          List<Character> playerGuesses) {
        System.out.println("Which letter you guess the word has: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);

    }

    private static boolean printState(String word, List<Character> playerGuesses) {
        int correctCount = 0;

        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }

        System.out.println();

        return (word.length() == correctCount);
    }

}