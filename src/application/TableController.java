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
 * TableController for events and initializing components with 2 boards(3x3 and 4x4).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class TableController {
	
	/**
	 * Node used for painting images on 'Sudoku' board button loaded with Image
	 * class.
	 */
	@FXML
	ImageView three;
	/**
	 * Node used for painting images on 'Sudoku' board button loaded with Image
	 * class.
	 */
	@FXML
	ImageView four;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		effectImage(three);
		effectImage(four);
	}

	/**
	 * Link with "StageMenu.fxml" to switch scene to other scene for setting the
	 * size of puzzle to 3x3 board.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleThree(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StageMenu.fxml"));
			Parent pane = loader.load();
			StageController setTable = loader.getController();
			setTable.setRandomNumber(3);
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
	 * Link with "StageMenu.fxml" to switch scene to other scene for setting the
	 * size of puzzle to 4x4 board.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleFour(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StageMenu.fxml"));
			Parent pane = loader.load();
			StageController setTable = loader.getController();
			setTable.setRandomNumber(4);
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
