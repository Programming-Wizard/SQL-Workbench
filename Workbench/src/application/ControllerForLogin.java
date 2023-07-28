package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	private Media media;
	private MediaPlayer mediaplayer;
	//	public boolean loginRequested = false;
	private static String usernameData;
	private static String passwordData;
	private Timeline hoverAnimation;
	private Timeline notHoveredAnimation;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
	private void createHoverAnimation() {
		hoverAnimation = new Timeline();
		hoverAnimation.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(loginBtn.translateYProperty(), 0)),
				new KeyFrame(Duration.millis(50), new KeyValue(loginBtn.translateYProperty(), -5))
				);
	}

	private void createNotHoveredAnimation() {
		notHoveredAnimation = new Timeline();
		notHoveredAnimation.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(loginBtn.translateYProperty(), 0)),
				new KeyFrame(Duration.millis(50), new KeyValue(loginBtn.translateYProperty(), 5))
				);
	}

	public void onMouseEntered() {
		loginBtn.setStyle("-fx-background-color: rgba(225,174,202,0.1); ");
		hoverAnimation.play();
	}

	public void onMouseExited() {
		loginBtn.setStyle("-fx-background-color: black; ");
		notHoveredAnimation.play();
	}	
	public boolean loginRequest()
	{
		this.usernameData = username.getText();
		this.passwordData = password.getText();
		System.out.println("username : " + usernameData + "\npassword : " + passwordData);
		return true;
	}

	public static String getUsername()
	{
		return usernameData;
	}
	public static String getPassword()
	{
		return passwordData;
	}


}
