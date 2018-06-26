package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * SelectScoreController for events and initializing components with score selection buttons for
 * every mode(Basic Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class SelectScoreController {

	/** Node used for painting images on '3x3' board button loaded with Image class.*/
	@FXML
	ImageView buttonThree;
	/**Node used for painting images on 'easy' mode button loaded with Image class. */
	@FXML
	ImageView button3Easy;
	/** Node used for painting images on 'hard' mode board button loaded with Image class.*/
	@FXML
	ImageView button3Hard;
	/**Node used for painting images on '4x4' board button loaded with Image class.*/
	@FXML
	ImageView buttonFour;
	/** Node used for painting images on 'easy' mode button loaded with Image class.*/
	@FXML
	ImageView button4Easy;
	/**Node used for painting images on 'hard' mode button loaded with Image class.*/
	@FXML
	ImageView button4Hard;
	/** Node used for painting images on 'Greater than Sudoku' board button loaded with Image class.*/
	@FXML
	ImageView buttonGreater;
	/**Node used for painting images on 'Back' button loaded with Image class.*/
	@FXML
	ImageView back;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	public void initialize() {
		effectImage(buttonThree);
		effectImage(button3Easy);
		effectImage(button3Hard);
		effectImage(buttonFour);
		effectImage(button4Easy);
		effectImage(button4Hard);
		effectImage(buttonGreater);
		effectImage(back);
	}

	/**
	 * Select size of board(3x3) to appears two modes(easy and hard).
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreThree(MouseEvent event) {
		buttonThree.setVisible(false);
		button3Easy.setVisible(true);
		button3Hard.setVisible(true);
	}

	/**
	 * Select easy mode to appears five-top scores player who can solve the puzzle on 3x3 board
	 * less time in this mode and switch scene to 'Scoreboard.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreThreeEasy(MouseEvent event) {
		ScoreManager.setSymbol("+");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	/**
	 * Select hard mode to appears five-top scores player who can solve the puzzle on 3x3 board
	 * less time in this mode and switch scene to 'Scoreboard.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreThreeHard(MouseEvent event) {
		ScoreManager.setSymbol("@");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	/**
	 * Select size of board(4x4) to appears two modes(easy and hard).
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreFour(MouseEvent event) {
		buttonFour.setVisible(false);
		button4Easy.setVisible(true);
		button4Hard.setVisible(true);
	}

	/**
	 * Select easy mode to appears five-top scores player who can solve the puzzle on 4x4 board
	 * less time in this mode and switch scene to 'Scoreboard.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreFourEasy(MouseEvent event) {
		ScoreManager.setSymbol("&");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	/**
	 * Select hard mode to appears five-top scores player who can solve the puzzle on 4x4 board
	 * less time in this mode and switch scene to 'Scoreboard.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreFourHard(MouseEvent event) {
		ScoreManager.setSymbol("*");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	/**
	 * Select 'Greater than Sudoku' mode to appears five-top scores player who can solve the puzzle on 4x4 board
	 * less time in this mode and switch scene to 'Scoreboard.fxml'.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleScoreGreaterThan(MouseEvent event) {
		ScoreManager.setSymbol("?");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
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

	/**
	 * Link with "MainMenu.fxml" to switch scene to other scene for back to MainMenu game.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleBack(MouseEvent event) {
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
	 * Makes the picture zoom in/out when mouse cursor enters on the button.
	 * 
	 * @param image - is a Node used for painting images loaded with Image class. 
	 */
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in -> {
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseExited(out -> {
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}
}
