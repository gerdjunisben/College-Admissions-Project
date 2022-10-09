package p1;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SignUpView {
private VBox root;
private static Pane view;
	
	public SignUpView() throws Exception
	{
	
		Label label = new Label();
		label.setText("Welcome");
		
		TextField emailField = new TextField();
		emailField.setPromptText("Enter Email");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password");
		PasswordField password2Field = new PasswordField();
		password2Field.setPromptText("Re-enter Password");
		
		VBox textBox = new VBox(47);
		textBox.getChildren().addAll(emailField,passwordField,password2Field);
		
		Button loginButton = new Button("Login");
		loginButton.setMaxSize(60, 60);
		
		Button signUpButton = new Button("Sign up");
		signUpButton.setMaxSize(60, 60);
		
		HBox buttonBox = new HBox(47);
		buttonBox.getChildren().addAll(signUpButton,loginButton);
		
		
		signUpButton.setOnAction((e)->{
			if(passwordField.getText().equals(password2Field.getText()))
			{
				boolean uFlag = false;
				boolean lFlag = false;
				boolean dFlag = false;
				boolean success = false;
				for(int x = 0;x<passwordField.getText().length();x++)
				{
					if(Character.isDigit(passwordField.getText().charAt(x)))
						dFlag = true;
					if(Character.isLowerCase(passwordField.getText().charAt(x)))
						lFlag = true;
					if(Character.isUpperCase(passwordField.getText().charAt(x)))
						uFlag = true;
					if(uFlag && lFlag && dFlag && passwordField.getText().length() > 7)
					{
						
						success = true;
						
						break;
					}
				}
				if(Utilities.bag.search(emailField.getText())!=null)
				{
					success=false;
				}
				
				if(!success)
				{
					if(Utilities.bag.search(emailField.getText())==null)
					{
						label.setText("Invalid password");
					}
					else
					{
						label.setText("Invalid Email");
					}
					
				}
				else
				{
					Utilities.currentUser = new User(emailField.getText(),passwordField.getText());
					Utilities.bag.insert(Utilities.currentUser);
					Utilities.backUpBag();
					try {
						GuiDemo.setView(ApplicationView.getView());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			else
			{
				label.setText("Passwords don't match");
			}
		});
		
		loginButton.setOnAction((e)->{
			try {
				GuiDemo.setView(LoginView.getView());
			} catch (Exception e1) {
				e1.printStackTrace();
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
			new SignUpView();
			return view;
		}
	}
}

