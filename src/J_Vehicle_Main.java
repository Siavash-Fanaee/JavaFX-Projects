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

public class J_Vehicle_Main extends Application {
	
	J_Vehicle v;
	
	Label lblType , lblMake, lblModel, lblColor, lblReg;
	TextField txtfMake, txtfModel, txtfReg;
	TextArea txtMain;
	Button btnCreate , btnShow, btnQuit;
	RadioButton rbTrauck, rbCar;
	ToggleGroup tg;
	ComboBox cb;
	
	public J_Vehicle_Main() {
		
		lblType = new Label("Type:");
		lblMake = new Label("Make:");
		lblModel = new Label("Model:");
		lblColor = new Label("Color:");
		lblReg = new Label("Reg Number:");
		
		txtfMake = new TextField();
		txtfModel = new TextField();
		txtfReg = new TextField();
		
		txtMain = new TextArea();
		
		btnCreate = new Button("Create");
		btnShow = new Button("Show Details"); 
		btnQuit = new Button("Quit");
		
		rbTrauck = new RadioButton("Truck");
		rbCar = new RadioButton("Car");
		
		tg = new ToggleGroup();
		rbTrauck.setToggleGroup(tg);
		rbCar.setToggleGroup(tg);
		rbCar.setSelected(true);
		
		cb = new ComboBox();
		cb.getItems().addAll("Blue", "red", "Green", "Black" , "White");
		cb.getSelectionModel().select(0);
		
	}
	
	@Override
	public void init() {
		btnQuit.setOnAction(ae -> Platform.exit());
		
		btnCreate.setOnAction(ae -> createInstance());
	}
	
	private void createInstance() {
		
		String registerNumber = txtfReg.getText();
		
		if (registerNumber==null) {
			txtMain.setText("Reg number must be filled!");
			
		}
		
		else {
			if (rbTrauck.isSelected()) {
				
				v = new J_Truck(registerNumber);
				v.setColer(cb.getSelectionModel().getSelectedItem().toString());			
				v.setMake(txtfMake.getText());
				v.setModel(txtfModel.getText());
				
				System.out.println("Truck created.");

			}
			
			if (rbCar.isSelected()) {
				
				v = new J_Car(registerNumber);
				v.setColer(cb.getSelectionModel().getSelectedItem().toString());			
				v.setMake(txtfMake.getText());
				v.setModel(txtfModel.getText());
				
				System.out.println("Car created.");

			}
			
			else {
				
			}
			
			txtMain.appendText("Car Detail:\n"+ v.getRegNum() + "\n"+v.getMake()+"\n"
					+ v.getModel() +"\n"+ v.getColer());

		}
		
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Vehicle");
		primaryStage.setWidth(400);
		primaryStage.setHeight(500);
		
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		vbMain.setSpacing(10);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setVgap(10);
		gpMain.setHgap(10);
				
		HBox hbRadio = new HBox();
		hbRadio.setSpacing(10);
		hbRadio.getChildren().addAll(rbTrauck , rbCar);
		hbRadio.setAlignment(Pos.BASELINE_CENTER);
				
		gpMain.add(lblType, 0, 0);
		gpMain.add(hbRadio, 1, 0);
		gpMain.add(lblMake, 0, 1);
		gpMain.add(txtfMake, 1, 1);
		gpMain.add(lblModel, 0, 2);
		gpMain.add(txtfModel, 1, 2);
		gpMain.add(lblColor, 0, 3);
		gpMain.add(cb, 1, 3);
		gpMain.add(lblReg, 0, 4);
		gpMain.add(txtfReg, 1, 4);
		gpMain.add(btnCreate, 0, 5);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnShow , btnQuit);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		vbMain.getChildren().addAll(gpMain, txtMain, hbButtons);

		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();

	}

}
