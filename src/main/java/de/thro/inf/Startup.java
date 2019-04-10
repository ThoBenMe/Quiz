package de.thro.inf;

/**
 * This project is a project to pass the PStA on University of Applied Sciences Rosenheim.
 * <p>
 * =======================
 * How to run the project:
 * =======================
 * <p>
 * 1. Build and run
 * <p>
 * 2. Enter "play" to play the quiz game
 * => You'll be asked 10 random questions from data.dat,
 * answer them in your own assessment.
 * Following answers are allowed: a, b, c or d
 * <p>
 * 3. After these 10 questions, your score is printed and
 * you'll be asked to play again
 * <p>
 * 4. Once you restarted the game, it is possible to enter "edit"
 * to add questions to the data.dat or to clear the highscore.
 * To add questions: Enter command "addq", you'll be asked
 * for the question string, then after the right answer
 * and last but not least, type in three wrong answers.
 * To clear the highscore list, enter "reset".
 * You'll be prompted if you truly want to clear the scores.
 * <p>
 * 5. Also you can step back to main menu by entering "back"
 * or exit the game by typing in "exit".
 * <p>
 * Main class to call any subclasses and methods; in conclusion to run the program.
 *
 * @author Thomas Meza on 15.06.2018.
 */
public class Startup {
    private static FileIO fileIO = new FileIO();
    private static Data data = new Data();

    /**
     * Main class to run the programm at all.
     * First some console output about the project itself, then reading data and starting console input handler.
     *
     * @param args run arguments
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Das Quiz");
        System.out.println("Ein PRG2 Projekt des WS17/18");
        System.out.println("der Hochschule Rosenheim");
        System.out.println("University of Applied Sciences Rosenheim");
        System.out.println("========================================");
        System.out.println();

        fileIO.readQuestions();
        fileIO.readScores();

        /**Runs console input handler */
        ConsoleIO consoleIO = new ConsoleIO();
        consoleIO.readName();
        consoleIO.gameLoop();
    }

    /**
     * Returns static de.thro.inf.FileIO instance for all classes.
     *
     * @return de.thro.inf.FileIO instance
     */
    public static final FileIO getFileIO() {
        return fileIO;
    }

    /**
     * Returns static de.thro.inf.Data instance for all classes.
     *
     * @return de.thro.inf.Data instance
     */
    public static final Data getData() {
        return data;
    }
}
