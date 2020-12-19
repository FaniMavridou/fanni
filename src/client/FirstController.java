package client;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import engine.Engine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FirstController implements Initializable
{

	private String path;
	//private String outfile;
	private String name;
	private String type;
	private Engine engine;
	//private String namee;
	private List<String> stats;
	
	@FXML
	private Button btnShowStatistics;
	//@FXML
	//private Button btnPDF;
	//@FXML
	//private Button btnMD;
	@FXML
	private Button btnOpen;
	@FXML
	private Button btnNext;
	@FXML 
	private CheckBox checkAnnotated;
	@FXML 
	private CheckBox checkRaw;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnOpen.setDisable(true);
		btnNext.setDisable(true);
		btnShowStatistics.setDisable(true);
		//btnPDF.setDisable(true);
		//btnMD.setDisable(true);
	}
	
	//openButton
	@FXML
	public void HandleButtonAction(ActionEvent event)
	{
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(null);
		if (file != null) {
			path = file.getAbsolutePath();
			String[] parts = path.split("\\\\");
			String x = parts[parts.length-1];
			String[] y = x.split("\\.");
			name = y[0];
			
			System.out.println(file.getAbsolutePath());
			System.out.println(type);
			System.out.println(name);
			
			if(name!=null  || path!=null || type!=null) {
				btnNext.setDisable(false);
				btnShowStatistics.setDisable(false);
			}
			engine = new Engine(path, type, name);
		    stats = engine.reportWithStats();
		}
		else {
			System.out.println("file is not valid");
		}
	}

	//checkbox annotated
	@FXML 
	private void checkAnnotatedBox()
	{
		if(checkAnnotated.isSelected()) {
			checkRaw.setSelected(false);
			btnOpen.setDisable(false);
			type = "ANNOTATED";	
		}
		else {
			btnOpen.setDisable(true);
		}
	}
	
	//checkbox raw
	@FXML 
	private void checkRawBox()
	{
		if(checkRaw.isSelected())
		{
			checkAnnotated.setSelected(false);	
			btnOpen.setDisable(false);
			type = "RAW";
		}
		else {
			btnOpen.setDisable(true);
		}
	}
	
	//show statistics
	public void changeScreen(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("stats.fxml"));
		
		Parent secondScreen = (Parent) loader.load();
		Scene secondScene = new Scene(secondScreen);
		
        SecondController secondController = loader.getController();  //Get controller
        secondController.getStats(stats);  //Pass data 
		
		Stage s = new Stage();
		s.setTitle( name );
		s.setScene(secondScene);
		s.show();
	}
	
	//create new rules.
	public void rulesButton(ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("rulesScreen.fxml"));
		Parent rulesScreen = (Parent) loader.load();
		Scene rulesScene = new Scene(rulesScreen);
		
        RulesController rulesController = loader.getController();//Get controller 
        rulesController.getEngine(engine); //Pass data you.
        rulesController.getName(name);
        
		Stage s = new Stage();
		s.setTitle("Set Rules");
		s.setScene(rulesScene);
		s.show();
		
		//btnPDF.setDisable(false);
		//btnMD.setDisable(false);
	}
	
	/*
	//EXPORT METHODS
	public void exportPDF()
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save as PDF");
		chooser.setInitialFileName( name );
		File file = chooser.showSaveDialog(null);
		
		outfile = file.getAbsolutePath();
		namee = outfile + ".pdf";
		engine.exportPdf(namee);	
	}
	
	public void exportMarkDown()
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save as Markdown");
		chooser.setInitialFileName( name );
		File file = chooser.showSaveDialog(null);
		
		outfile = file.getAbsolutePath();
		namee = outfile + ".md";
		engine.exportMarkDown(namee);
	}
	*/
}
