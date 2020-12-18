package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import org.apache.commons.io.FileUtils;

import engine.Engine;
import engine.IPlainTextDocumentEngine;


/**
 * Test class to test the back-end, server class Engine of the project
 * 
 * Use several profiles for the handling of tested texts
 * 
 * @author pvassil
 * @version 0.1
 * 
 */
public class TestEngine {
	private static List<String> omList; 
	private static List<String> h1List; 
	private static List<String> h2List; 
	private static List<String> boldList; 
	private static List<String> italicsList;
	private static List<List<String>> inputSpec;
	private static IPlainTextDocumentEngine engine;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testRegisterInputRuleSet() {
		String inputFileName = "Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(inputFileName, "RAW", "happyhippo");
		
		// Test hippo ruleset
		String profile = setupProfileHippo();
		String ruleset = engine.registerInputRuleSetForPlainFiles(inputSpec).toString();
		assertEquals(profile.strip(), ruleset.strip());
		
		// Test econ ruleset
		inputFileName = "Resources/SampleDocs/economy_mt.txt";
		engine = new Engine(inputFileName, "RAW", "economy");
		
		profile = setupProfileEcon();
		ruleset = engine.registerInputRuleSetForPlainFiles(inputSpec).toString();
		assertEquals(profile.strip(), ruleset.strip());
		
		// Test HTMLhippo ruleset
		List<String> prefixes = new ArrayList<String>(); 
		prefixes.add("<H1>");prefixes.add("<H2>");prefixes.add("<i>");prefixes.add("<b>");prefixes.add("<p>");
		inputFileName = "Resources/SampleDocs/hippocratesOath.html";
		engine = new Engine(inputFileName, "ANNOTATED", "happyhippoHTML");
		
		profile = setupProfileHTLMHippo();
		ruleset = engine.registerInputRuleSetForAnnotatedFiles(inputSpec, prefixes).toString();
		assertEquals(profile.strip(), ruleset.strip());
	}
	

	private static String setupProfileEcon() {
		inputSpec = new ArrayList<List<String>>();
		omList = new ArrayList<String>(); inputSpec.add(omList);
		omList.add("OMIT");omList.add("POSITIONS");omList.add("2,3");
		h1List = new ArrayList<String>(); inputSpec.add(h1List);
		h1List.add("H1");h1List.add("STARTS_WITH"); h1List.add("POLITICAL ECONOMY");
		boldList = new ArrayList<String>(); inputSpec.add(boldList);
		boldList.add("<B>");boldList.add("ALL_CAPS");

		String referenceResult = "inputRuleSet" + "\n" + 
				"OMIT:  IN_POS [2, 3]" + "\n" + 
		"H1:  STARTS_WITH (POLITICAL ECONOMY)" + "\n" + 
		"H2:  UNDEFINED" + "\n" + 
		"BOLD:  ALL_CAPS" + "\n" +
		"ITALICS:  UNDEFINED"; 

		return referenceResult;
	}
	
	private static String setupProfileHippo() {
		inputSpec = new ArrayList<List<String>>();
		h1List = new ArrayList<String>(); inputSpec.add(h1List);
		h1List.add("H1");h1List.add("STARTS_WITH"); h1List.add("OATH AND");
		omList = new ArrayList<String>(); inputSpec.add(omList);
		omList.add("OMIT");omList.add("POSITIONS");omList.add("1,4");
		h2List = new ArrayList<String>(); inputSpec.add(h2List);
		h2List.add("H2");h2List.add("ALL_CAPS");
		italicsList = new ArrayList<String>(); inputSpec.add(italicsList);
		italicsList.add("<I>");italicsList.add("POSITIONS"); italicsList.add("5,17");
		
		String referenceResult = "inputRuleSet" + "\n" + 
				"OMIT:  IN_POS [1, 4]" + "\n" + 
		"H1:  STARTS_WITH (OATH AND)" + "\n" + 
		"H2:  ALL_CAPS" + "\n" + 
		"BOLD:  UNDEFINED" + "\n" +
		"ITALICS:  IN_POS [5, 17]"; 

		return referenceResult;
	}
	
	
	private static String setupProfileHTLMHippo() {
		inputSpec = new ArrayList<List<String>>();
		h1List = new ArrayList<String>(); inputSpec.add(h1List);
		h1List.add("H1");h1List.add("STARTS_WITH"); h1List.add("<H1>");
		h2List = new ArrayList<String>(); inputSpec.add(h2List);
		h2List.add("H2");h2List.add("STARTS_WITH"); h2List.add("<H2>");
		italicsList = new ArrayList<String>(); inputSpec.add(italicsList);
		italicsList.add("<I>");italicsList.add("STARTS_WITH"); italicsList.add("<i>");
		boldList = new ArrayList<String>(); inputSpec.add(boldList);
		boldList.add("<B>");boldList.add("STARTS_WITH"); boldList.add("<b>");

		String referenceResult = "inputRuleSet" + "\n" +
				"OMIT:  UNDEFINED" + "\n" +
		"H1:  STARTS_WITH (<H1>)" + "\n" + 
		"H2:  STARTS_WITH (<H2>)" + "\n" +
		"BOLD:  STARTS_WITH (<b>)" + "\n" +
		"ITALICS:  STARTS_WITH (<i>)";

		return referenceResult;	
	}
	
	

