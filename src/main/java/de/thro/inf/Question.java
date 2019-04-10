package de.thro.inf;

/**
 * Class to handle questions and answers
 *
 * @author Baran Yildirim on 12.06.2018.
 */
public class Question {
    private String question;
    private String correctAnswer;
    private String[] falseAnswers = new String[3];

    /**
     * Returns current question.
     *
     * @return current question.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the correct answer.
     *
     * @return correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the false answer.
     *
     * @return false answer
     */
    public String[] getFalseAnswers() {
        return falseAnswers;
    }

    /**
     * Sets current question
     *
     * @param question current question.
     */
    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("question string is empty");
        }
        this.question = question;
    }

    /**
     * Sets the question's correct answer.
     *
     * @param correctAnswer correct answer
     */
    public void setCorrectAnswer(String correctAnswer) {
        if (correctAnswer == null || correctAnswer.isEmpty()) {
            throw new IllegalArgumentException("correctAnswer string is null or empty");
        }
        this.correctAnswer = correctAnswer;
    }

    /**
     * Sets the question's false answer.
     *
     * @param falseAnswers false answer
     */
    public void setFalseAnswers(String[] falseAnswers) {
        if (falseAnswers == null) {
            throw new NullPointerException("falseAnswers is null");
        }
        if (falseAnswers.length != 3) {
            throw new IllegalArgumentException("Array size not allowed. Actual: " + falseAnswers.length
                    + "; Expected: " + 3);
        } else {
            for (int i = 0; i < falseAnswers.length; ++i) {
                if (falseAnswers[i] == null || falseAnswers[i].isEmpty()) {
                    throw new IllegalArgumentException("falseAnswers[" + i + "] string is null or empty");
                }
            }
        }

        this.falseAnswers = falseAnswers;
    }
}
