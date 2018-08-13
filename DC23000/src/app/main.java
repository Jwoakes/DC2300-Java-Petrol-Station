package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;


public class main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		try {
			SplitPane root = (SplitPane) FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("UI.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception a) {
			a.printStackTrace();
		}
	}
	
//	private static void launch(String[] args) {	

}
