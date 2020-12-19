package client;

import java.io.File;
import engine.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class ExportController
{
	
	private Engine tasia;
	private String namee;
	private String outfile;
	private String name;
	
	@FXML
	private Button btnPDF;
	@FXML
	private Button btnMD;
	
	//EXPORT METHODS
		public void exportPDF()
		{
		
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Save as PDF");
			chooser.setInitialFileName( name );
			File file = chooser.showSaveDialog(null);
			if (file != null) {
				outfile = file.getAbsolutePath();
				namee = outfile + ".pdf";
				tasia.exportPdf(namee);
			}else{
				System.out.println("file is not valid");
			}
		}
		
		
		public void exportMarkDown()
		{
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Save as Markdown");
			chooser.setInitialFileName( name );
			File file = chooser.showSaveDialog(null);
			if (file != null) {
				outfile = file.getAbsolutePath();
				namee = outfile + ".md";
				tasia.exportMarkDown(namee);
			}else{
				System.out.println("file is not valid");
			}
			
		}
		
		
		
		
		
		//get data
		public void getName(String name) {
			this.name = name;
			
		}
		public void getEngine(Engine mpampis) {
			this.tasia = mpampis;
			
		}

	
		
}
