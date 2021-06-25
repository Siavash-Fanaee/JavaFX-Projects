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


public class F_TypingTutor extends Application {
	
	//Components that need class scope:
	Label lblTyping, lblErrors, lblShowErrors, lblElapsedTime, lblShowElapsedTime;
	TextField txtfSentence, txtfTyping;
	Button btnStart, btnStop;
	
	int errors = 0;
	
	int elapsedTime = 0;
	
	String[] sentences = new String[5];
	
	//Just a counter. Index for the sentences array.
	int i = 0;
	
	//Timer for timing typing exercises.
	Timeline tLine;
	
	
	public F_TypingTutor() {
		
		//Instantiation:
		lblTyping = new Label("Type each sentence into the lower textfield:");
		lblErrors = new Label("Errors:"); 
		lblShowErrors = new Label(); 
		lblElapsedTime = new Label("Elapsed Time:");
		lblShowElapsedTime = new Label(); 
		
		txtfSentence = new TextField();
		txtfTyping = new TextField();
		btnStart = new Button("Start");
		btnStop = new Button("Stop");
		
		btnStart.setMinWidth(50);
		btnStop.setMinWidth(50);
		
		//Create some sentences as examples. Just hardcode.
		//sentences = new String[5];
		sentences[0] = "Mary had a little lamb.";
		sentences[1] = "Whose fleece was white as snow.";
		sentences[2] = "And everywhere that Mary went.";
		sentences[3] = "The lamb was sure to go.";
		sentences[4] = "That's all folks!";
		
	}//constructor()

	
	
	@Override
	public void init() {
		//Event handling:
		
		txtfTyping.setOnKeyReleased(ae -> {
			//Check if the start of each sentence is the same.
			if(txtfSentence.getText().startsWith(txtfTyping.getText())) {
				//All correct thus far...
				txtfTyping.setStyle("-fx-text-inner-color: black;");
				
			}//if
			else {
				//Increment the error count.
				errors ++;
				
				//Update the errors label.
				lblShowErrors.setText(errors + "");
				
				//There is a typing error. Flag the error.
				txtfTyping.setStyle("-fx-text-inner-color: red;");
				
			}//else
			
			//Check if the sentence is complete. It matches the example sentence in full.
			if(txtfTyping.getText().equals(txtfSentence.getText())) {
				
				//Check that the array index stays within bounds.
				if(i + 1 < sentences.length) {
					//Show the next sentence.
					i++;		//increment the sentence index.
					txtfSentence.setText(sentences[i]);
					
					//Clear the typing textfield.
					txtfTyping.clear();
				}//if array in bounds.							
				
			}//if sentence is complete.	
		});
		
		btnStart.setOnAction(ae -> {
			
			//Clear the time.
			elapsedTime = 0;
			
			//Update the time in the label.
			lblShowElapsedTime.setText(elapsedTime + "");
			
			//Reset the sentences index.
			i = 0;
			
			//Clear the error count.
			errors = 0;
			
			//Show the updated error count.
			lblShowErrors.setText(errors + "");
			
			//First, show the first sentence.
			txtfSentence.setText(sentences[i]);
			
			//And clear the lower textfield.
			txtfTyping.clear();
						
			//Start timing the test.
			tLine = new Timeline(new KeyFrame(
					Duration.millis(1000),
					timertick -> {
						elapsedTime++;
						lblShowElapsedTime.setText(elapsedTime + "");
					}
					));
			
			tLine.setCycleCount(Animation.INDEFINITE);
			tLine.play();
			
			//Prevent multiple clicks on the start button.
			btnStart.setDisable(true);					
			
		});			//Event handling for the start button.
		
		
		
		btnStop.setOnAction(ae -> {
			tLine.stop();
			
			i = 0;
			
			btnStart.setDisable(false);
			
		});
		
	}//init()

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Set title and size:
		primaryStage.setTitle("Typing Tutorial");
		primaryStage.setWidth(500);
		primaryStage.setHeight(200);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setHgap(10);
		gpMain.setVgap(10);
		
		
		gpMain.add(lblTyping, 0, 0);
		gpMain.add(lblErrors, 1, 0);
		gpMain.add(lblShowErrors, 2, 0);
		gpMain.add(txtfSentence, 0, 1);
		gpMain.add(lblElapsedTime, 1, 1);
		gpMain.add(lblShowElapsedTime, 2, 1);
		gpMain.add(txtfTyping, 0, 2);
		
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(btnStart , btnStop);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		hbButtons.setSpacing(10);
		
		gpMain.add(hbButtons, 0, 3);
		
		
		Scene s = new Scene(gpMain);
		
		primaryStage.setScene(s);
		
		s.getStylesheets().add("./style1.css");

		
		//Get icon image:
		primaryStage.getIcons().add(new Image("Typing.png"));
		
		txtfTyping.minWidthProperty().bind(primaryStage.widthProperty().subtract(180));

		
		//Show the stage:
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		//Launch the application:
		launch();
	}

}
