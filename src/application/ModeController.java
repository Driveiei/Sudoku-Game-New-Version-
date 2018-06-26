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
import strategy.GreaterThanStrategy;
import strategy.Mode;

/**
 * ModeController for events and initializing components with two modes(Basic-
 * Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class ModeController {
	
	/** Node used for painting images on 'Sudoku' board button loaded with Image class.*/
	@FXML
	ImageView sudoku;
	/** Node used for painting images on 'Greater than' board button loaded with Image class.*/
	@FXML
	ImageView greater;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		effectImage(sudoku);
		effectImage(greater);
	}

	/**
	 * Link with "TableMenu.fxml" to switch scene to other scene for selecting size
	 * of game.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleBasic(MouseEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("TableMenu.fxml"));
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
	 * Link with "GridUI.fxml" to switch scene to other scene for initialize
	 * 'Greater than' mode with size of 3x3 puzzle.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleGreater(MouseEvent event) {
		RandomNumber.setRandomNumber(3);
		Mode.setMode(new GreaterThanStrategy());
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
			image.setFitWidth(350);
			image.setFitHeight(300);
			image.setOnMouseExited(out -> {
				image.setFitWidth(300);
				image.setFitHeight(200);
			});
		});
	}
}
