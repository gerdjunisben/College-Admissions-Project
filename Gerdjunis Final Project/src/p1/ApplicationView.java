package p1;




import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ApplicationView {
private VBox root;
private static Pane view;
private File file;
	
	public ApplicationView() throws Exception
	{
		
		Label label = new Label();
		label.setText("Welcome");
		
		TextField firstNameField = new TextField();
		firstNameField.setPromptText("Enter First Name");
		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Enter Last Name");
		TextField phoneField = new TextField();
		phoneField.setPromptText("Enter Phone Number");
		TextField addressField = new TextField();
		addressField.setPromptText("Enter Address");
		TextField gpaField = new TextField();
		gpaField.setPromptText("Enter GPA");
		TextField satField = new TextField();
		satField.setPromptText("Enter SAT");
		TextField incomeField = new TextField();
		incomeField.setPromptText("Enter Income");
		
		VBox textBox = new VBox(47);
		HBox textBox1 = new HBox(25);
		HBox textBox2 = new HBox(25);
		textBox1.getChildren().addAll(firstNameField,lastNameField,phoneField);
		textBox2.getChildren().addAll(gpaField,satField,incomeField);
		textBox.getChildren().addAll(textBox1,textBox2,addressField);
		
		Button essayButton = new Button("Add essay");
		essayButton.setMaxSize(120, 60);
		
		Button applyButton = new Button("Apply");
		applyButton.setMaxSize(120, 60);
		
		HBox buttonBox = new HBox(47);
		buttonBox.getChildren().addAll(essayButton,applyButton);
		
		
		essayButton.setOnAction((e)->{
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
			
			file = fileChooser.showOpenDialog(null);
			Utilities.currentUser.setEssayFile(file);
			
		});
		
		applyButton.setOnAction((e)->{
			Utilities.currentUser.setAddress(addressField.getText());
			Utilities.currentUser.setFirst(firstNameField.getText());
			Utilities.currentUser.setLast(lastNameField.getText());
			Utilities.currentUser.setPhoneNumber(phoneField.getText());
			String gpa = gpaField.getText();
			String sat = satField.getText();
			String income = incomeField.getText();
			try
			{
				Utilities.currentUser.setGpa(Double.parseDouble(gpa));
			}
			catch(Exception ex)
			{
				gpaField.setText("must be between 0 and 4");
			}
			try
			{
				
				Utilities.currentUser.setSat(Integer.parseInt(sat));
			}
			catch(Exception ex)
			{
				satField.setText("must be between 400 and 1600");
			}
			try
			{
				
				Utilities.currentUser.setIncome(Integer.parseInt(income));
			}
			catch(Exception ex)
			{
				incomeField.setText("must be numeric, negative will be corrected to positive");
			}
			
			if(!Utilities.currentUser.isComplete())
			{
				label.setText("INCOMPLETE APPLICATION");
			}
			else
			{
				Utilities.score.scoreUser(Utilities.currentUser);
				Utilities.backUpBag();
				try {
					GuiDemo.setView(MainView.getView());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			
		});
		
		
		
	
		root = new VBox(47);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label,textBox,buttonBox);
		view = new Pane(root);
	}
	
	
	public static Pane getView() throws Exception
	{
		if(view!=null)
			return view;
		else
		{
			new ApplicationView();
			return view;
		}
	}
}

