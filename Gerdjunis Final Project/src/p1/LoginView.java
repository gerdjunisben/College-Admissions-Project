package p1;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoginView {
private VBox root;
private static Pane view;
	
	public LoginView() throws Exception
	{
	
		Label label = new Label();
		label.setText("Welcome");
		
		TextField emailField = new TextField();
		emailField.setPromptText("Enter Email");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Enter Password");
		
		VBox textBox = new VBox(47);
		textBox.getChildren().addAll(emailField,passwordField);
		
		Button loginButton = new Button("Login");
		loginButton.setMaxSize(60, 60);
		
		Button signUpButton = new Button("Sign up");
		signUpButton.setMaxSize(60, 60);
		
		HBox buttonBox = new HBox(47);
		buttonBox.getChildren().addAll(loginButton,signUpButton);
		
		
		loginButton.setOnAction((e)->{
			Utilities.currentUser = Utilities.bag.search(emailField.getText());
			if( Utilities.currentUser!=null && Utilities.currentUser.getPassword().equals(passwordField.getText()))
			{
				try {
					if(!Utilities.currentUser.isComplete())
					{
						GuiDemo.setView(ApplicationView.getView());
					}
					else
					{

						Utilities.score.scoreUser(Utilities.currentUser);
						GuiDemo.setView(MainView.getView());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else
			{
				label.setText("Invalid login");
			}
		});
		
		signUpButton.setOnAction((e)->{
			try {
				GuiDemo.setView(SignUpView.getView());
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
			new LoginView();
			return view;
		}
	}
}

