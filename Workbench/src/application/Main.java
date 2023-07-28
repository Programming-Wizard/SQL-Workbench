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
			FXMLLoader LoginScreen = new FXMLLoader(getClass().getResource("/Login.fxml"));
			Parent root = LoginScreen.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("The Basic SQL Queries Executor");

			ControllerForLogin loginController = LoginScreen.getController();
			loginController.loginBtn.setOnMouseClicked(event -> {
				if (loginController.loginRequest()) 
				{
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
