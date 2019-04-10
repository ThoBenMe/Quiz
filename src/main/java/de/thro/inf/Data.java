package de.thro.inf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to store all available questions and highscores read by de.thro.inf.FileIO class.
 *
 * @author Baran Yildirim on 12.06.2018.
 */
public class Data {

    private final List<Score> highscores = new LinkedList<>();
    private final List<Question> questions = new LinkedList<>();

    /**
     * Returns a sorted map of all available highscores.
     *
     * @return sorted highscores map
     */
    public final List<Score> getHighscoresSorted() {
        highscores.sort(Comparator.comparing(Score::getScore).reversed());
        return highscores;
    }

    /**
     * Adds question instance by parameter to local list.
     *
     * @param question question instance
     */
    public final void addNewQuestion(Question question) {
        if (question == null) {
            throw new NullPointerException("question object is null");
        }
        questions.add(question);
    }

    /**
     * Removes question instance by parameter in local list.
     *
     * @param question
     */
    public final void removeQuestion(Question question) {
        if (!questions.remove(question)) {
            throw new IllegalArgumentException("question object not existing in questions list");
        }
    }

    /**
     * Returns an array with size of 10 containing random sorted question instances.
     *
     * @return question array
     */
    public final Question[] getRandomizedQuestions() {
        Question[] result = new Question[10];

        if (questions.size() < 10) {
            throw new RuntimeException("Too few questions in list questions. Actual: " + questions.size()
                    + "; Expected: 10");
        }
        for (int i = 0; i < result.length; ++i) {

            Question question;
            boolean questionExists;

            /* Check if question is already in array, pick another one if */
            do {
                int randomNum = ThreadLocalRandom.current().nextInt(0, questions.size() - 1);
                question = questions.get(randomNum);
                questionExists = false;
                for (int j = 0; j < result.length; ++j) {
                    if (result[j] == question) {
                        questionExists = true;
                        continue;
                    }
                }
            }

            while (questionExists);

            result[i] = question;
        }

        return result;
    }


    /**
     * Clears highscore and clears score file too.
     */
    public final void clearScores() {
        highscores.clear();
        Startup.getFileIO().writeScores();
    }

    /**
     * Adds new highscore to list and writes to file too.
     *
     * @param name  player name
     * @param score player score
     */
    public final void addScore(String name, int score) {
        highscores.add(new Score(name.replaceAll("\t", ""), score));
        Startup.getFileIO().writeScores();
    }

    /**
     * Returns the four suitable answers (the correct one and the false ones) in a randomized sort.
     *
     * @param question question instance
     * @return
     */
    public final String[] getRandomizedAnswers(Question question) {
        if (question.getFalseAnswers().length != 3) {
            throw new RuntimeException("Wrong amount of wrong answers. Actual: " + question.getFalseAnswers().length
                    + "; Expected: 3");
        }

        String[] falseAnswers;
        String[] allAnswers = new String[4];

        falseAnswers = question.getFalseAnswers();
        int randomNum = ThreadLocalRandom.current().nextInt(0, allAnswers.length);
        allAnswers[randomNum] = question.getCorrectAnswer();
        for (int i = 0, j = 0; i < allAnswers.length; ++i) {
            if (allAnswers[i] == null) {
                allAnswers[i] = falseAnswers[j];
                ++j;
            }
        }
        return allAnswers;
    }

    /**
     * Returns all existing question instances in a list.
     */
    public final List<Question> getQuestions() {
        return questions;
    }
}
