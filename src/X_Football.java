
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
import javafx.scene.control.SelectionModel;
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

public class X_Football extends Application {

	X_Player p;

	Label lblType, lblID, lblNumber, lblName;

	TextField txtfID, txtfName;

	TextArea txtDetails;

	ComboBox cmbNumber;

	Button btnCreate, btnShowDetails, btnQuit;

	RadioButton rdoGoalkeeper, rdoStriker, rdoDefender;

	// The radio buttons belong to a toggle group. Mutually exclusive.
	ToggleGroup tgGroup;

	public X_Football() {

		lblType = new Label("Player Type:");
		lblID = new Label("ID:");
		lblNumber = new Label("Number:");
		lblName = new Label("Name:");

		txtfID = new TextField();
		txtfName = new TextField();

		txtDetails = new TextArea();

		cmbNumber = new ComboBox();
		cmbNumber.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		cmbNumber.getSelectionModel().select(0);

		btnCreate = new Button("Create");
		btnShowDetails = new Button("Show Detail");
		btnQuit = new Button("Quit");
		btnShowDetails.setMinWidth(70);
		btnQuit.setMinWidth(70);

		rdoGoalkeeper = new RadioButton("Goal keeper");
		rdoStriker = new RadioButton("Striker");
		rdoDefender = new RadioButton("Defender");

		tgGroup = new ToggleGroup();

		rdoGoalkeeper.setToggleGroup(tgGroup);
		rdoStriker.setToggleGroup(tgGroup);
		rdoDefender.setToggleGroup(tgGroup);

		rdoDefender.setSelected(true);
	}

	@Override
	public void init() {
		// Event handling.

		// Support quitting by actioning the Quit button.
		btnQuit.setOnAction(ae -> Platform.exit());

		btnCreate.setOnAction(ae -> createPlayerType());

		btnShowDetails.setOnAction(ae -> showDetails());

	}// init()

	private void showDetails() {
		txtDetails.appendText("Player Details: \n" + p.toStringDescr());

	}

	private void createPlayerType() {

		// Check what type of player should be created.

		// Player p already declared at class scope.
		int id;
		id = Integer.parseInt(txtfID.getText());

		if (rdoGoalkeeper.isSelected()) {

			p = new X_GoalKeeper(id);
			p.setName(txtfName.getText());
			p.setType("Goalkeeper");
			p.setNumber(1);
			System.out.println("Goalkeeper created.");

		} else if (rdoStriker.isSelected()) {
			p = new X_Striker(id);
			p.setName(txtfName.getText());
			p.setType("Striker");
			p.setNumber((int) cmbNumber.getSelectionModel().getSelectedItem());

			System.out.println("Striker created.");

		} else if (rdoDefender.isSelected()) {
			// Create a new defender type.
			p = new X_Defender(id);
			p.setName(txtfName.getText());
			p.setType("Defender");
			p.setNumber((int) cmbNumber.getSelectionModel().getSelectedItem());

			System.out.println("Defender created.");

		} else {
			// Player type is invalid.

		} // final else

		// Player of the type required now exists.

	}// createPlayerType()

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Football V.1.0.0");
		primaryStage.getIcons().add(new Image("Football.jpg"));
		primaryStage.setWidth(400);
		primaryStage.setHeight(400);

		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		vbMain.setSpacing(10);

		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setVgap(10);
		gpMain.setHgap(10);

		HBox hbRadio = new HBox();
		hbRadio.setSpacing(10);
		hbRadio.getChildren().addAll(rdoGoalkeeper, rdoStriker, rdoDefender);
		hbRadio.setAlignment(Pos.BASELINE_CENTER);

		gpMain.add(lblType, 0, 0);
		gpMain.add(lblID, 0, 1);
		gpMain.add(lblNumber, 0, 2);
		gpMain.add(lblName, 0, 3);

		gpMain.add(hbRadio, 1, 0);
		gpMain.add(txtfID, 1, 1);
		gpMain.add(txtfName, 1, 3);
		gpMain.add(cmbNumber, 1, 2);

		gpMain.add(btnCreate, 0, 4);
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnShowDetails, btnQuit);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);

		vbMain.getChildren().addAll(gpMain, txtDetails, hbButtons);

		Scene s = new Scene(vbMain);
		s.getStylesheets().add("style.css");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();

	}

}
