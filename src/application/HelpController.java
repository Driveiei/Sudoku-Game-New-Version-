package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * HelpController for events and initializing components with mode of descript
 * selection buttons for every mode(Basic Sudoku and Greater than Sudoku).
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class HelpController {
	/** Node used for painting images on 'Basic Sudoku' board button description  board button loaded with Image class.*/
	@FXML
	ImageView sudoku;
	/** Node used for painting images on 'Greater than Sudoku' board button description loaded with Image class.*/
	@FXML
	ImageView greater;
	/** Node used for painting images on 'Back' button loaded with Image class.*/
	@FXML
	ImageView back;
	/**Pane for containing description of 'Basic Sudoku' text*/
	@FXML
	Pane data1;
	/**Pane for containing description of 'Greater than Sudoku' text*/
	@FXML
	Pane data2;

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	public void initialize() {
		effectImage(sudoku);
		effectImage(greater);
		effectImage(back);
	}
	
	/**
	 * Select 'Basic Sudoku' pane to appears the description about it.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleSudoku(MouseEvent event) {
		data1.setVisible(true);
		sudoku.setOnMouseExited(e->{
			data1.setVisible(false);
		});
	}

	/**
	 * Select 'Greater than Sudoku' pane to appears the description about it.
	 * 
	 * @param event - When mouse event occurs, the top-most node under cursor is
	 *            picked and the event is delivered to it through capturing and
	 *            bubbling phase described at EventDispatcher.
	 */
	public void handleGreater(MouseEvent event) {
		data2.setVisible(true);
		greater.setOnMouseExited(e ->{
			data2.setVisible(false);
		});
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
			}catch(IOException e) {
				System.err.println(e.getMessage());
			}
	}
	
	/**
	 * Makes the picture zoom in/out when mouse cursor enters on the button.
	 * 
	 * @param image - is a Node used for painting images loaded with Image class. 
	 */
	public void effectImage(ImageView image) {
		image.setOnMouseEntered(in ->{
			image.setFitWidth(225);
			image.setFitHeight(175);
			image.setOnMouseReleased(out ->{
				image.setFitWidth(200);
				image.setFitHeight(150);
			});
		});
	}
}
