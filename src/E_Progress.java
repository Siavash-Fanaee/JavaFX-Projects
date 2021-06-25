//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

//Components for layout.
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;			
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

//Insetting and alignment.
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Support for quitting.
import javafx.application.Platform;

//Imports for file handling support.
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.stage.FileChooser;
import java.io.File;


//Image for the icon.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//Imports for alerts.
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Date handling.
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.DatePicker;

//Currency output formatting.
import java.text.NumberFormat;
import java.util.Locale;

//Timing of a typing test.
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;

public class E_Progress extends Application {
	
	//Declare in class scope:
	ProgressBar progBar;
	ProgressIndicator progInd;
	Label lblProgress;
	Button btnIncrease, btnDecrease;
	
	
	public E_Progress() {
		//Instantiation:
		progBar = new ProgressBar(0);
		progInd = new ProgressIndicator(0);
		lblProgress = new Label("Progress: ");
		btnIncrease= new Button("Increase");
		btnDecrease = new Button("Decrease");
		
	}//constructor()
	
	
	@Override
	public void init() {
		
		//Event Handling for buttons:
		
		btnIncrease.setOnAction(ae -> increaseProgress());
		
		btnDecrease.setOnAction(ae -> decreaseProgress());
		
		
	}//init()
	
	public void increaseProgress() {
		
		double progress = progBar.getProgress();
		
		//Now increase the progress. Progress full-scale is 1.0. Use floating point, eg. double.
		progress = progress + 0.05;
		
		//Progress value increased. Check if off-scale and correct if necessary.
		if(progress >= 1) {
			progress = 1;
		}//if
			
		//Print to the console to show F.P. approximation problem.
		System.out.println(progress + "");
		
		//Set the progress of the progress bar.
		progBar.setProgress(progress);
		progInd.setProgress(progress);
		
	}//increaseProgress()
	
	
	public void decreaseProgress() {
		
		double progress = progBar.getProgress();
		
		//Now increase the progress. Progress full-scale is 1.0. Use floating point, eg. double.
		progress = progress - 0.05;
		
		//Progress value increased. Check if off-scale and correct if necessary.
		if(progress <= 0) {
			progress = 0;
		}//if
			
		//Print to the console to show F.P. approximation problem.
		System.out.println(progress + "");
		
		//Set the progress of the progress bar.
		progBar.setProgress(progress);
		progInd.setProgress(progress);
		
	}//decreaseProgress()
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Set title:
		primaryStage.setTitle("Progress");
		
		//Set Size:
		primaryStage.setWidth(400);
		primaryStage.setHeight(250);
		
		//Create layout (first level)
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		
		//Create layout (second level)
		HBox hbLabel = new HBox();
		HBox hbProgress = new HBox();
		HBox hbButtons = new HBox();
		
		//Add components to the second level layout
		hbLabel.getChildren().add(lblProgress);
		hbProgress.getChildren().addAll(progBar , progInd);
		hbButtons.getChildren().addAll(btnIncrease , btnDecrease);
		
		//Some adjustments:
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		hbButtons.setSpacing(10);
//		hbProgress.setSpacing(5);
		hbLabel.setPadding(new Insets(10));
		
		//Add second level layout to the first level Layout:
		vbMain.getChildren().addAll(hbLabel , hbProgress , hbButtons);

		progBar.minWidthProperty().bind(primaryStage.widthProperty().subtract(60.0));

		//Create scene:
		Scene s = new Scene(vbMain);
		
		//Set style:
		s.getStylesheets().add("style.css");
		
		//Set the scene:
		primaryStage.setScene(s);
		
		//Show the stage:
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		//Launch the application:
		launch();
	}

}
