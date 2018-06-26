package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import strategy.Mode;

/**
 * ScoreManager is a singleton class for manipulate this class's instance that
 * can generate score symbols to identify type of score which should have shown
 * on scoreboard.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class ScoreManager {

	/**
	 * The instance arbitrates access to the resource and storage-related state
	 * information.
	 */
	private static ScoreManager score = null;

	/**
	 * Text to divide symbol which can separate the type of score which score should
	 * shown.
	 */
	private static String symbol;
	/** List of Score */
	private List<Score> listScore;

	/**
	 * Initialize list of score.
	 */
	private ScoreManager() {
		listScore = new ArrayList<Score>();
	}

	/**
	 * Get the ScoreManager's instance.
	 * 
	 * @return the ScoreManager's instance.
	 */
	public static ScoreManager getInstance() {
		if (score == null) {
			symbol = "+";
			score = new ScoreManager();
		}
		return score;
	}

	/**
	 * Change the Score's symbol type.
	 * 
	 * @param symbol
	 *            - text to divide symbol which can separate the type of score which
	 *            score should shown.
	 */
	public static void setSymbol(String symbol) {
		ScoreManager.symbol = symbol;
		score = new ScoreManager();
	}

	/**
	 * Get the Score's symbol type.
	 * 
	 * @return text to divide symbol which can separate the type of score which
	 *         score should shown.
	 */
	public String getsymbol() {
		return symbol;
	}

	/**
	 * Sort score by ascending the time.
	 * 
	 * @param list
	 *            of score which must be sort in this method by time.
	 */
	public void sortTime(List<Score> list) {
		Collections.sort(list, new Comparator<Score>() {
			@Override
			public int compare(Score time1, Score time2) {
				return time1.getTime().compareTo(time2.getTime());
			}

		});
	}

	/**
	 * Read scores from filter by symbols.
	 * 
	 * @param list of score which reads by filter symbol's type.
	 * @throws by the FileInputStream, FileOutputStream, and RandomAccessFile
	 *             constructors when a file with the specified pathname does not
	 *             exist.
	 */
	@SuppressWarnings("resource")
	public List<Score> readScore() throws FileNotFoundException {
		try {
			String path = System.getProperty("user.dir");
			String filename = path + "score.md";
			InputStream in = new FileInputStream(filename);
			Scanner readText;
			readText = new Scanner(in);
			Score save = null;
			while (readText.hasNextLine()) {
				String score = readText.nextLine();
				if (score.startsWith(symbol)) {
					String name = score.split(",")[0].trim();
					String time = score.split(",")[1].trim();
					save = new Score(name, time);
					listScore.add(save);
				}
			}
			if (in != null)
				try {
					in.close();
				} catch (IOException ioe) {
				}
		} catch (FileNotFoundException e) {
			System.out.println("Didn't have any score.");
			System.out.println("Finish the game first");
		}
		return listScore;
	}

	/**
	 * Write scores with adding symbols to the text.
	 * 
	 * @param name - name of user to record.
	 * @param time - time which user uses to solve the puzzle in game.
	 */
	public void recordScore(String name, String time) {
		String path = System.getProperty("user.dir");
		String outputfile = path + "score.md";
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputfile, true);
		} catch (FileNotFoundException ex) {
			System.out.println("Couldn't open output file " + outputfile);
			return;
		}
		PrintStream printOut = new PrintStream(out);
		if (Mode.getInstance().getSize() == 3) {
			if (Mode.getInstance().getClass().getName().equals("strategy.EasyStrategy")) {
				printOut.printf("+%s , %s\n", name, time);
				symbol = "+";
			}

			else if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("@%s , %s\n", name, time);
				symbol = "@";
			}

			else if (Mode.getInstance().getClass().getName().equals("strategy.GreaterThanStrategy")) {
				printOut.printf("?%s , %s\n", name, time);
				symbol = "?";
			}
		} else if (Mode.getInstance().getSize() == 4) {
			if (Mode.getInstance().getClass().getName().equals("strategy.EasyStrategy")) {
				printOut.printf("&%s , %s\n", name, time);
				symbol = "&";
			} else if (Mode.getInstance().getClass().getName().equals("strategy.HardStrategy")) {
				printOut.printf("*%s , %s\n", name, time);
				symbol = "*";
			}
		}
		printOut.close();

	}

}
