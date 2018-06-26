package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.RandomNumber;
import strategy.Mode;

/**
 * EndGameController for events and initializing components with play again,
 * main menu, and scoreboard buttons.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class EndGameController {
	/** Node used for painting images on 'Play Again' button with Image class. */
	@FXML
	ImageView playAgain;
	/** Node used for painting images on 'Main Menu' button with Image class. */
	@FXML
	ImageView mainMenu;
	/** Node used for painting images on 'Score Board' button with Image class. */
	@FXML
	ImageView scoreboard;
	/**
	 * Node used for painting images on 'Time' button which is user's use to solve
	 * puzzle with Image class.
	 */
	@FXML
	Label yourTime;
	/** Declare ScoreManager to record a time. */
	private ScoreManager manage;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	public void initialize() {
		manage = ScoreManager.getInstance();
		manage.recordScore(NameController.getName(), GridController.getTime());
		yourTime.setText("Time " + GridController.getTime());

		effectImage(playAgain);
		effectImage(mainMenu);
		effectImage(scoreboard);
	}

	/**
	 * Link with "GridUI.fxml" to switch scene to other scene for starting game and
	 * play again with new.
	 *
	 * @param event - when mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handlePlayAgain(MouseEvent event) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("GridUI.fxml"));
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
	 * Link with "MainMenu.fxml" to switch scene to other scene for back to the beginning of application.
	 *
	 * @param event - when mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleMainMenu(MouseEvent event) {
		Mode.getInstance().clearPuzzle();
		RandomNumber.setRandomNumber(RandomNumber.getInstance().getSize());
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
	 * Link with "SelectScoreboard.fxml" to switch scene to other scene with selection score board scene.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked and
	 * the event is delivered to it through capturing and bubbling phase
	 * described at EventDispatcher.
	 */
	public void handleScoreboard(MouseEvent event) {
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
	 * Makes the picture zoom in/out when mouse cursor enters on the button.
	 * 
	 * @param image
	 *            - is a Node used for painting images loaded with Image class.
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
