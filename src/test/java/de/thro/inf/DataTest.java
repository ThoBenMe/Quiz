package de.thro.inf;

import de.thro.inf.Data;
import de.thro.inf.Question;
import de.thro.inf.Score;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Tests diverse methods of class de.thro.inf.Data.
 *
 * @author Thomas Meza on 17.06.2018.
 */
public class DataTest {

    /**
     * Tests if getHighscoresSorted actually returns a sorted list of scores.
     */
    @Test
    public void getHighscoresSorted() {
        Data d = new Data();
        d.addScore("A", 50);
        d.addScore("B", 30);
        d.addScore("C", 70);
        List<Score> l = d.getHighscoresSorted();

        Assert.assertEquals(l.get(0).getScore(), 70);
        Assert.assertEquals(l.get(1).getScore(), 50);
        Assert.assertEquals(l.get(2).getScore(), 30);
    }

    /**
     * Tests if NullPointerExpection is thrown on adding null reference.
     */
    @Test(expected = NullPointerException.class)
    public void addNewQuestion() {
        Data d = new Data();
        d.addNewQuestion(null);
    }

    /**
     * Tests if IllegalArgumentException is thrown on removing a not existing question.
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeQuestion() {
        Data d = new Data();
        d.removeQuestion(new Question());
    }

    /**
     * Tests if RuntimeException is thrown on requesting randomized question list while question list size is <= 10.
     */
    @Test(expected = RuntimeException.class)
    public void getRandomizedQuestions() {
        Data d = new Data();
        d.getRandomizedQuestions();
    }

    /**
     * Tests if RuntimeException is thrown on requesting randomized answers from empty de.thro.inf.Question instance.
     */
    @Test(expected = RuntimeException.class)
    public void getRandomizedAnswers() {
        Data d = new Data();
        d.getRandomizedAnswers(new Question());
    }
}