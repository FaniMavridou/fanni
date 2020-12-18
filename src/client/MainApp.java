package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class MainApp extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainAPPfxml.fxml"));
		Parent root = (Parent) loader.load();

		//secondController sec = loader.getController();
		//Parent root = FXMLLoader.load(getClass().getResource("MainAPPfxml.fxml"));
		//root.setController(this);
		Scene scene = new Scene(root);
		arg0.setTitle("Application");
		arg0.setScene(scene);
		arg0.show();
		
	}
}
