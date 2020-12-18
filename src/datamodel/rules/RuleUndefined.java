package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleUndefined extends AbstractRule {

	public RuleUndefined() {
		
		super();
	}
	
	public boolean isValid(LineBlock paragraph)
	{	
		return false;
	}

	public String toString()
	{
		return " UNDEFINED";
	}
}
