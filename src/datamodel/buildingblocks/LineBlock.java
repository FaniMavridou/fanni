package datamodel.buildingblocks;

import java.util.List;

public class LineBlock {

	private FormatEnum format;
	private StyleEnum style;
	
	private List<String> lines;
	private int position;
	
	
	public LineBlock(List<String> lines, int position) {
		
		this.lines = lines;
		this.position = position;
	}
	
	public void setFormat(FormatEnum format) {
		
		this.format = format;
	}

	public void setStyle(StyleEnum style) {
		
		this.style = style;
	}
	
	public FormatEnum getFormat()
	{
		return this.format;
	}

	public StyleEnum getStyle()
	{
		return this.style;
	}
	
	public List<String> getLines()
	{
		return lines;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public int getNumWords()
	{
		int totalWords = 0;
		for (String line : lines)
		{
			String[] words = line.split(" ");
			totalWords = totalWords + words.length;
		}
		return totalWords;
	}
	
	public String getStatsAsString()
	{
		String str = "Lines: " + lines.size() + "\t\t" + "Words: " + getNumWords()  + "\n";
		
		return str;
	}
	
	
}