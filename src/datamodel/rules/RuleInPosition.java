package datamodel.rules;

import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class RuleInPosition extends AbstractRule {

	private List<LineBlock> lineblocks;
	private List<Integer> positions;
	
	public RuleInPosition(List<LineBlock> lineblocks, List<Integer> positions) {
		
		super();
		this.lineblocks = lineblocks;
		this.positions = positions;
	}
	
	public boolean isValid(LineBlock lineblock)
	{	
		if( this.positions.contains( lineblock.getPosition() ))
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		String nums = positions.toString();
		
		return " IN_POS " + nums;
	}

}