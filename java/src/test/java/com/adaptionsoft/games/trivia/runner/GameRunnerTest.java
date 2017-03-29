package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class GameRunnerTest {

    public static final String MASTER_RESOURCES_PATH = "src/test/resources/master";

    @Test
    @Ignore
    public void createMaster() throws Exception {
        new File(MASTER_RESOURCES_PATH).mkdirs();
        for (int i = 0; i < 100; i++) {
            String masterResult = play(i);
            new FileOutputStream(MASTER_RESOURCES_PATH + "/seed_" + i + ".txt").write(masterResult.getBytes());
        }
    }

    @Test
    public void approveMaster() throws Exception {
        for (int i = 0; i < 100; i++) {
            List<String> strings = Files.readAllLines(Paths.get(MASTER_RESOURCES_PATH +  "/seed_" + i + ".txt"));
            StringBuilder builder = new StringBuilder();
            for (String line : strings) {
                builder.append(line);
                builder.append("\n");
            }
            assertEquals(builder.toString(), play(i));
        }
    }

    private String play(long seed) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bout);
        System.setOut(printStream);

        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(seed);

        boolean notAWinner = false;
        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);

        return new String(bout.toByteArray());
    }
}