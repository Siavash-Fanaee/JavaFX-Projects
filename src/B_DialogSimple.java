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

public class B_DialogSimple extends Application {
	
	//Declare components that require class scope:
	MenuBar mbMain;
	Menu mnuFile, mnuHelp;
	MenuItem mitemShowDialog, mitemQuit, mitemHelp, mitemAbout;
	TextArea txtMain;
	
	public B_DialogSimple() {
		
		//Use the constructor to instantiate components:
		mbMain = new MenuBar();
		
		mnuFile = new Menu("File");
		mnuHelp = new Menu("Help");
		
		mitemShowDialog = new MenuItem("Show Dialoge");
		mitemQuit = new MenuItem("Quit");
		mitemHelp = new MenuItem("Help"); 
		mitemAbout = new MenuItem("About"); 
		txtMain = new TextArea();
		
		
	}//constructor()
	
	
	
	@Override 
	public void init() {
		//Event handling:
		
		//Event handling for the 'Quit' menu item.
		mitemQuit.setOnAction(ae -> Platform.exit());
		
		//Event handling for the 'Show Dialog' menu item.
		mitemShowDialog.setOnAction(ae -> showXDialog());
		
		mitemAbout.setOnAction(ae -> showDialog());
		
		
	}//init()

	
	
	public void showXDialog() {
		//Create a secondary stage:
		Stage dialogStage = new Stage();
		
		//Set title:
		dialogStage.setTitle("Enetr Information");
		dialogStage.setWidth(400);
		dialogStage.setHeight(500);
		
		//Create a layout for the dialog.
		GridPane gpDialog = new GridPane();
		
		//Set the attributes of the gridpane.
		gpDialog.setPadding(new Insets(10));
		gpDialog.setHgap(10);
		gpDialog.setVgap(10);
		
		//Create controls/components for the dialog layout:

		Label lblName = new Label("First Name: ");
		TextField txtfName = new TextField();
		
		Label lblLastName = new Label("Last Name: ");
		TextField txtfLastName = new TextField();

		Label lblAddr1 = new Label("Address 1 :");
		TextField txtfAddr1 = new TextField();

		Label lblAddr2 = new Label("Address 2 :");
		TextField txtfAddr2 = new TextField();

		Label lblAddr3 = new Label("Address 3 :");
		TextField txtfAddr3 = new TextField();
		
		Button btnOk = new Button ("Ok");
		Button btnClose = new Button ("Close");
		
		//Add components to the layout:
		gpDialog.add(lblName, 0, 0);
		gpDialog.add(txtfName, 1, 0);
		gpDialog.add(lblLastName, 0, 1);
		gpDialog.add(txtfLastName, 1, 1);
		gpDialog.add(lblAddr1, 0, 2);
		gpDialog.add(txtfAddr1, 1, 2);
		gpDialog.add(lblAddr2, 0, 3);
		gpDialog.add(txtfAddr2, 1, 3);
		gpDialog.add(lblAddr3, 0, 4);
		gpDialog.add(txtfAddr3, 1, 4);
		gpDialog.add(btnClose, 0, 5);
		gpDialog.add(btnOk, 1, 5);
		
		//Size the buttons.
		btnOk.setMinWidth(60);
		btnClose.setMinWidth(60);
		
		//Event Handling for button Close:
		btnClose.setOnAction(ae -> dialogStage.close());
		
		//Event Handling for button Ok:
		btnOk.setOnAction(ae -> {
			//Local variable to temporarily store name. Get the first name.
			String name  = txtfName.getText();
			
			//Get the last name.
			String lastName = txtfLastName.getText();
						
			//Concatenate the name strings. Show the name on the main user interface.
			//txtMain.setText(name);
			
			txtMain.appendText(name + " " + lastName +  "\n");
			
			//Append the address. This time simply get the text from the textfields.
			//No local variables created for this example.
			txtMain.appendText(txtfAddr1.getText() + "\n"
					+ txtfAddr2.getText() + "\n"
					+ txtfAddr3.getText() + "\n\n");
			
			
			//Now just close the dialog.
			dialogStage.close();
		});
		
		
		//Create a scene:
		Scene s = new Scene(gpDialog);
		
		//Set the scene:
		dialogStage.setScene(s);
		
		//Show the stage:
		dialogStage.show();
		
	}

	public void showDialog() {
		
		//Creating an alert:
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Call me for more information");
		alert.setContentText("Copy right 2021");
		
		//Optional: Set a custom image into the alert.
		//Remember to do the Image and ImageView imports.
		Image img = new Image("./124.jpg");
		ImageView imView = new ImageView(img);
		alert.setGraphic(imView);
		
		//Show alert:
		alert.showAndWait();		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Construct a layout and create an interface:
		
		//Set the title:
		primaryStage.setTitle("DialogSimple");
		
		primaryStage.getIcons().add(new Image("124.jpg"));

		
		//Set the width and height:
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);
		
		//Create Layout:
		BorderPane bpmain = new BorderPane();
		
		//Add Menu Items to Menus:
		mnuFile.getItems().add(mitemShowDialog);
		mnuFile.getItems().add(mitemQuit);
		
		mnuHelp.getItems().add(mitemAbout);
		mnuHelp.getItems().add(mitemHelp);
		
		//Add Menus to Menu bar:
		mbMain.getMenus().add(mnuFile);
		mbMain.getMenus().add(mnuHelp);
		
		//Add Menu bar:
		bpmain.setTop(mbMain);
		
		//Add component to the layout:
		bpmain.setCenter(txtMain);

		//Create a scene:
		Scene s = new Scene(bpmain);
		
		//Set the scene:
		primaryStage.setScene(s);
		
		//Show the stage:
		primaryStage.show();

	}

	
	public static void main(String[] args) {
		//Launch the application.
		launch();
	}

}
