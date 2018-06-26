package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ScoreboardController for events and initializing components with five-top
 * scores for every mode(Basic- Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class ScoreboardController {

	/** Lays out its children in a single vertical column. */
	@FXML
	VBox box;
	/**
	 * Node used for painting images on '3x3' board button loaded with Image class.
	 */
	@FXML
	ImageView mainMenu;

	/** Declare class to get instance. */
	private ScoreManager reader;
	/** Number of players to shown the time on the board. */
	private final int TOP_SCORE = 5;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 * This is a hook to initialize anything your controller or UI needs.
	 * 
	 * @throws FileInputStream, FileOutputStream, and RandomAccessFile 
	 *             constructors when a file
	 *             with the specified pathname does not exist.
	 */
	public void initialize() throws FileNotFoundException {
		mainMenu.setOnMouseEntered(in -> {
			mainMenu.setFitWidth(225);
			mainMenu.setFitHeight(175);
			mainMenu.setOnMouseExited(out -> {
				mainMenu.setFitWidth(200);
				mainMenu.setFitHeight(150);
			});
		});
		showScore();

	}

	/**
	 * Link with "MainMenu.fxml" to switch scene to other scene for back to MainMenu
	 * game.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleMenu(MouseEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Show five-top players sort by time using to solve Sudoku puzzle in each game
	 * to the scene.
	 */
	public void showScore() {
		reader = ScoreManager.getInstance();
		List<Score> save;
		Label score;
		try {
			save = reader.readScore();
			reader.sortTime(save);
			if (save.size() >= TOP_SCORE) {
				for (int i = 0; i < TOP_SCORE; i++) {
					score = new Label();
					score.setText(String.format("%d) %3s", i + 1, save.get(i).toString()));
					// score.setStyle("-fx-padding: 10;");
					score.setStyle("-fx-font: 32px \"Serif\";-fx-padding: 30;");
					box.getChildren().add(score);
					box.setAlignment(Pos.TOP_LEFT);
				}
			} else {
				for (int i = 0; i < save.size(); i++) {
					score = new Label();
					score.setText(String.format("%d) %s", i + 1, save.get(i).toString()));
					// score.setStyle("-fx-padding: 10;");
					score.setStyle("-fx-font: 32px \"Serif\";-fx-padding: 30;");
					box.getChildren().add(score);
					box.setAlignment(Pos.TOP_LEFT);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}

	}
}
