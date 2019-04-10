package de.thro.inf;

/**
 * Class to handle player stats.
 *
 * @author Thomas Meza on 12.06.2018.
 */
public class Player {
    private int score;
    private String name;

    /**
     * Constructor sets default value (score = 0) and name to empty string.
     */
    public Player() {
        score = 0;
        setName("none");
    }

    /**
     * Method to increment the player's current score by 10.
     */
    public final void incrementScore() {
        score += 10;
    }

    /**
     * Method to decrement the player's current score by 5.
     */
    public final void decrementScore() {
        score -= 5;
    }

    /**
     * Returns the current user name.
     *
     * @return user name
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns current user's score
     *
     * @return
     */
    public final int getScore() {
        return score;
    }

    /**
     * Sets current user name.
     *
     * @param name user name
     */
    public final void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty.");
        }

        this.name = name;
    }
}
