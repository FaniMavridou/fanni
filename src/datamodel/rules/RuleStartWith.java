package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleStartWith extends AbstractRule {

	private String prefix;
	
	public RuleStartWith(String prefix) {
		
		super();
		this.prefix = prefix;
	}
	
	public boolean isValid(LineBlock lineblock) {
		
		String line = lineblock.getLines().get(0);
		
		if ( line.startsWith(this.prefix) ) {
			return true;
		}
		return false;
	}

	public String toString()
	{
		return " STARTS_WITH (" + prefix + ")";
	}
}
