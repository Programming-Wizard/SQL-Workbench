package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			adding a login scene to the stage
			FXMLLoader LoginScreen = new FXMLLoader(getClass().getResource("/Login.fxml"));
			Parent root = LoginScreen.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("The Basic SQL Queries Executor");
//			adding login screen's controller to access it methods
			ControllerForLogin loginController = LoginScreen.getController();
//			adding event listener to its button specifically to avoid any other clicks from being captured
			loginController.loginBtn.setOnMouseClicked(event -> {
				if (loginController.loginRequest()) 
				{
//					setting and new scene if login was successful
					FXMLLoader workBenchScreen = new FXMLLoader(getClass().getResource("/WorkBenchBuild.fxml"));
					try {
						Parent newRoot = workBenchScreen.load();
						Scene workBench = new Scene(newRoot);
						primaryStage.setScene(workBench);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				} 
			});

			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}