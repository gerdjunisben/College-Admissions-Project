package p1;


import java.io.BufferedReader;
import java.io.FileReader;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainView {
private VBox root;
private static Pane view;
	
	public MainView() throws Exception
	{
	
		Label label = new Label();
		label.setText("Welcome");
		
		TextArea area = new TextArea();
		String fileName = "decisions/" + Utilities.currentUser.getLast() + "." + Utilities.currentUser.getFirst() + ".decision.txt";
		try
		{
			FileReader r = new FileReader(fileName);
			BufferedReader br = new BufferedReader(r);
			StringBuffer sb = new StringBuffer();
			String s= br.readLine();
			while(s != null)
			{
				sb.append(s + "\n");
				s = br.readLine();
			}
			br.close();
			r.close();

			area.setText(sb.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
		root = new VBox(47);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(label,area);
		view = new Pane(root);
	}
	
	
	public static Pane getView() throws Exception
	{
		if(view!=null)
			return view;
		else
		{
			new MainView();
			return view;
		}
	}
}

