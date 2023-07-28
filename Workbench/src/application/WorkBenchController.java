package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WorkBenchController implements Initializable {
	@FXML
	private VBox Linecontainer;
	@FXML
	private TextArea WorkBench;
	@FXML
	private ScrollPane scrollPane;

	private int lineCount = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Linecontainer.getChildren().add(new Text(" 1"));

		WorkBench.setOnKeyPressed(event -> {
			if (event.isControlDown() && (event.getCode() == KeyCode.ENTER)) {

				int CursorPosition = WorkBench.getCaretPosition();
				System.out.println(CursorPosition);

				WorkBench.replaceSelection("\n");

				WorkBench.positionCaret(CursorPosition + 1);

				lineCount++;
				Linecontainer.getChildren().add(new Text(" " + String.valueOf(lineCount)));
			} 
			else if (event.getCode() == KeyCode.ENTER) 
			{
				lineCount++;
				Linecontainer.getChildren().add(new Text(" " + String.valueOf(lineCount)));
			}

		});

	}

	public void workbenchdata() 
	{
		String Data = WorkBench.getText();
		String username = ControllerForLogin.getUsername();
		String password = ControllerForLogin.getPassword();
		String url = "jdbc:mysql://localhost:3306/my_database";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();
			String sqlQuery = Data;
			//			try {
			//			int resultSet = stmt.executeUpdate(sqlQuery);
			ResultSet resultSet = stmt.executeQuery(sqlQuery);
			System.out.println("query executed succesfully");
			//			}
			//			catch (Exception e) {
			//				System.out.println(e);
			//			}

			while (resultSet.next()) 
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String email = resultSet.getString("email");
				System.out.println("id : " + id + ", name : " + name + ", age : " + age + ", email" + email);
			}
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
