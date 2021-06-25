
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
import javafx.geometry.HPos;
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
import java.io.FileNotFoundException;

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

public class K_Mail extends Application {

	Label lblFrom, lblSubject, lblTo;
	TextField txtfFrom, txtfSubject, txtfTo;
	TextArea txtMain;
	Button btnRec, btnQuit, btnSend;

	public K_Mail() {

		lblFrom = new Label("From: ");
		lblSubject = new Label("Subject: ");
		lblTo = new Label("To: ");

		txtfFrom = new TextField();
		txtfSubject = new TextField();
		txtfTo = new TextField();

		txtMain = new TextArea();

		btnRec = new Button("...");
		btnQuit = new Button("Quit");
		btnSend = new Button("Send");

		btnQuit.setMinWidth(60);
		btnSend.setMinWidth(60);

	}

	@Override
	public void init() {

		btnQuit.setOnAction(ae -> Platform.exit());

		btnRec.setOnAction(ae -> RecieveHandler());
	}

	private void RecieveHandler() {

		Label lblName = new Label("Names: ");
		Label lblEmail = new Label("Email Address: ");
		ListView lvName = new ListView();
		lvName.setMaxHeight(100);
		lvName.setMaxWidth(150);
		TextField txtfEmail = new TextField();
		Button btnOk = new Button("Ok");
		Button btnCancel = new Button("Cancel");
		btnOk.setMinWidth(60);
		btnCancel.setMinWidth(60);

		Stage stage = new Stage();
		stage.setTitle("Choose the name to send your Email:");
		stage.setWidth(380);
		stage.setHeight(280);

		GridPane gpDialog = new GridPane();
		gpDialog.setPadding(new Insets(10));
		gpDialog.setHgap(10);
		gpDialog.setVgap(10);

		gpDialog.add(lblName, 0, 0);
		gpDialog.add(lvName, 0, 1);
		gpDialog.add(lblEmail, 0, 2);
		gpDialog.add(txtfEmail, 0, 3);

		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnOk, btnCancel);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);

		gpDialog.add(hbButtons, 0, 4);

		Scene s = new Scene(gpDialog);
		stage.setScene(s);
		stage.show();

		// Reading From addresses.csv file:
		String line;
		File file = new File("./addresses1.csv");
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader buf = new BufferedReader(fr);

		try {
			while ((line = buf.readLine()) != null) {

				String[] contactDataArray = new String[3];

				contactDataArray = line.split(":");

				lvName.getItems().add(contactDataArray[0]);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		lvName.setOnMousePressed(ae -> {

			// Reading From addresses.csv file:
			String line1 = null;
			File file1 = new File("./addresses1.csv");
			FileReader fr1 = null;
			try {
				fr1 = new FileReader(file1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedReader buf1 = new BufferedReader(fr1);

			try {
					String[] contactDataArray = new String[3];

					contactDataArray = line1.split(":");

					txtfEmail.setText(contactDataArray[1]);

				}
			catch (Exception e) {
				e.printStackTrace();
			}

			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}



		});

		btnCancel.setOnAction(ae -> stage.close());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Mail V.1.0");
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		primaryStage.getIcons().add(new Image("envelope.jpg"));

		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		vbMain.setSpacing(10);

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(lblFrom, 0, 0);
		gp.add(txtfFrom, 1, 0);
		gp.add(lblSubject, 0, 1);
		gp.add(txtfSubject, 1, 1);
		gp.add(lblTo, 0, 2);
		gp.add(txtfTo, 1, 2);
		gp.add(btnRec, 2, 2);

		HBox hbButtons = new HBox();
		hbButtons.setPadding(new Insets(10));
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnQuit, btnSend);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);

		vbMain.getChildren().addAll(gp, txtMain, hbButtons);

		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style.css");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
