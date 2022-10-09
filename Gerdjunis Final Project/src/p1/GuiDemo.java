package p1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiDemo extends Application {

	public static BorderPane root;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//--module-path "C:\Program Files\Java\Javafx-sdk-17.0.1\lib" --add-modules=javafx.controls,javafx.fxml
		
		Utilities.restoreWaitingList();
		Utilities.score = new Scorer();
		Utilities.restoreUserBag();
		
		
		
		root = new BorderPane();
		Scene scene = new Scene(root,500,500);
		primaryStage.setScene(scene);
		setView(LoginView.getView());
		primaryStage.show();
		
	
		
		
	}
	
	public static void setView(Pane pane)
	{
		root.setCenter(pane);
	}


}
