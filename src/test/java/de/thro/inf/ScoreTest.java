package de.thro.inf;

import de.thro.inf.Score;
import org.junit.Test;

/**
 * Test class for testing score methods.
 *
 * @author Thomas Meza on 17.06.2018.
 */
public class ScoreTest {

    /**
     * Tests if IllegalArgumentException is thrown on setName if argument is null.
     * (setName is called in ctor)
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNameNull() {
        Score s = new Score(null, 0);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setName if argument is empty string.
     * (setName is called in ctor)
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNameEmpty() {
        Score s = new Score("", 0);
    }
}