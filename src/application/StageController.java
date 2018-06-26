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
import logic.RandomNumber;
import strategy.EasyStrategy;
import strategy.HardStrategy;
import strategy.Mode;

/**
 * StageController for events and initializing components with two modes(Basic-
 * Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class StageController {

	/** Node used for painting images on 'easy' selection button loaded with Image class.*/
	@FXML
	ImageView easy;
	/** Node used for painting images on 'hard' selection button loaded with Image class.*/
	@FXML
	ImageView hard;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		effectImage(easy);
		effectImage(hard);
	}

	/**
	 * Link with "GridUI.fxml" to switch scene to other scene for starting game and
	 * set game mode to 'easy' mode.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleEasy(MouseEvent event) {
		Mode.setMode(new EasyStrategy());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GridUI.fxml"));
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
	 * Link with "GridUI.fxml" to switch scene to other scene for starting game and
	 * set game mode to 'hard' mode.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleHard(MouseEvent event) {
		Mode.setMode(new HardStrategy());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GridUI.fxml"));
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

	public void setRandomNumber(int table) {
		RandomNumber.setRandomNumber(table);

	}
}
