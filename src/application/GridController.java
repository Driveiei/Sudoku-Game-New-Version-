package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.BoxManager;
import logic.GridManager;
import logic.RandomNumber;
import strategy.Mode;

/**
 * GridController for events and initializing components that player can play
 * game.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class GridController {
	/** This BorderPane is for adding table of sudoku */
	@FXML
	BorderPane borderPane;
	/**
	 * This Label link to task for update time along with GridController is running
	 */
	@FXML
	Label timer;
	/** ImageView for show hint when click */
	@FXML
	ImageView assist;
	/** ImageView for clear number when click */
	@FXML
	ImageView clear;
	/** ImageView for change scene from user when click */
	@FXML
	ImageView done;
	/** ImageView for restart this scene when click */
	@FXML
	ImageView restart;
	/** ImageView for return to main menu when click */
	@FXML
	ImageView mainMenu;

	/** Save final time of player */
	private static String time;
	/** worker to start threads */
	private TimeTask worker;

	/** Declare class */
	private Grid griddy;
	private Mode mode;
	private SupportGrid support;

	/** Declare attributes */
	private int size;
	private int realSize;
	private int count;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		runTime();
		effectImage(assist);
		effectImage(done);
		effectImage(clear);
		effectImage(restart);
		effectImage(mainMenu);
		mode = Mode.getInstance();
		mode.setPuzzle();

		count = 0;
		size = mode.getSize();
		realSize = size * size;
		support = new SupportGrid(size);

		griddy = new Grid(borderPane);
		griddy.run();
	}

	/**
	 * Clear every number in table that has from player(if box has been locked by
	 * player handleClear will ignore that box).
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked
	 *            and the event is delivered to it through capturing and bubbling
	 *            phase described at EventDispatcher.
	 */
	public void handleClear(MouseEvent event) {

		List<GridManager> puzzle = new ArrayList<GridManager>();
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = support.adaptGrid(selectBox, selectGrid);
				int box = support.adaptBox(selectBox, selectGrid);
				BoxManager oneBox = puzzle.get(grid).getList().get(box);
				if (!oneBox.getLock())
					griddy.getLabel()[selectBox][selectGrid].setText("");
			}
		}
	}

	/**
	 * Show hint for player. It's randomly show up(if box has been locked by player
	 * handleHint will ignore that box). this method can't click more than size.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked
	 *            and the event is delivered to it through capturing and bubbling
	 *            phase described at EventDispatcher.
	 */
	public void handleHint(MouseEvent event) {
		if (count < realSize) {
			worker.setTime();
			List<BoxManager> puzzle = new ArrayList<BoxManager>();
			puzzle.addAll(support.getSpaceBoxList());
			if (puzzle.size() == 0)
				return;
			int cursor = support.randomCursor(puzzle.size());
			BoxManager targetBox = puzzle.get(cursor);
			String target = Integer.toString(targetBox.getNumber());

			int numberOfGrid = -1;
			int numberOfBox = 0;
			while (++numberOfGrid < realSize) {
				numberOfBox = mode.getPuzzle().get(numberOfGrid).getList().indexOf(targetBox);
				if (numberOfBox >= 0) {
					break;
				}
			}

			int column = support.scaleToArrayColumn(numberOfGrid, numberOfBox);
			int row = support.scaleToArrayRow(numberOfGrid, numberOfBox);
			griddy.getLabel()[column][row].setText(target);
			griddy.getLabel()[column][row].setStyle("-fx-text-fill: rgb(255, 64, 0);");
			griddy.getLabel()[column][row].setFont(Font.font(null, FontWeight.BLACK, 32));
			mode.getPuzzle().get(numberOfGrid).getList().get(numberOfBox).setLock(true);
			mode.getPuzzle().get(numberOfGrid).getList().get(numberOfBox).setCheck(true);
			griddy.removeButtonSelection(column, row);
			count++;
		}
	}

	/**
	 * When player have success game(numbers in box are correct, and fill all empty
	 * box). This event for switch scene to 'EndGame.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked
	 *            and the event is delivered to it through capturing and bubbling
	 *            phase described at EventDispatcher.
	 */
	public void handleDone(MouseEvent event) {
		List<GridManager> puzzle = new ArrayList<GridManager>();
		boolean check = true;
		puzzle.addAll(mode.getPuzzle());
		for (int selectGrid = 0; selectGrid < realSize; selectGrid++) {
			for (int selectBox = 0; selectBox < realSize; selectBox++) {
				int grid = support.adaptGrid(selectBox, selectGrid);
				int box = support.adaptBox(selectBox, selectGrid);
				BoxManager oneBox = puzzle.get(grid).getList().get(box);
				if (!oneBox.getLock() || !oneBox.getCheck()) {
					String answerText = Integer.toString(oneBox.getNumber());
					if (griddy.getLabel()[selectBox][selectGrid].getText().equals(answerText))
						continue;
					else {
						check = false;
						break;
					}
				}
			}
			if (!check)
				break;
		}
		if (check) {
			time = timer.getText();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
				Parent pane = loader.load();
				Scene scene = new Scene(pane);
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Reset all of this scene(create new table).
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked
	 *            and the event is delivered to it through capturing and bubbling
	 *            phase described at EventDispatcher.
	 */
	public void handleRestart(MouseEvent ac) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Return to main menu. This round isn't save score.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked
	 *            and the event is delivered to it through capturing and bubbling
	 *            phase described at EventDispatcher.
	 */
	public void handleMainMenu(MouseEvent ac) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Get the total time that user used for solve sudoku problem.
	 * 
	 * @return String of time.
	 */
	public static String getTime() {
		return time;
	}

	/**
	 * Makes the picture zoom in/out when mouse cursor enters on the button.
	 * 
	 * @param image - is a Node used for painting images loaded with Image class.
	 */
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in -> {
			image.setFitWidth(175);
			image.setFitHeight(125);
			image.setOnMouseExited(out -> {
				image.setFitWidth(150);
				image.setFitHeight(100);
			});
		});
	}

	/**
	 * Start thread to update time in 'GridUI.fxml'.
	 */
	public void runTime() {
		worker = new TimeTask();
		timer.setText("00 : 00 : 000");
		timer.textProperty().bind(worker.messageProperty());
		new Thread(worker).start();
	}
}