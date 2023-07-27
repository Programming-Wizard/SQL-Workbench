package application;

import java.net.URL;
import java.security.PublicKey;
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

        WorkBench.setOnKeyPressed(event -> 
        {
        	if (event.isControlDown() && (event.getCode() == KeyCode.ENTER)) {
        		
        		int CursorPosition = WorkBench.getCaretPosition();
        		
        		WorkBench.replaceSelection("\n");
        		
        		WorkBench.positionCaret(CursorPosition + 1);
        		
                lineCount++;
                Linecontainer.getChildren().add(new Text(" "+String.valueOf(lineCount)));
        	}
        	else if (event.getCode() == KeyCode.ENTER) 
        	{
                lineCount++;
                Linecontainer.getChildren().add(new Text(" " + String.valueOf(lineCount)));
            }
        	
        });
        
        
    }
	
	public void getworkbenchtext()
	{
		System.out.println(WorkBench.getText());
	}
}

