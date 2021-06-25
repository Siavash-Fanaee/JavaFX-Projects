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

public class G_ContactManager extends Application {

	Label lblNames, lblEmail, lblAddress;
	TextField txtfEmail;
	Button btnAbout, btnClose;
	TextArea txtAddr;
	ListView lvNames;
	
	public G_ContactManager() {
		
		lblNames = new Label("Names: ");
		lblEmail = new Label("Email: "); 
		lblAddress = new Label("Address: ");
		
		txtfEmail = new TextField();
		
		btnAbout = new Button("About");
		btnClose = new Button("Close");
		btnAbout.setMinWidth(60);
		btnClose.setMinWidth(60);
		
		txtAddr = new TextArea();
		lvNames = new ListView();
	}
	
	@Override
	public void init() {
		
		
		btnClose.setOnAction(ae -> Platform.exit());
		btnAbout.setOnAction(ae -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About Contact Manager");
			alert.setHeaderText("ContactManager V1.0.0. 2021");
			alert.setContentText("ContactManager personal contacts manager application.\n" + 
			"Copyright the HDC ContactManager Corp. 2021.");
			
			Image img = new Image("logo1.png");
			ImageView imView = new ImageView(img);
			alert.setGraphic(imView);
			
			alert.showAndWait();
		});
		
		lvNames.setOnMousePressed(ae -> {
			
			//Get the name that is selected.
			String selectedName = lvNames.getSelectionModel().getSelectedItem().toString();
			
			//Now call showEmailAndAddr() with the name as arg.
			showEmailAndAddr(selectedName);
		});
		
		lvNames.setOnKeyReleased(ae -> {
			
			//Get the name that is selected.
			String selectedName = lvNames.getSelectionModel().getSelectedItem().toString();
			
			showEmailAndAddr(selectedName);		
			
			
		});
		
	}
	
	private void showEmailAndAddr(String name){
		
		//Read lines from the contacts file.
		
		try {
			
			String line;			//Stores lines from the file.
			
			FileReader fr = new FileReader("./contacts.csv");
			BufferedReader buf = new BufferedReader(fr);
			
			//Iterate through the file reading a line at a time.
			while((line = buf.readLine()) != null) {
				
				//Test if the line starts with the target name.
				if(line.startsWith(name)) {
					//Split the line from the file.
					String [] dataArray = new String[3];
					dataArray = line.split(":");
					
					//Put the email and address strings into txtfEmail and txtAddr.
					txtfEmail.setText(dataArray[1]);
					txtAddr.setText(dataArray[2]);
										
					
				}//if name found
				else;			//Do nothing.				
			}//while
			
			//Finished with while loop. Just close the file.
			buf.close();
			
			
		}//try
		catch(IOException ioe) {
			
			System.out.println("Error reading contacts file.");
			ioe.toString();
			
		}//catch()
		
		
	}//showEmailAndAddr()
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Contact Manager");
		primaryStage.setWidth(500);
		primaryStage.setHeight(300);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setHgap(10);
		gpMain.setVgap(10);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnAbout , btnClose);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		gpMain.add(lblNames, 0, 0);
		gpMain.add(lblEmail, 1, 0);
		gpMain.add(lvNames, 0, 1 , 1 , 3);
		gpMain.add(txtfEmail, 1, 1);
		gpMain.add(lblAddress, 1, 2);
		gpMain.add(txtAddr, 1, 3);
		
		gpMain.add(hbButtons, 1, 4);
		
		
		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style.css");
		
		readContactNames("./contacts.csv");
		
		
		primaryStage.show();
	}

	private void readContactNames(String contactsFile) {

		try {
			String line;				//Stores lines from the file.
			
			FileReader fr = new FileReader(contactsFile);
			BufferedReader buf = new BufferedReader(fr);
			
			//Iterate through the file. Read one line at a time.
			while((line = buf.readLine()) != null) {
				
				//A 3-item array to store data from each line.
				String [] contactDataArray = new String[3];
				
				//Split the line from the file on ':'.
				contactDataArray = line.split(":");
				
				//Add just names to the list view.
				lvNames.getItems().add(contactDataArray[0]);
							
				
			}//while()
			
			//Done reading the file. Close it.
			buf.close();
					
			
		}//try
		catch(IOException ioe) {
			
			System.out.println("Error reading the contacts file.");
			ioe.toString();
						
		}//catch()
	}

	
	public static void main(String[] args) {
		launch();
	}

}
