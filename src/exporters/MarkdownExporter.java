package exporters;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;

public class MarkdownExporter {

	private Document document;
	private String outputFileName;
	
	public MarkdownExporter(Document document, String outputFileName)
	{
		this.document = document;
		this.outputFileName = outputFileName;
	}
	
	public int export()
	{
		try {
			File file = new File(this.outputFileName);
			FileWriter filerWriter = new FileWriter(file);
			PrintWriter writer = new PrintWriter(filerWriter);
			String paragraph;
			int paragraphsNumber = 0;
			
			for(LineBlock lineblock : document.getLineblocks())
			{
				paragraph = setupLines(lineblock);
				
				if(paragraph == null) { 
					continue; 
				}
				writer.write(paragraph);
				paragraphsNumber++;
			}
			writer.close();
			return paragraphsNumber;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private String setupLines(LineBlock lineblock)
	{
		String paragraph = String.join(" ", lineblock.getLines());
		
		
		switch( lineblock.getStyle() )
		{
			case OMITTED:
				return null;
				
			case H1 : 
				paragraph = "#" + paragraph + "\n\n";
				return paragraph;
				
			case H2 : 
				paragraph = "##" + paragraph + "\n\n";
				return paragraph;
			// default	
			case NORMAL:
		}
		
		switch( lineblock.getFormat() )
		{
			case BOLD: 
				paragraph = "**" + paragraph + "**";
				break;
				
			case ITALICS :
				paragraph = "*" + paragraph + "*";
				break;
			// default
			case REGULAR :
		}
		paragraph = paragraph + "\n\n";
		
		return paragraph;
	}
}
