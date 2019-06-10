package dsMaze;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class Main extends Application {
	
	Scene scene1,scene2;
	public static int size;
	
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		//int size=0; // 
		while (size%2 !=1 || size>25 || size ==1 || size<0) {
			System.out.println("Enter the size of the graph. It must be odd number and not greater than 25!");
			size=input.nextInt();
		}
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Maze");
		Button bttn1=new Button("Generate Maze");
		Button bttn2=new Button("Close");
		bttn2.setOnAction(e -> primaryStage.close());
		bttn1.setOnAction(e -> {
			Graph g = new Graph(size);
			Maze.generateMaze(g);
			g.display();
		});
		primaryStage.setOnCloseRequest(e -> primaryStage.close());
		VBox layout=new VBox(30);
		Label l=new Label("RANDOM MAZE");
		layout.getChildren().addAll(l,bttn1,bttn2);
		layout.setAlignment(Pos.CENTER);
		scene1=new Scene(layout,200,200);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
}
