package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class is the class to start the application or ready to begin
 * running.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class Main extends Application {

	/**
	 * Configure and start the application with music.
	 * 
	 * @param args not used
	 */
	public static void main(String[] arg) {
		launch(arg);
	}

	/**
	 * The start method is called after the init method has returned, and after the
	 * system is ready for the application to begin running.
	 * 
	 * @param stage - the stage for this application which the application scene can
	 *            be set.
	 * @throws Checked
	 *             exceptions need to be declared in a method or constructor's
	 *             throws clause if they can be thrown by the execution of the
	 *             method or constructor and propagate outside the method or
	 *             constructor boundary.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(pane);
			stage.setResizable(false);
			stage.setTitle("Sudoku Game");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}