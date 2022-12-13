package com.jionek.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessingGameTest {

    public static final int GAME_RANDOMNESS_RETRIES = 80;
    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    public void testSimpleWinSituation(){
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it in 1 try!", message);
    }

    @Test
    public void testSimpleWrongNegSituation(){
        String message = game.guess(-5);
        assertEquals("You didn't get it - you're too low!", message);
    }

    @Test
    public void testSimpleWrongPosSituation(){
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1);
        assertEquals("You didn't get it - you're too high!", message);
    }


    @RepeatedTest(10)
    public void testRandomNumberGeneration(){
        // 1 2 3 4 5 6 7 8 9 10
        // 1 0 1 1 1 1 1 0 1 1 = 10
        int[] rndNumCount = new int[11];

        for (int count = 0; count < GAME_RANDOMNESS_RETRIES; count ++) {
            GuessingGame game = new GuessingGame();
            int randomNum = game.getRandomNumber();
            rndNumCount[randomNum] = 1;
        }

        int sum = 0;
        for ( int count=0; count <11; count ++) {
            sum += rndNumCount[count];
        }

        assertEquals(10, sum);
    }

    @Test
    public void testFourWrongAnswers(){
        makeThreeWrongGuesses();
        String message = game.guess(-5);
        assertEquals("You didn't get it and you had 4 tries. The game is over!", message);
    }

    private void makeThreeWrongGuesses() {
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
    }

    @Test
    public void testThreeWrongAnswersAndOneRight(){
        makeThreeWrongGuesses();
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it in 4 tries!", message);
    }
    @Test
    public void testTwoWrongAnswersAndOneRight(){
        game.guess(-5);
        game.guess(-5);
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum);
        assertEquals("You got it in 3 tries!", message);
    }

    @Test
    public void testTenWrongAnswers(){
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        game.guess(-5);
        String message = game.guess(-5);
        assertEquals("You are limited to only 4 tries. Your game is over!", message);
    }
}
