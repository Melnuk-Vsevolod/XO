

package xoFX;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button button1 = new Button("1");
		Button button2 = new Button("2");
		Button button3 = new Button("3");
		Button button4 = new Button("4");
		Button button5 = new Button("5");
		Button button6 = new Button("6");
		Button button7 = new Button("7");
		Button button8 = new Button("8");
		Button button9 = new Button("9");
		
		
		
		
		BorderPane pane = new BorderPane();
		BorderPane pane1 = new BorderPane();
		BorderPane pane2 = new BorderPane();
		BorderPane pane3 = new BorderPane();
		button1.boundsInLocalProperty();
		
		
		
		pane.setTop(pane1);
		pane.setCenter(pane2);
		pane.setBottom(pane3);
		
		pane1.setLeft(button1);
		pane1.setCenter(button2);
		pane1.setRight(button3);
		
		
		pane2.setLeft(button4);
		pane2.setCenter(button5);
		pane2.setRight(button6);
		
		pane3.setLeft(button7);
		pane3.setCenter(button8);
		pane3.setRight(button9);
		
		
//		pane.setCenter(button3);
//		pane.setLeft(button4);
//		pane.setRight(button5);
		
	

		Scene scene = new Scene(pane, 100, 100);

		primaryStage.setTitle("XO");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
