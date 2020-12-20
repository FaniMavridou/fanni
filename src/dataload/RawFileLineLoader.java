package dataload;

import datamodel.buildingblocks.LineBlock;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RawFileLineLoader {


	/** Takes the path of the document and an empty List<LineBlock> lineblocks ,as parameters
	 * and fills the List with newly created LineBlocks from the raw file.
	 *
	 * @param filePath, the path of the file
	 * @param lineblocks, a List<LineBlock>  which is going to be returned filled later.
	 * @return a List<LineBlock> with all the paragraphs of the file
	 */
	
	public List<LineBlock> load(String filePath, List<LineBlock> lineblocks) {
		
		try {
			File file = new File(filePath);
			Scanner scanner = new Scanner(file).useDelimiter("\r\n\\s*\r\n"); 

			List<String> lines = new ArrayList<>();
			lineblocks = new ArrayList<LineBlock>();
			
			int position = 1;
			while (scanner.hasNext())
			{		 
				String paragraph = scanner.next();
				if(paragraph == "\s") {
					continue;
				}					
				lines = Arrays.asList(paragraph.split("\r\n"));		
				lineblocks.add(new LineBlock(lines, position));		
				position++;				
			}
			scanner.close();
			 
		}  catch (IOException e) {
			  e.printStackTrace();
		} 
		
		return lineblocks;
	}	
}
