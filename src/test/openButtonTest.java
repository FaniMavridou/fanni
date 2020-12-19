package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import client.FirstController;
import client.MainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class openButtonTest {

	private static FirstController view;
	private static MainApp mainApp;
	//private static Stage classStage = new Stage();
	
	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	/*@BeforeClass
	public static void initialize() {
		view = new FirstController();
		
		
		//view.setPath("C:\\Users\\user\\Desktop\\atlantis.txt");
		view.HandleButtonAction(null);
	}*/
	
	@Test
	public void test() throws Exception  {
		//fail("Not yet implemented");
		view = new FirstController();
		view.initializeButtonForTest();
		view.setType("RAW");
		view.HandleButtonAction(null);
		assertEquals("C:\\Users\\vamva\\Desktop\\atlantis.txt",view.getPath()); //auto epeidh allazei pantou(to path) mporeis na mhn to elegxeis
		assertEquals("atlantis",view.getName());
		
	}

}
