package de.thro.inf;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * File to read and write files.
 *
 * @author Daniel Hentzschel on 12.06.2018.
 */
public class FileIO {
    /* de.thro.inf.Data path of file containing all questions and their answers. */
    private static final String dataFile = "data/data.dat";

    /* Scores path of file containing any scores. */
    private static final String scoresFile = "data/score.dat";

    /**
     * de.thro.inf.Cryptography class instance for encrypting and decrypting readable and writable strings.
     */
    private static final Cryptography cryptography = new Cryptography("NAiwns238ku566iq75Ah63hs");

    /**
     * Reads whole file and returns content as string.
     *
     * @param path file path to read
     * @return all text content of file
     */
    private final String readFromFile(String path) {
        if (path == dataFile && !new File(path).exists()) {
            /* Try catch not wanted, but IntelliJ insisted to surround code with try/catch */
            try {
                throw new FileNotFoundException("File " + dataFile + " not found.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            return new String(cryptography.decrypt(Files.readAllBytes(Paths.get(path))));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads question file. Parses and adds any questions to de.thro.inf.Data instance.
     */
    public final void readQuestions() {
        String[] fileContentSplitted = readFromFile(dataFile).split("\n");

        for (String line : fileContentSplitted) {
            String[] splittedLine = line.split("\t");

            if (splittedLine.length != 5) {
                System.err.println("Failed to read a putative question!");
                System.err.println("Splitted line parts:");

                for (String str : splittedLine) {
                    System.err.println(str);
                }

                continue;
            }

            Question question = new Question();

            question.setQuestion(splittedLine[0]);
            question.setCorrectAnswer(splittedLine[1]);

            /* Add all false answers to question instance */
            String[] falseAnswers = new String[3];
            for (int i = 2; i < splittedLine.length; ++i) {
                falseAnswers[i - 2] = splittedLine[i];
            }

            question.setFalseAnswers(falseAnswers);
            Startup.getData().addNewQuestion(question);
        }
    }

    /* Reads scores file. Parses and adds any score to de.thro.inf.Data instance. */
    public final void readScores() {
        String[] fileContentSplitted = readFromFile(scoresFile).split("\n");

        for (String line : fileContentSplitted) {
            String[] splittedLine = line.split("\t");

            if (splittedLine.length != 2) {
                continue;
            }

            /* Add score to scores map in de.thro.inf.Data instance */
            Startup.getData().addScore(splittedLine[0], Integer.parseInt(splittedLine[1]));
        }
    }

    /**
     * Deletes file on given path and then writes string to new created file.
     *
     * @param path file path
     * @param text string to write
     */
    private final void writeToFile(String path, String text) {
        File file = new File(path);

        /* Remove file to avoid collisions */
        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream stream = new FileOutputStream(path);
            stream.write(cryptography.encrypt(text.getBytes(StandardCharsets.UTF_8)));
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes any question data to file (used when question is added).
     */
    public final void writeQuestions() {
        List<Question> questions = Startup.getData().getQuestions();
        StringBuilder fileContent = new StringBuilder();

        /* Write question line by line */
        for (Question question : questions) {
            fileContent.append(question.getQuestion().trim());
            fileContent.append("\t");
            fileContent.append(question.getCorrectAnswer().trim());

            /* Append false answers to string */
            String[] falseAnswers = question.getFalseAnswers();
            for (int i = 0; i < falseAnswers.length; ++i) {
                fileContent.append("\t");
                fileContent.append(falseAnswers[i].trim());
            }
            fileContent.append("\n");
        }

        writeToFile(dataFile, fileContent.toString());
    }

    /* Writes any scores to score file. Executed after any game. */
    public final void writeScores() {
        List<Score> scores = Startup.getData().getHighscoresSorted();
        StringBuilder fileContent = new StringBuilder();

        /* Write highscore line by line */
        for (Score score : scores) {
            fileContent.append(score.getName());
            fileContent.append("\t");
            fileContent.append(score.getScore());
            fileContent.append("\n");
        }

        writeToFile(scoresFile, fileContent.toString());
    }

}
