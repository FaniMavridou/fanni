package datamodel.buildingblocks;

import java.util.List;

import dataload.RawFileLineLoader;


public class Document {

	private String inputPath;
	private DocumentRawType fileType;
	private List<LineBlock> lineblocks;
	
	public enum DocumentRawType{
		RAW, ANNOTATED
	}
	
	public Document(String inputPath, DocumentRawType fileType) {
		this.inputPath = inputPath;
		this.fileType = fileType;
		loadFiles(inputPath);
	}
	
	private void loadFiles(String inputPath){
		
		RawFileLineLoader loader = new RawFileLineLoader();
		this.lineblocks = loader.load(this.inputPath, this.lineblocks);
	}
	
	public DocumentRawType getInputFileType() {
		
		return fileType;
	}
	
	public List<LineBlock> getLineblocks(){
		return lineblocks;
	}

}