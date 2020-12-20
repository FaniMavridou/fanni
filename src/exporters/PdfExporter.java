package exporters;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;

public class PdfExporter {

	private Document document;
	private String outputFileName;
	
	public PdfExporter(Document document, String outputFileName)
	{
		this.document = document;
		this.outputFileName = outputFileName;
	}
	
	public int export()
	{
		try {
			com.itextpdf.text.Document itextDocument = new com.itextpdf.text.Document();
			PdfWriter.getInstance(itextDocument, new FileOutputStream(this.outputFileName));
			Paragraph paragraph;
			Chunk chunk;
			Font font;
			int paragraphsNumber = 0;
			
			itextDocument.open();
			
			for(LineBlock lineblock : this.document.getLineblocks())
			{
				paragraph = new Paragraph();
				
				font = determineFont(lineblock);
				if (font == null)
				{
					continue;
				}
				
				for(String line : lineblock.getLines())
				{
					chunk = new Chunk(line + " ", font);
					paragraph.add(chunk);
				}
				chunk = new Chunk("\n\n");
				paragraph.add(chunk);
				itextDocument.add(paragraph);
				paragraphsNumber++;
			}
			itextDocument.close();
			return paragraphsNumber;
			
		} catch (DocumentException dex)
		{
			dex.printStackTrace();
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
		return 0;
	}
	
private Font determineFont(LineBlock lineblock)
{
	Font font = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);;
	
	switch( lineblock.getStyle() )
	{
		case OMITTED: 
			return null;
			
		case H1 : 
			font.setSize(24); // Could be 32 for H1
			return font;
			
		case H2 : 
			font.setSize(18); // And 24 for H2
			return font;
		// default	
		case NORMAL:
	}
	
	switch( lineblock.getFormat() )
	{
		case BOLD: 
			font.setStyle(1);
			break;
			
		case ITALICS :
			font.setStyle(2);
			break;
		// default
		case REGULAR :
			//font.setStyle(0);
	}
	
	return font;
}
}
