package client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import engine.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class RulesController implements Initializable
{

	private Engine mpampis;
	
	
	@FXML private Button btnApply;
	@FXML private Button btnContinue;
	
	@FXML private CheckBox H1Box;
	@FXML private CheckBox H2Box;
	@FXML private CheckBox ItalicBox;
	@FXML private CheckBox BoldBox;
	@FXML private CheckBox OmitBox;
	
	@FXML private CheckBox pr1;
	@FXML private CheckBox pr2;
	@FXML private CheckBox pr3;
	@FXML private CheckBox pr4;
	@FXML private CheckBox pr5;
	
	
	@FXML private ChoiceBox<String> H1ChoiceBox; 
	@FXML private ChoiceBox<String> H2ChoiceBox;
	@FXML private ChoiceBox<String> italicChoiceBox;
	@FXML private ChoiceBox<String> boldChoiceBox;
	@FXML private ChoiceBox<String> omitChoiceBox;
	
	@FXML private TextField h1Text;
	@FXML private TextField h2Text;
	@FXML private TextField italicText;
	@FXML private TextField boldText;
	@FXML private TextField omitText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		h1Text.setDisable(true);
		h2Text.setDisable(true);
		italicText.setDisable(true);
		boldText.setDisable(true);
		omitText.setDisable(true);
		
		H1ChoiceBox.setDisable(true);
		H2ChoiceBox.setDisable(true);
		italicChoiceBox.setDisable(true);
		boldChoiceBox.setDisable(true);
		omitChoiceBox.setDisable(true);
		
		H1ChoiceBox.getItems().add("STARTS_WITH");
		H1ChoiceBox.getItems().add("ALL_CAPS");
		H1ChoiceBox.getItems().add("POSITIONS");
		
		H2ChoiceBox.getItems().add("STARTS_WITH");
		H2ChoiceBox.getItems().add("ALL_CAPS");
		H2ChoiceBox.getItems().add("POSITIONS");
		
		italicChoiceBox.getItems().add("STARTS_WITH");
		italicChoiceBox.getItems().add("ALL_CAPS");
		italicChoiceBox.getItems().add("POSITIONS");
		
		boldChoiceBox.getItems().add("STARTS_WITH");
		boldChoiceBox.getItems().add("ALL_CAPS");
		boldChoiceBox.getItems().add("POSITIONS");
		
		omitChoiceBox.getItems().add("STARTS_WITH");
		omitChoiceBox.getItems().add("ALL_CAPS");
		omitChoiceBox.getItems().add("POSITIONS");
		
		
	}
	//get engine element from firstController
	public void getEngine(Engine engine) {
		this.mpampis = engine;
		
	}
	
	@FXML
	private void button()
	{
		
		System.out.println("eimai mesa ;)");
		List<List<String>> ruleSetString = new ArrayList<>();
		
		
		String input;
		String selectedChoice;
		
		if(H1Box.isSelected()) {
			List<String> rule = new ArrayList<>();
			input = h1Text.getText(); //1
			selectedChoice = H1ChoiceBox.getSelectionModel().getSelectedItem(); //POSITION
			System.out.println("Text Field: " + input);
			System.out.println("Text choice: " + selectedChoice);
			rule.add("H1");
			rule.add(selectedChoice);
			rule.add(input);
			ruleSetString.add(rule);
			if(selectedChoice.equals("STARTS_WITH")) {
				if(pr1.isSelected()) {
					List<String> prefixes = new ArrayList<>();
					prefixes.add(input);
					mpampis.registerInputRuleSetForAnnotatedFiles(ruleSetString, prefixes);
				}
			}
		}
		
		if(H2Box.isSelected()) {
			List<String> rule = new ArrayList<>();
			input = h2Text.getText();
			selectedChoice = H2ChoiceBox.getSelectionModel().getSelectedItem();
			rule.add("H2");
			rule.add(selectedChoice);
			rule.add(input);	
			ruleSetString.add(rule);
			if(selectedChoice.equals("STARTS_WITH")) {
				if(pr1.isSelected()) {
					List<String> prefixes = new ArrayList<>();
					prefixes.add(input);
					mpampis.registerInputRuleSetForAnnotatedFiles(ruleSetString, prefixes);
				}
			}
			//System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");	
		}
		if(ItalicBox.isSelected()) {
			List<String> rule = new ArrayList<>();
			input = italicText.getText();
			selectedChoice = italicChoiceBox.getSelectionModel().getSelectedItem();
			rule.add("<I>");
			rule.add(selectedChoice);
			rule.add(input);	
			ruleSetString.add(rule);
			if(selectedChoice.equals("STARTS_WITH")) {
				if(pr1.isSelected()) {
					List<String> prefixes = new ArrayList<>();
					prefixes.add(input);
					mpampis.registerInputRuleSetForAnnotatedFiles(ruleSetString, prefixes);
				}
			}
		}
		if(BoldBox.isSelected()) {
			List<String> rule = new ArrayList<>();
			input = boldText.getText();
			selectedChoice = boldChoiceBox.getSelectionModel().getSelectedItem();
			rule.add("<B>");
			rule.add(selectedChoice);
			rule.add(input);	
			ruleSetString.add(rule);
			if(selectedChoice.equals("STARTS_WITH")) {
				if(pr1.isSelected()) {
					List<String> prefixes = new ArrayList<>();
					prefixes.add(input);
					mpampis.registerInputRuleSetForAnnotatedFiles(ruleSetString, prefixes);
				}
				mpampis.registerInputRuleSetForPlainFiles(ruleSetString);
			}
		}
		if(OmitBox.isSelected()) {
			List<String> rule = new ArrayList<>();
			input = omitText.getText();
			selectedChoice = omitChoiceBox.getSelectionModel().getSelectedItem();
			System.out.println("Text choice: " + selectedChoice);
			rule.add("OMIT");
			rule.add(selectedChoice);
			rule.add(input);	
			ruleSetString.add(rule);
			if(selectedChoice.equals("STARTS_WITH")) {
				if(pr1.isSelected()) {
					List<String> prefixes = new ArrayList<>();
					prefixes.add(input);
					mpampis.registerInputRuleSetForAnnotatedFiles(ruleSetString, prefixes);
				}
			}
		}
		mpampis.registerInputRuleSetForPlainFiles(ruleSetString);
		mpampis.loadFileAndCharacterizeBlocks();
		//String path = "C:\\Users\\user\\Desktop\\gg.pdf";
		//mpampis.exportPdf(path);
	}
	
	//methods for checkBox
	@FXML
	private void checkH1()
	{
		if(H1Box.isSelected()) {
			H1ChoiceBox.setDisable(false);
			h1Text.setDisable(false);
			
		}
		else {
			H1ChoiceBox.setDisable(true);
			h1Text.setDisable(true);
		}
	}

	@FXML
	private void checkH2()
	{
		if(H2Box.isSelected()) {
			H2ChoiceBox.setDisable(false);
			h2Text.setDisable(false);
			
		}
		else {
			H2ChoiceBox.setDisable(true);
			h2Text.setDisable(true);
		}
	}
	

	@FXML
	private void checkItalic()
	{
		if(ItalicBox.isSelected()) {
			italicChoiceBox.setDisable(false);
			italicText.setDisable(false);
			
		}
		else {
			italicChoiceBox.setDisable(true);
			italicText.setDisable(true);
		}
	}
	

	@FXML
	private void checkBold()
	{
		if(BoldBox.isSelected()) {
			boldChoiceBox.setDisable(false);
			boldText.setDisable(false);
			
		}
		else {
			boldChoiceBox.setDisable(true);
			boldText.setDisable(true);
		}
	}
	

	@FXML
	private void checkOmit()
	{
		if(OmitBox.isSelected()) {
			omitChoiceBox.setDisable(false);
			omitText.setDisable(false);
			
		}
		else {
			omitChoiceBox.setDisable(true);
			omitText.setDisable(true);
		}
	}
	/*
	@FXML
	private void apply() {
		
		String selectedChoice = H1ChoiceBox.getSelectionModel().getSelectedItem();
		switch(selectedChoice) {
			case("Starts_With") : 
				System.out.println("Give me the prefixes");
				break;
				
			case("Position") :
				System.out.println("Give me the prefixes");
				
				break;
				
			case("All_Caps") :
				System.out.println("Give me the prefixes");
			    break;
			    
			default :
				System.out.println("YOU HAVE TO GIVE A RULE");
				break;
		}
		
		
	}
	*/
	
	
}