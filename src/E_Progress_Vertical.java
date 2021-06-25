import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Import Compnents for this app:
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
//Imports for layout:
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.image.Image;



public class E_Progress_Vertical extends Application {

	//Declare components:
	ProgressBar progBar;
	ProgressIndicator progInd;
	Label lblProgress;
	Button btnIncrease, btnDecrease;
	
	
	public E_Progress_Vertical() {
		//Instantiate components:
		
		progBar = new ProgressBar(0);
		progBar.setMinWidth(100);
		progInd = new ProgressIndicator(0) ;
		lblProgress = new Label("Progress");
		btnIncrease = new Button("Increase");
		btnDecrease = new Button("Decrease");
		btnIncrease.setMinWidth(80);
		btnDecrease.setMinWidth(80);
	}
	
	@Override
	public void init () {
		//Event Handling:
		btnIncrease.setOnAction(ae -> increaseProgress());
		btnDecrease.setOnAction(ae -> decreaseProgress());

	}//init()
	
	public void increaseProgress() {
		
		//First get the progress:
		double progress = progBar.getProgress();
		
		//Now increase the progress...
		progress = progress + 0.05;
		
		if (progress >= 1) {
			progress = 1;
		}
		
		//Greater than 0.7 - change the color:
		if (progress >= 0.7) {
			progBar.setStyle("-fx-accent: red");
			progInd.setStyle("-fx-accent: red");
		}
		
		else if (progress <= 0.7 && progress >= 0.3) {
			progBar.setStyle("-fx-accent: blue");
			progInd.setStyle("-fx-accent: blue");
		}
		
		else {
			progBar.setStyle("-fx-accent: green");
			progInd.setStyle("-fx-accent: green");
		}

		
		
		
		System.out.printf("%.2f\n", progress);
		
		progBar.setProgress(progress);
		progInd.setProgress(progress);
		
		
	}//increaseProgress()
	
	
	public void decreaseProgress() {
		
		//First get the progress:
		double progress = progBar.getProgress();
		
		//Now decrease the progress...
		progress = progress - 0.05;
		
		if (progress < 0.05) {
			progress = 0;
		}
		
		//Greater than 0.7 - change the color:
		if (progress >= 0.7) {
			progBar.setStyle("-fx-accent: red");
			progInd.setStyle("-fx-accent: red");
		}
		
		else if (progress <= 0.7 && progress >= 0.3) {
			progBar.setStyle("-fx-accent: green");
			progInd.setStyle("-fx-accent: green");
		}
		
		else {
			progBar.setStyle("-fx-accent: blue");
			progInd.setStyle("-fx-accent: blue");
		}
		
		
		
		System.out.printf("%.2f\n", progress);
		
		progBar.setProgress(progress);
		progInd.setProgress(progress);
		
	}//decreaseProgress()
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Layout/GUI construction:
		
		//Set title:
		primaryStage.setTitle("Progress V.1.0.0");
		
		//Set icon for application:
//		primaryStage.getIcons().add(new Image ("124.jpg"));
		
		//Set the width and height.
		primaryStage.setWidth(250);
		primaryStage.setHeight(400);
		
		//Create a layout.
		GridPane gpMain = new GridPane();
		
		gpMain.add(lblProgress, 0, 0);
		gpMain.add(progBar, 0, 1);
		gpMain.add(progInd, 0, 2);
		gpMain.add(btnIncrease, 0, 3);
		gpMain.add(btnDecrease, 0, 4);
		

		
		gpMain.setVgap(10);		

		//Add components to the layout.

		progBar.getTransforms().setAll(
                new Translate(0, 10),
                new Rotate(90, 50, 50)
        );

		
		//Align the button right:
		gpMain.setAlignment(Pos.BASELINE_CENTER);
		
		//Set spacing:

		//Create a scene.
		Scene s = new Scene(gpMain);

		//Set the scene.
		primaryStage.setScene(s);
		
		s.getStylesheets().add("./style.css");

		
//		progBar.minWidthProperty().bind(primaryStage.heightProperty().subtract(60.0));

		primaryStage.show();
	}


	public static void main(String[] args) {

		launch();
	}

}
