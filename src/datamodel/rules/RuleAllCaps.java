package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleAllCaps extends AbstractRule {

	public RuleAllCaps() {
		
		super();
	}

	public boolean isValid(LineBlock lineblock)
	{	
		
		for (String line : lineblock.getLines())
		{
			if ( !line.equals(line.toUpperCase()))
			{
				return false;
			}
		}
		return true;
	}
	
	public String toString()
	{
		return " ALL_CAPS";
	}
}
