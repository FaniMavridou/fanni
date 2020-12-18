package test;

import static org.junit.Assert.*;

import org.junit.Test;

import client.FirstController;

public class openButtonTest {

	private static FirstController view;
	
	
	static void initialize() {
		view = new FirstController();
		
		
		view.setPath("C:\\Users\\user\\Desktop\\atlantis.txt");
		view.HandleButtonAction(null);
	}
	
	@Test
	public void test() throws InterruptedException  {
		fail("Not yet implemented");
		
		
		
		
	}

}
