package de.thro.inf;

import de.thro.inf.Player;
import org.junit.Test;

/**
 * Test class for testing player methods.
 *
 * @author Thomas Meza on 17.06.2018.
 */
public class PlayerTest {

    /**
     * Tests if IllegalArgumentException is thrown on setName if argument is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNameNull() {
        Player p = new Player();
        p.setName(null);
    }

    /**
     * Tests if IllegalArgumentException is thrown on setName if argument is empty string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setNameEmpty() {
        Player p = new Player();
        p.setName("");
    }
}