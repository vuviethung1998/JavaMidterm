package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArrayList  extends Application{
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ArrayList.fxml"));
			primaryStage.setTitle("ArrayList Demonstration");
			primaryStage.setScene(new Scene(root));
	        primaryStage.setMinWidth(600);
	        primaryStage.setMinHeight(400);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