	@Test
	public final void testLoadProcessWriteMarkupHippo() {
		String inputFileName = "Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(inputFileName, "RAW", "happyhippo");
		setupProfileHippo();
		engine.registerInputRuleSetForPlainFiles(inputSpec);
		String outputFileName = "Resources//Outputs//hippocratesOath.txt.md";
		
		int inputBlocks = engine.loadFileAndCharacterizeBlocks();
		assertEquals(17,inputBlocks);
		int outputParagraphs = engine.exportMarkDown(outputFileName); //, "happyhippo"
		assertEquals(15,outputParagraphs); //used to be 17
		
		List<String> report = engine.reportWithStats();
		assertEquals("Total number of paragraphs: 17", report.get(0).strip());
		assertEquals("Total number of words: 1145", report.get(1).strip());
		
		File outputFile = new File("Resources//Outputs//hippocratesOath.txt.md");
		File outputFileRef = new File("Resources//OutputReferences//hippocratesOath.Reference.Setup2.md");
		Boolean localComparison = compareFiles(outputFile, outputFileRef, "testLoadProcessWriteMarkupHippo()");
		assertEquals(true, localComparison);
	}

	@Test
	public final void testLoadProcessWriteMarkupEconomy() {
		String inputFileName = "Resources/SampleDocs/economy_mt.txt";
		engine = new Engine(inputFileName, "RAW", "economy");
		setupProfileEcon();
		engine.registerInputRuleSetForPlainFiles(inputSpec);
		String outputFileName = "Resources//Outputs//economy_mt.txt.md";
		
		int inputBlocks = engine.loadFileAndCharacterizeBlocks();
		assertEquals(19,inputBlocks);
		
		int outputParagraphs = engine.exportMarkDown(outputFileName);//, "economy"
		assertEquals(17,outputParagraphs);			//used to be 19
		File outputFile = new File("Resources//Outputs//economy_mt.txt.md");
		File outputFileRef = new File("Resources//OutputReferences//economy_mt.Reference.Setup1.md");
		Boolean localComparison = compareFiles(outputFile, outputFileRef, "testLoadProcessWriteMarkupEconomy()");
		assertEquals(true, localComparison);
	}

	@Test
	public final void testLoadProcessWritePdfHippo() {
		String inputFileName = "Resources/SampleDocs/hippocratesOath.txt";
		engine = new Engine(inputFileName, "RAW", "happyhippo");
		setupProfileHippo();
		engine.registerInputRuleSetForPlainFiles(inputSpec);
		String outputFileName = "Resources//Outputs//hippocratesOath.txt.pdf";
		engine.loadFileAndCharacterizeBlocks();
		int outputParagraphs = engine.exportPdf(outputFileName);
		assertEquals(15,outputParagraphs);
		//does not work: some contents are auto-generated and differ.
		//to find out, must check by reading par. by par.
//		File outputFile = new File("Resources//Outputs//hippocratesOath.txt.pdf");
//		File outputFileRef = new File("Resources//OutputReferences//hippocratesOath.Reference.Setup2.pdf");
//		Boolean localComparison = compareFiles(outputFile, outputFileRef, "testLoadProcessWritePdfHippo()");
//		assertEquals(true, localComparison);
	}
	
	@Test
	public final void testLoadProcessWritePdfEconomy() {
		String inputFileName = "Resources/SampleDocs/economy_mt.txt";
		engine = new Engine(inputFileName, "RAW", "economy");
		setupProfileEcon();
		engine.registerInputRuleSetForPlainFiles(inputSpec);
		String outputFileName = "Resources//Outputs//economy_mt.txt.pdf";
		engine.loadFileAndCharacterizeBlocks();
		
		int outputParagraphs = engine.exportPdf(outputFileName);
		assertEquals(17,outputParagraphs);
//		File outputFile = new File("Resources//Outputs//economy_mt.txt.md");
//		File outputFileRef = new File("Resources//OutputReferences//economy_mt.Reference.Setup1.md");
//		Boolean localComparison = compareFiles(outputFile, outputFileRef, "testLoadProcessWriteMarkupEconomy()");
//		assertEquals(true, localComparison);
	}

	
	
	@Test
	public final void testLoadProcessWriteMarkupHTMLHippo() {
		String inputFileName = "Resources/SampleDocs/hippocratesOath.html";
		engine = new Engine(inputFileName, "ANNOTATED", "happyhippoHTML");
		setupProfileHTLMHippo();
		List<String> prefixes = new ArrayList<String>(); 
		prefixes.add("<H1>");prefixes.add("<H2>");prefixes.add("<i>");prefixes.add("<b>");prefixes.add("<p>");
		engine.registerInputRuleSetForAnnotatedFiles(inputSpec, prefixes);
		engine.loadFileAndCharacterizeBlocks();
		
		String outputFileName = "Resources//Outputs//hippocratesOathHtml.md";
		int outputParagraphs = engine.exportMarkDown(outputFileName);
		assertEquals(17,outputParagraphs);
//		File outputFile = new File("Resources//Outputs//hippocratesOathHtml.md");
//		File outputFileRef = new File("Resources//OutputReferences//hippocratesOath.Reference.Setup2.md");
//		Boolean localComparison = compareFiles(outputFile, outputFileRef, "testLoadProcessWriteMarkupHTMLHippo()");
//		assertEquals(true, localComparison);
	}
	
	
	private Boolean compareFiles(File outputFile, File outputFileRef, String caller) {
		Boolean localComparison = false;
		try {
			localComparison = FileUtils.contentEquals(outputFile, outputFileRef);
		} catch (IOException e) {
			System.err.println("[TestEngine] IO Exception at "+ caller);
			e.printStackTrace();
		}
		return localComparison;
	}//end compareFiles

}//end class