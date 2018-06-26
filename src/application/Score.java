package application;

/**
 * Score represents production with user's name and time.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class Score {
	/** Text of user name */
	private String name;
	/** Text of time by solving the puzzle */
	private String time;

	/**
	 * Initialize and record user's name and time by solving the puzzle.
	 * 
	 * @param name - user's name.
	 */
	public Score(String name, String time) {
		this.name = name.substring(1);
		this.time = time;
	}

	/**
	 * Get a user's name.
	 * 
	 * @return user's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get time by solving the puzzle.
	 * 
	 * @return time by solving the puzzle.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Format the user's name and time text.
	 * 
	 * @return text's information of Score per game.
	 */
	@Override
	public String toString() {
		return String.format("%-6s%20s", name, time);
	}
}
