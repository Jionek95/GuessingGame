package com.jionek.game;

import java.util.Random;

public class GuessingGame {
    private final int randomNumber = new Random().nextInt(1, 11);
    private int counter = 0;

    public String guess(int guessedNum) {
        counter++;
        String tryWordOption = counter == 1 ?  "try" : "tries";
        String winningMessage = String.format("You got it in %d %s!", counter, tryWordOption);
        String response = null;

        if (counter == 4 && guessedNum != getRandomNumber()){
            response = String.format("You didn't get it and you had %d %s. The game is over!", counter, tryWordOption);
        } else if ( counter > 4) {
            response = String.format("You are limited to only 4 %s. Your game is over!", tryWordOption);
        } else {
            String numberTooLowTooHigh = null;
            if (guessedNum < getRandomNumber()){
                numberTooLowTooHigh = "You're too low!";
            } else if (guessedNum > getRandomNumber()) {
                numberTooLowTooHigh = "You're too high!";
            }
            String message = String.format("You didn't get it - %s", numberTooLowTooHigh);
            response = guessedNum == getRandomNumber() ? winningMessage : message;
        }
        return response;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();

        boolean loopShouldContinue = true;

        do {
            String input = System.console().readLine("Enter the number: ");
            if ("q".equals(input)){
                System.out.printf("The game is closed");
                return;
            }
            String output = game.guess(Integer.parseInt(input));
            System.out.println(output);
            if (output.contains("over") || output.contains("got it")) {
                loopShouldContinue = false;
            }
        } while (loopShouldContinue);
    }
}
