package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class ControllerForLogin implements Initializable {
	@FXML
	private MediaView background;
	@FXML
	public Button loginBtn;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label errorLabel;
	@FXML
	private ImageView errorImage;
	private Media media;
	private MediaPlayer mediaplayer;
	// public boolean loginRequested = false;
	private static String usernameData;
	private static String passwordData;
	private Timeline hoverAnimation;
	private Timeline notHoveredAnimation;
	private String errorMessage1 = "\"Invalid password or unable to connect to the database.\"\n"
			+ "\r\n"
			+ "Enable SQL service or "
			+ "Enter correct password.";
	private String errorMessage2 = "Password is a required field";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		errorImage.setVisible(false);
		errorLabel.setVisible(false);
		media = new Media(getClass().getResource("/background2.mp4").toExternalForm());
		mediaplayer = new MediaPlayer(media);
		background.setMediaPlayer(mediaplayer);
		mediaplayer.setAutoPlay(true);
		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
		createHoverAnimation();
		createNotHoveredAnimation();

		loginBtn.setOnMouseEntered(event -> onMouseEntered());
		loginBtn.setOnMouseExited(event -> onMouseExited());

	}

//	timeline for moving the login button up
	private void createHoverAnimation() 
	{
		hoverAnimation = new Timeline();
		hoverAnimation.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(loginBtn.translateYProperty(), 0)),
				new KeyFrame(Duration.millis(50), new KeyValue(loginBtn.translateYProperty(), -5)));
	}

//	timeline for moving the login button down again
	private void createNotHoveredAnimation() 
	{
		notHoveredAnimation = new Timeline();
		notHoveredAnimation.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(loginBtn.translateYProperty(), 0)),
				new KeyFrame(Duration.millis(50), new KeyValue(loginBtn.translateYProperty(), 5)));
	}

//	adding the methods to there appropriate listener
	public void onMouseEntered() 
	{
		loginBtn.setStyle("-fx-background-color: rgba(225,174,202,0.1); ");
		hoverAnimation.play();
	}

	public void onMouseExited() 
	{
		loginBtn.setStyle("-fx-background-color: black; ");
		notHoveredAnimation.play();
	}

//	establishing and checking if there is no problem with making a connection with the server or the user entered password
	public boolean loginRequest() {
		errorImage.setVisible(false);
		errorLabel.setVisible(false);
		this.usernameData = username.getText();
		this.passwordData = password.getText();
		//		this.passwordData = hashPassword(password.getText());
		//		System.out.println("username : " + usernameData + "\npassword : " + passwordData);
		String url = "jdbc:mysql://localhost:3306/my_database";
		if(passwordData.length() > 0)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, usernameData, passwordData);
				con.close();
				return true;
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} catch (SQLException e) 
			{
//				System.out.println("");
				displayingError(errorMessage1);
				return false;
			}
			}
		else
		{
//			System.out.println("The password is required");
			displayingError(errorMessage2);
		}
		return false;
	}

	
	public void displayingError(String Message)
	{
		errorLabel.setVisible(true);
		errorImage.setVisible(true);
		errorLabel.setText(Message);
		errorLabel.setOpacity(1);
		errorImage.setOpacity(1);
		
		Duration showDuration = Duration.seconds(3);
	    Timeline showDurationTimeline = new Timeline(new KeyFrame(showDuration, event -> {
	        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorLabel);
	        fadeOut.setToValue(0);
	        fadeOut.setOnFinished(e -> {
	            errorLabel.setVisible(false);
	        });
	        fadeOut.play();
	    }));
	    showDurationTimeline.stop();
	    showDurationTimeline.play();
	    Duration showDuration2 = Duration.seconds(3);
	    Timeline showDurationTimeline2 = new Timeline(new KeyFrame(showDuration2, event -> {
	    	FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), errorImage);
	    	fadeOut.setToValue(0);
	    	fadeOut.setOnFinished(e -> {
	    		errorLabel.setVisible(false);
	    	});
	    	fadeOut.play();
	    }));
	    showDurationTimeline2.stop();
	    showDurationTimeline2.play();
		
	}
	
//	getting the user defined username and password using getters
	public static String getUsername() 
	{
		return usernameData;
	}

	public static String getPassword() 
	{
		return passwordData;
	}

//	hashing algorithm to hash the user entered password
//		 private String hashPassword(String password) {
//		        try {
//		            MessageDigest md = MessageDigest.getInstance("SHA-256");
//		            byte[] hashBytes = md.digest(password.getBytes());
//		            StringBuilder sb = new StringBuilder();
//		            for (byte b : hashBytes) {
//		                sb.append(String.format("%02x", b));
//		            }
//		            return sb.toString();
//		        } catch (NoSuchAlgorithmException e) {
//		            e.printStackTrace();
//		            return null;
//		        }
//		    }

}