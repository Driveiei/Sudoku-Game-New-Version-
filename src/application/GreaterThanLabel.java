package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import logic.GridManager;
import strategy.Mode;

/**
 * GreaterThanLabel creates greater or less than symbols into the Pane(GridPane)
 * in 'Greater than Sudoku' mode which will set image depends on adjacent cells.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class GreaterThanLabel {

	/** Declare Mode for using to get puzzle. */
	private Mode mode;

	/**Pane for all symbols*/
	Pane greaterPane;
	
	/** Use for adjust translate of symbols */
	private final int TWO = 2;

	/** Image for each symbols. */
	private Image vLeft = new Image("source/v-left.png");
	private Image vUp = new Image("source/v-up.png");
	private Image vDown = new Image("source/v-down.png");
	private Image vRight = new Image("source/v-right.png");

	/**
	 * Initialize and add the symbols to the Pane(GridPane).
	 * 
	 * @param greaterPane - Pane which contains GridPane puzzle and Pane(contains symbol's image).
	 */
	public GreaterThanLabel(Pane greaterPane) {
		mode = Mode.getInstance();
		this.greaterPane = greaterPane;
	}
	
	/**
	 * Generate all symbols into pane.
	 * */
	public void run() {
		int size = mode.getSize();
		int computeSize = size * size * size * (size - 1);
		List<ImageView> horizontal = new ArrayList<>();
		List<ImageView> vertical = new ArrayList<>();

		horizontal.addAll(horizontalSymbols(computeSize, size));
		vertical.addAll(verticalSymbols(computeSize, size));

		for (int cursor = 0; cursor < horizontal.size(); cursor++) {
			greaterPane.getChildren().add(horizontal.get(cursor));
			greaterPane.getChildren().add(vertical.get(cursor));
		}
	}

	/**
	 * Set the Image for each line(only horizontal line) with fixed image depends on
	 * adjacent cells.
	 * 
	 * @param computeSize - number of image for horizontal point(54 for 3x3 board).
	 * @param size - number of box in one grid.
	 * @return All image symbols(< or >) with fixed translate and size.
	 */
	public List<ImageView> horizontalSymbols(int computeSize, int size) {
		//list which contains Image with no pictures.
		List<ImageView> images = new ArrayList<ImageView>();
		images.addAll(createLabel(computeSize));
		//the set of all numbers in the puzzle.
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		//base's size of box in grid.
		int base = mode.getBase();
		//image's size to adjust the size of symbols.
		int imageSize = base / size;
		//number of image in list.
		int count = 0;

		for (int rowTimes = 0; rowTimes < size; rowTimes++) {
			for (int columnTimes = 0; columnTimes < size; columnTimes++) {
				for (int vertical = 0; vertical < size; vertical++) {
					for (int times = 0; times < size - 1; times++) {
						int left = puzzle.get(size * rowTimes + vertical).getList().get(size * columnTimes + times)
								.getNumber();
						int right = puzzle.get(size * rowTimes + vertical).getList().get(size * columnTimes + times + 1)
								.getNumber();
						//compare numbers in two cells.
						if (left < right) {
							images.get(count).setImage(vLeft);
						} else {
							images.get(count).setImage(vRight);
						}
						//set size  of image.
						images.get(count).setFitWidth(imageSize);
						images.get(count).setFitHeight(imageSize);
						//set position of image.
						images.get(count).setTranslateX(base + times * base + vertical * base * size - imageSize / TWO);
						images.get(count).setTranslateY(base / TWO + (base * columnTimes) + rowTimes * base * size - imageSize / TWO);
						count++;
					}
				}
			}
		}
		return images;
	}

	/**
	 * Set the Image for each line(only vertical line) with fixed image depends on
	 * adjacent cells.
	 * 
	 * @param computeSize - number of image for horizontal point(54 for 3x3 board).
	 * @param size - number of box in one grid.
	 * @return All image symbols(^ or v) with fixed translate and size.
	 */
	public List<ImageView> verticalSymbols(int computeSize, int size) {
		//list which contains Image with no pictures.
		List<ImageView> images = new ArrayList<ImageView>();
		images.addAll(createLabel(computeSize));
		//the set of all numbers in the puzzle.
		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		//base's size of box in grid.
		int base = mode.getBase();
		//image's size to adjust the size of symbols.
		int imageSize = base / size;
		//number of image in list.
		int count = 0;

		for (int rowTimes = 0; rowTimes < size; rowTimes++) {
			for (int columnTimes = 0; columnTimes < size; columnTimes++) {
				for (int times = 0; times < size - 1; times++) {
					for (int vertical = 0; vertical < size; vertical++) {
						int up = puzzle.get(size * rowTimes + columnTimes).getList().get(vertical + times * size).getNumber();
						int down = puzzle.get(size * rowTimes + columnTimes).getList().get(vertical + times * size + size).getNumber();
						//compare numbers in two cells.
						if (up < down) {
							images.get(count).setImage(vUp);
						} else {
							images.get(count).setImage(vDown);
						}
						//set size  of image.
						images.get(count).setFitWidth(imageSize);
						images.get(count).setFitHeight(imageSize);
						//set position of image.
						images.get(count).setTranslateX(base / TWO + base * vertical + base * size * columnTimes - imageSize / TWO);
						images.get(count).setTranslateY(base + base * times + base * size * rowTimes - imageSize / TWO);
						count++;
					}
				}
			}
		}
		return images;
	}

	/**
	 * Create ImageView with no image by fix size that list can contains.
	 * 
	 * @return list of ImageView with no image by fix size that list can contains.
	 * */
	public List<ImageView> createLabel(int size) {
		List<ImageView> images = new ArrayList<ImageView>();
		for (int times = 0; times < size; times++) {
			images.add(new ImageView());
		}
		return images;
	}
}