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
 * MainController for events and initializing components with start, about, and
 * scoreboard.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class MainController {
	/** Node used for painting images on 'start' button loaded with Image class. */
	@FXML
	ImageView start;
	/** Node used for painting images on 'about' button loaded with Image class. */
	@FXML
	ImageView about;
	/** Node used for painting images on 'scoreboard' button loaded with Image class. */
	@FXML
	ImageView scoreborad;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		effectImage(start);
		effectImage(about);
		effectImage(scoreborad);
	}

	/**
	 * Link with "NameMenu.fxml" to switch scene to other scene.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked and
	 * the event is delivered to it through capturing and bubbling phase
	 * described at EventDispatcher.
	 */
	public void handleStart(MouseEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("NameMenu.fxml"));
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
	 * Link with "Help.fxml" to switch scene to other scene.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked and
	 * the event is delivered to it through capturing and bubbling phase
	 * described at EventDispatcher.
	 */
	public void handleAbout(MouseEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("Help.fxml"));
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
	 * Link with "SelectScoreboard.fxml" to switch scene to other scene.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is picked and
	 * the event is delivered to it through capturing and bubbling phase
	 * described at EventDispatcher.
	 */
	public void handleScoreboard(MouseEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("SelectScoreboard.fxml"));
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
