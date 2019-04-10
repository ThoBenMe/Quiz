package de.thro.inf;

/**
 * Class to store player score information.
 *
 * @author Thomas Meza on 15.06.2018.
 */
public class Score {
    private String name;
    private int score;

    /**
     * Constructor initially sets name and score by parameters.
     *
     * @param name  name string
     * @param score score amount
     */
    public Score(String name, int score) {
        setName(name);
        setScore(score);
    }

    /**
     * Returns name as string.
     *
     * @return name string
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets name by given parameter.
     *
     * @param name name string
     */
    public final void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty");
        }

        this.name = name;
    }

    /**
     * Returns score amount.
     *
     * @return score amount
     */
    public final int getScore() {
        return score;
    }

    /**
     * Sets score amount by given parameter.
     *
     * @param score score amount
     */
    public final void setScore(int score) {
        this.score = score;
    }
}
