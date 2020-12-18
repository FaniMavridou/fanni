package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.buildingblocks.Document.DocumentRawType;
import datamodel.buildingblocks.Document;

public class TestDocument {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public final void testDocumentLoadHippo() {
		Document document = new Document("Resources/SampleDocs/hippocratesOath.txt", DocumentRawType.RAW);
		
		assertEquals(17, document.getLineblocks().size());
	}
	
	@Test
	public final void testDocumentLoadEcon() {
		Document document = new Document("Resources/SampleDocs/economy_mt.txt", DocumentRawType.RAW);
		
		assertEquals(19, document.getLineblocks().size());
	}
}
