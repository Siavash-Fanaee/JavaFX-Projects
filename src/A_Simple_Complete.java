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

public class A_Simple_Complete extends Application {
	
	//Declare components that require class scope:

	Label lblName, lblMsg , lblMsgPrint;
	TextField txtfName;
	Button btnOk, btnClose;

	public A_Simple_Complete() {
		
		//Use the constructor to instantiate components:

		lblName = new Label("Enter Name: ");
		lblMsg = new Label("Message: ");
		lblMsgPrint = new Label();
		txtfName = new TextField();
		
		btnOk = new Button("Ok");
		btnClose = new Button("Close");
		btnOk.setMinWidth(60);
		btnClose.setMinWidth(60);
	}
	
	@Override
	public void init() {
		
		//Event handling:

		btnClose.setOnAction(ae -> Platform.exit());
		
		btnOk.setOnAction(ae -> {
			lblMsgPrint.setText("Welcome " + txtfName.getText());
		});
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Simple Application. v.1.0.0");
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		primaryStage.getIcons().add(new Image("Siavash.jpg"));
		
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.add(lblName, 0, 0);
		gp.add(txtfName, 1, 0);
		gp.add(lblMsg, 0, 1);
		gp.add(lblMsgPrint, 1, 1);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnOk , btnClose);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		vbMain.getChildren().add(gp);
		vbMain.getChildren().add(hbButtons);
		
		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		//Launch the application in the main method.
		launch();

	}

}
