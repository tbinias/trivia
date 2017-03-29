package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.*;

public class GameRunnerTest {

    private final String master_123_101 = "Chet was added\n" + "They are player number 1\n" + "Pat was added\n"
            + "They are player number 2\n" + "Sue was added\n" + "They are player number 3\n"
            + "Chet is the current player\n" + "They have rolled a 1\n" + "Chet's new location is 1\n"
            + "The category is Science\n" + "Science Question 0\n" + "Question was incorrectly answered\n"
            + "Chet was sent to the penalty box\n" + "Pat is the current player\n" + "They have rolled a 2\n"
            + "Pat's new location is 2\n" + "The category is Sports\n" + "Sports Question 0\n"
            + "Answer was corrent!!!!\n" + "Pat now has 1 Gold Coins.\n" + "Sue is the current player\n"
            + "They have rolled a 3\n" + "Sue's new location is 3\n" + "The category is Rock\n" + "Rock Question 0\n"
            + "Question was incorrectly answered\n" + "Sue was sent to the penalty box\n";

    @Test
    public void name() throws Exception {
        assertEquals(master_123_101, play(new int[]{1,2,3}, new boolean[]{true,false,true}));
    }

    public String play(int[] dice, boolean[] answers) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bout);
        System.setOut(printStream);
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        boolean notAWinner = false;
        for (int i = 0; i < dice.length; i++) {

            aGame.roll(dice[i]);

            if (answers[i]) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        };
        return new String(bout.toByteArray());
    }
}