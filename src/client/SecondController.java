package client;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class SecondController implements Initializable{

	@FXML private Label text;
	@FXML private ScrollPane panel; 
	
	public void getStats(List<String> stats)
	{
		String t = "";
		for(String string : stats)
		{
			System.out.print(string);
			t += string + "\n" ;
			//System.out.print(t);

		}
		text.setText(t);
		//panel.setContent(text);
	}
	
	//mipws na to bgaloume?
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}