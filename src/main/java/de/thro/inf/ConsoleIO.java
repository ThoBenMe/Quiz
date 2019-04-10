package de.thro.inf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Class to handle user console i/o.
 *
 * @author Jacob von Perger on 12.06.2018.
 */
public class ConsoleIO {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);

    private static final Player user = new Player();
    private static String command;

    private static final String playCommand = "play";
    private static final String highscoreCommand = "highscore";
    private static final String editCommand = "edit";
    private static final String exitCommand = "exit";
    private static final String addqCommand = "addq";
    private static final String resetCommand = "reset";
    private static final String backCommand = "back";
    private static final String[] yesNoCommands = {"y", "n"};
    private static final char[] answers = {'a', 'b', 'c', 'd'};

    /**
     * Requests username input.
     */
    public void readName() {
        System.out.println("Bitte gib Deinen Namen ein: ");
        user.setName(scanner.nextLine());
    }

    /**
     * Runs loop, main game happening. Calls input command handler.
     */
    public void gameLoop() {
        boolean doRepeat = false;
        do {
            if (!doRepeat) {
                System.out.println("Um das Quiz zu spielen, gib '" + playCommand + "' ein");
                System.out.println("Um alle bisherigen Highscores auszugeben, gib '" + highscoreCommand + "' ein");
                System.out.println("Um die Fragen zu bearbeiten, gib '" + editCommand + "' ein");
                System.out.println("Um das Quiz zu verlassen, gib '" + exitCommand + "' ein");
            }

            boolean isCommandAllowed = false;
            while (!isCommandAllowed) {
                if (!doRepeat) {
                    command = scanner.next();
                } else {
                    command = playCommand;
                }

                isCommandAllowed = true;
                if (command.equals(playCommand)) {
                    playGame();
                    printCurrentScore();
                    printHighscoreList();
                    doRepeat = requestRevenge();
                } else if (command.equals(highscoreCommand)) {
                    printHighscoreList();
                } else if (command.equals(editCommand)) {
                    editGame();
                } else if (command.equals(exitCommand)) {
                    return;
                } else {
                    isCommandAllowed = false;
                    System.out.println("Falsche Eingabe! Bitte gib '" + playCommand + "' ODER '" + editCommand + "' ein!");
                }
            }
        } while (doRepeat || command.equals(backCommand));
    }

    /**
     * Returns index of answer in array.
     *
     * @param inputChar a, b, c, d
     * @return index in array
     */
    private final int getAnswerIndex(char inputChar) {
        for (int i = 0; i < answers.length; ++i) {
            if (inputChar == answers[i]) {
                return i;
            }
        }
        throw new IllegalArgumentException(inputChar + " not allowed, expected: a, b, c or d");
    }

    /**
     * Runs quiz game, prints questions and asks for right answer (10 times).
     */
    public final void playGame() {
        Question[] question = Startup.getData().getRandomizedQuestions();
        for (int i = 0; i < 10; ++i) {
            String[] randomizedAnswers = Startup.getData().getRandomizedAnswers(question[i]);


            System.out.println();
            System.out.println((i + 1) + ". " + question[i].getQuestion());

            for (int j = 0; j < randomizedAnswers.length; ++j) {
                System.out.println("\t" + answers[j] + ". " + randomizedAnswers[j]);
            }

            System.out.println();
            System.out.println("Wie lautet deine Antwort?");

            char inputChar = ' ';
            boolean isAnswerAllowed = false;
            while (!isAnswerAllowed) {
                inputChar = scanner.next().toLowerCase().charAt(0);
                for (int j = 0; j < randomizedAnswers.length; ++j) {
                    if (inputChar == answers[j]) {
                        isAnswerAllowed = true;
                        break;
                    }
                }

                if (!isAnswerAllowed) {
                    System.out.println("Falsche Eingabe! Gib a, b, c oder d ein: ");
                }

            }

            if (randomizedAnswers[getAnswerIndex(inputChar)].equals(question[i].getCorrectAnswer())) {
                System.out.println("Richtige Antwort, sehr gut!");
                user.incrementScore();
            } else {
                System.out.println("Das ist leider falsch!");
                user.decrementScore();
            }
        }
        Startup.getData().addScore(user.getName(), user.getScore());
        Startup.getFileIO().writeScores();
    }

    /**
     * Prints player's current score.
     */
    public void printCurrentScore() {
        System.out.println("Dein de.thro.inf.Score beträgt: " + user.getScore());
    }

    /**
     * Prints whole highscore list.
     */
    public void printHighscoreList() {
        System.out.println("Das sind alle bisherigen Highscores: ");

        List<Score> highscores = Startup.getData().getHighscoresSorted();
        for (Score score : highscores) {
            System.out.println(score.getName() + ": " + score.getScore());
        }

        if (highscores.size() == 0) {
            System.out.println("Leider hat noch niemand gespielt!");
        }

        System.out.println();
    }

    /**
     * Asks player for a revenge and returns if a new game is wanted.
     *
     * @return if new game is wanted
     */
    public final boolean requestRevenge() {
        System.out.println("Nochmal spielen? y/n");
        String answer = null;
        try {
            answer = bufferedReader.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isAnswerAllowed = false;

        do {
            if (answer.equals(yesNoCommands[0]) || answer.equals(yesNoCommands[1])) {
                return answer.equals(yesNoCommands[0]);
            } else {
                System.out.println("Falsche Eingabe! Gib '" + yesNoCommands[0] + "' oder '" + yesNoCommands[1] + "' ein: ");
                try {
                    answer = bufferedReader.readLine().toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (!isAnswerAllowed);
        return false;
    }

    /**
     * Lets player edit the game. Allows adding questions, resetting the highscore list, exit the program
     * or go back to main menu.
     */
    public void editGame() {
        System.out.println("Um eine Frage hinzuzufügen, gib '" + addqCommand + "' ein");
        System.out.println("Um den Highscore zurückzusetzen, gib '" + resetCommand + "' ein");
        System.out.println("Um zum Hauptmenü zurückzukehren, gib '" + backCommand + "' ein");
        System.out.println("Um das Quiz zu verlassen, gib '" + exitCommand + "' ein");

        boolean isCommandAllowed = false;
        while (!isCommandAllowed) {
            command = scanner.next();

            if (command.equals(addqCommand)) {
                Question question = new Question();
                String[] answers = new String[3];

                try {
                    System.out.println("Wie lautet die Frage?");
                    question.setQuestion(bufferedReader.readLine());

                    System.out.println("Wie lautet die RICHTIGE Antwort?");
                    question.setCorrectAnswer(bufferedReader.readLine());

                    for (int i = 0; i < 3; ++i) {
                        System.out.println("Wie lautet die " + (i + 1) + ". falsche Antwort?");
                        answers[i] = bufferedReader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                question.setFalseAnswers(answers);

                Startup.getData().getQuestions().add(question);
                Startup.getFileIO().writeQuestions();
                isCommandAllowed = true;
            } else if (command.equals(resetCommand)) {
                System.out.print("Willst Du den Highscore wirklich unwiderruflich leeren? y/n: ");

                if (scanner.next().toLowerCase().equals(yesNoCommands[0])) {
                    Startup.getData().clearScores();
                }
                isCommandAllowed = true;
            } else if (command.equals(backCommand)) {
                return;
            } else if (command.equals(exitCommand)) {
                return;
            } else {
                System.out.println("Falsche Eingabe! Bitte gib '" + addqCommand + "' ODER '" + resetCommand + "' ein!");
            }

        }
    }
}
