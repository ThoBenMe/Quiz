package de.thro.inf;

import de.thro.inf.Question;
import org.junit.Test;

/**
 * Test class for testing question class.
 *
 * @author Thomas Meza on 17.06.2018.
 */
public class QuestionTest {

    /**
     * Tests if IllegalArgumentException is thrown on setQuestion if argument is null value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setQuestionNull() {
        Question q = new Question();
        q.setQuestion(null);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setQuestion if argument is empty string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setQuestionEmpty() {
        Question q = new Question();
        q.setQuestion("");
    }

    /**
     * Tests if IllegalArgumentException is thrown on setCorrectAnswer if argument is null value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setCorrectAnswerNull() {
        Question q = new Question();
        q.setCorrectAnswer(null);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setCorrectAnswer if argument is null value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setCorrectAnswerEmpty() {
        Question q = new Question();
        q.setCorrectAnswer("");
    }

    /**
     * Tests if NullPointerException is thrown on setFalseAnswers if argument is null value.
     */
    @Test(expected = NullPointerException.class)
    public void setFalseAnswersNull() {
        Question q = new Question();
        q.setFalseAnswers(null);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setFalseAnswers if argument is string array with size != 3.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setFalseAnswersArraySize() {
        Question q = new Question();
        q.setFalseAnswers(new String[1]);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setFalseAnswers if argument is string array containing empty
     * or null valued strings.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setFalseAnswersIllegalArgument() {
        Question q = new Question();
        q.setFalseAnswers(new String[]{"", "", null});
    }
}