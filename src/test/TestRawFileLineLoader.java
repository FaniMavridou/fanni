package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dataload.RawFileLineLoader;

import java.util.ArrayList;
import java.util.List;

import datamodel.buildingblocks.LineBlock;

public class TestRawFileLineLoader {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testLoadHippo() {
		RawFileLineLoader loader = new RawFileLineLoader();
		List<LineBlock> lineblocks = new ArrayList<>();
		lineblocks = loader.load("Resources/SampleDocs/hippocratesOath.txt", lineblocks);
		assertEquals(17,lineblocks.size());
		List<LineBlock> hippoLineBlocks = setupHippo();
		Boolean localComparison = compareLoadedParagraphs(lineblocks, hippoLineBlocks);
		assertEquals(true, localComparison);
	}
	
	@Test
	public final void testLoadEcon() {
		RawFileLineLoader loader = new RawFileLineLoader();
		List<LineBlock> lineblocks = new ArrayList<>();
		lineblocks = loader.load("Resources/SampleDocs/economy_mt.txt", lineblocks);
		assertEquals(19,lineblocks.size());
	}
	
	
	// Test only the first 6 lineblocks, cause it s too much work otherwise
	private List<LineBlock> setupHippo() {
		List<LineBlock> lineblocks = new ArrayList<LineBlock>();
		List<String> line1 = new ArrayList<>();
		line1.add("Internet Wiretap Edition of"); lineblocks.add(new LineBlock(line1, 1));	
		List<String> line2 = new ArrayList<>();
		line2.add("OATH AND LAW OF HIPPOCRATES"); lineblocks.add(new LineBlock(line2, 2));
		List<String> line3 = new ArrayList<>();
		line3.add("From \"Harvard Classics Volume 38\""); 
		line3.add("Copyright 1910 by P.F. Collier and Son."); lineblocks.add(new LineBlock(line3, 3));
		List<String> line4 = new ArrayList<>();
		line4.add("This text is placed in the Public Domain, June 1993."); lineblocks.add(new LineBlock(line4, 4));
		List<String> line5 = new ArrayList<>();
		line5.add("INTRODUCTORY NOTE"); lineblocks.add(new LineBlock(line5, 5));
		List<String> line6 = new ArrayList<>();
		line6.add("HIPPOCRATES, the celebrated Greek physician, was a contemporary");
		line6.add("of the historian Herodotus. He was born in the island of Cos between");
		line6.add("470 and 460 B.C., and belonged to the family that claimed descent");
		line6.add("from the mythical AEsculapius, son of Apollo. There was already a");
		line6.add("long medical tradition in Greece before his day, and this he is");
		line6.add("supposed to have inherited chiefly through his predecessor Herodicus;");
		line6.add("and he enlarged his education by extensive travel. He is said,");
		line6.add("though the evidence is unsatisfactory, to have taken part in the");
		line6.add("efforts to check the great plague which devastated Athens at the");
		line6.add("beginning of the Peloponnesian war. He died at Larissa between 380");
		line6.add("and 360 B.C.");
		lineblocks.add(new LineBlock(line6, 6));

		return lineblocks;
	}
	
	
	private boolean compareLoadedParagraphs(List<LineBlock> lineblocks, List<LineBlock> hippoLineBlocks)
	{
		boolean localComparison = false;
		
		for (int i = 0; i < hippoLineBlocks.size(); i++)
		{
			String newStats = String.join(" ", lineblocks.get(i).getStatsAsString());
			String defaultStats = String.join(" ", hippoLineBlocks.get(i).getStatsAsString());
			
			if ( newStats.equals(defaultStats))
			{
				localComparison = true;
			}else
			{
				return false;
			}
		}
		return localComparison;
	}
}
