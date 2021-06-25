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


public class Single_Image_Viewer extends Application {
	
	//Components that require class scope.
	MenuBar mBar;
	Menu mnuFile;
	MenuItem miOpen, miQuit;
	Label lblPath;
	Image img;
	ImageView imvMain;
	
	public Single_Image_Viewer() {
		
		//Instantiate all components that are declared at class level.
		mBar = new MenuBar();
		mnuFile = new Menu("File");
		miOpen = new MenuItem("Open"); 
		miQuit= new MenuItem("Quit");
		imvMain = new ImageView();
		lblPath = new Label();


		//Add menu Items to the File Menu
		mnuFile.getItems().add(miOpen);
		mnuFile.getItems().add(miQuit);
		
		//Add File Menu to the Menu Bar:
		mBar.getMenus().add(mnuFile);
		
	}//constructor()
	
	
	@Override
	public void init() {
		//Event Handling:
		miOpen.setOnAction(ae -> openImage());
		miQuit.setOnAction(ae -> Platform.exit());
		
	}//init()

	
	public void openImage() {
		
		//Create a file chooser:
		FileChooser fc = new FileChooser();
		fc.setTitle("Select an IMAGE file: ");
		
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files" , "*.jpg" , "*.png"));
		
		File file;
		
		if ((file = fc.showOpenDialog(null)) != null) {
			
			img = new Image (file.toURI().toString());
			imvMain.setImage(img);
			
			lblPath.setText("Image Path: " + file.getPath());
		}
		else;
				
	}//openImage()
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Set title:
		primaryStage.setTitle("Image Viewer");
		
		//Set size:
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);
		
		//Create layout:
		BorderPane bpMain = new BorderPane();
		
		//Adding menu Bar and Image View to the layout:
		bpMain.setTop(mBar);
				
		HBox hbImageView = new HBox();
		hbImageView.getChildren().add(imvMain);		
		bpMain.setCenter(hbImageView);
				
		HBox hbPath = new HBox();
		hbPath.getChildren().add(lblPath);
		bpMain.setBottom(hbPath);
		
		//Create scene:
		Scene s = new Scene(bpMain);
		
		//Set scene:
		primaryStage.setScene(s);
		
		//Scale the image to match the stage size.
		imvMain.fitHeightProperty().bind(hbImageView.heightProperty());
		imvMain.fitWidthProperty().bind(hbImageView.widthProperty());
		
		//Show the stage:
		primaryStage.show();

	}

	public static void main(String[] args) {
		//Launch the application:
		launch();

	}//main()

}
