package test;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import client.FirstController;


public class openButtonTest {

	private static FirstController view;
	
	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	/*@BeforeClass
	public static void initialize() {
		view = new FirstController();
		
		
		//view.setPath("C:\\Users\\user\\Desktop\\atlantis.txt");
		view.HandleButtonAction(null);
	}*/
	
	@Test
	public void test() throws Exception  {
		
		view = new FirstController();
		view.initializeButtonForTest();
		view.setType("RAW");
		view.HandleButtonAction(null);
		//assertEquals("your path",view.getPath()); //auto epeidh allazei pantou(to path) mporeis na mhn to elegxeis
		assertEquals("atlantis",view.getName());
		
	}

}