//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
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

public class ZZ_FX_Eng extends Application {
	
	MenuBar mnuBar;
	Menu mnuFile;
	MenuItem miHelp, miAbout, miQuit;
	
	Label lblSerial, lblName, lblDesc;
	TextField txtfSerial, txtfName, txtfDesc;
	
	Button btOk, btnCancel;
	

	public ZZ_FX_Eng() {
		
		mnuBar = new MenuBar();
		mnuFile = new Menu("File");
		miAbout = new MenuItem("About");
		miHelp = new MenuItem("Help");
		miQuit = new MenuItem("Quit");
		
		lblSerial = new Label("Serial Number:");
		lblName = new Label("Name:");
		lblDesc = new Label("Description:");
		
		txtfSerial = new TextField();
		txtfName = new TextField();
		txtfDesc = new TextField();
		
		
		mnuFile.getItems().addAll(miAbout, miHelp, miQuit);
		mnuBar.getMenus().add(mnuFile);
		
		
		btOk = new Button("Ok");
		btnCancel = new Button("Cancel");
		btOk.setMinWidth(60);
		btnCancel.setMinWidth(60);

	}
	
	@Override
	public void init() {
		
		miQuit.setOnAction(ae -> Platform.exit());
		
		miAbout.setOnAction(ae -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About me");
			alert.setHeaderText("Seyedsiavash Fanaee - 3019365");
			alert.setContentText("Griffith College Dublin. 2021");
			
			ImageView imgView = new ImageView(new Image("siavash.jpg"));
			alert.setGraphic(imgView);
			
			alert.showAndWait();
		});
		
		miHelp.setOnAction(ae -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Help");
			alert.setHeaderText("This is a simple Eng App!");
			alert.setContentText("For more info please send an email to: Siavashf84@gmail.com");
			
			ImageView imgView = new ImageView(new Image("help.jpg"));
			alert.setGraphic(imgView);
			
			alert.showAndWait();
			
		});
		
		btnCancel.setOnAction(ae -> {
			
			txtfSerial.clear();
			txtfName.clear();
			txtfDesc.clear();
			
		});
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FX_Eng - Seyedsiavash Fanaee");
		primaryStage.setWidth(350);
		primaryStage.setHeight(300);
		
		BorderPane bpMain = new BorderPane();
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setVgap(10);
		gp.setHgap(10);
		
		gp.add(lblSerial, 0, 0);
		gp.add(txtfSerial, 1, 0);
		gp.add(lblName, 0, 1);
		gp.add(txtfName, 1, 1);
		gp.add(lblDesc, 0, 2);
		gp.add(txtfDesc, 1, 2);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		hbButtons.getChildren().addAll(btOk , btnCancel);
		gp.add(hbButtons, 1, 3);
			
		bpMain.setTop(mnuBar);
		bpMain.setCenter(gp);
		
		Scene s = new Scene(bpMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style1.css");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
