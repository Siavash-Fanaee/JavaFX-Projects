
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
import java.io.FileOutputStream;

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

public class FX_Editor extends Application {

	// Variables & components that need class scope.
	MenuBar mbar;
	Menu mnuFile, mnuEdit, mnuHelp;
	MenuItem miNew, miOpen, miSave, miSaveAs, miClose, miQuit;
	MenuItem miUndo, miRedo, miCut, miCopy, miPaste, miDelet, miSelectAll;
	MenuItem miContents, miAboutUs;
	TextArea txtMain;

	public FX_Editor() {

		// Instantiate components in the Constructor:
		mbar = new MenuBar();

		mnuFile = new Menu("File");
		mnuEdit = new Menu("Edit");
		mnuHelp = new Menu("Help");

		miNew = new MenuItem("New");
		miOpen = new MenuItem("Open");
		miSave = new MenuItem("Save");
		miSaveAs = new MenuItem("Save as");
		miClose = new MenuItem("Close");
		miQuit = new MenuItem("Quit");

		miUndo = new MenuItem("Undo");
		miRedo = new MenuItem("Redo");
		miCut = new MenuItem("Cut");
		miCopy = new MenuItem("Copy");
		miPaste = new MenuItem("Paste");
		miDelet = new MenuItem("Delete");
		miSelectAll = new MenuItem("Select All");

		miContents = new MenuItem("Contents");
		miAboutUs = new MenuItem("About us");

		txtMain = new TextArea();

	}// constructor

	@Override
	public void init() {
		// Event Handling:

		// Event Handling for File Menu Items:
		miNew.setOnAction(ae -> {
			// Action for new (if text area is not empty ask client for save. Then text area
			// will be cleaned):
			if (txtMain.getText().trim().equals("")) {
				txtMain.clear();
			} else {
				saveAsHandler();
				txtMain.clear();
			}

		});
		miOpen.setOnAction(ae -> fileOpener());
		miSave.setOnAction(ae -> saveHandler());
		miSaveAs.setOnAction(ae -> saveAsHandler());

		miClose.setOnAction(ae -> Platform.exit());
		miQuit.setOnAction(ae -> Platform.exit());

		// Event Handling for Edit Menu Items:
		miUndo.setOnAction(ae -> txtMain.undo());
		miRedo.setOnAction(ae -> txtMain.redo());
		miDelet.setOnAction(ae -> txtMain.deleteNextChar());
		miCut.setOnAction(ae -> txtMain.cut());
		miCopy.setOnAction(ae -> txtMain.copy());
		miPaste.setOnAction(ae -> txtMain.paste());
		miSelectAll.setOnAction(ae -> txtMain.selectAll());

		// Event Handling for Help Menu Items:
		miAboutUs.setOnAction(ae -> showDialogeAbout());

	}// init()

	public void saveAsHandler() {
		// Use a file chooser:
		FileChooser fc = new FileChooser();

		// Show the file save dialogue:
		File fileToSave = fc.showSaveDialog(null);

		// Test to see if the dialogue is confirmed and there is a file to save:
		if (fileToSave != null) {
			// Try save the file using the name given:
			try {
				FileOutputStream fos = new FileOutputStream(fileToSave, true);

				String text = txtMain.getText();

				// The file text must be saved as bytes:
				byte[] dataOut = text.getBytes();

				// Now just write it to the file:
				fos.write(dataOut);

				// Flush the fos to the file. It might just be buffered:
				fos.flush();

				// Now just close it:
				fos.close();

			} catch (IOException ioe) {
				System.out.println("Error saving file.\n");
				ioe.printStackTrace();
			}
		} else
			; // Do nothing:
	}// saveAsHandler()

	public void saveHandler() {

	}// saveHandler()

	public void fileOpener() {
		// Create a file chooser to select a file:
		FileChooser fc = new FileChooser();

		// Create a File to hold showOpenDialog method of fc object:
		File fileToOpen = fc.showOpenDialog(null);

		if (fileToOpen != null) {

			// Try to open a file and show it in the main text area:
			try {
				// Accumulate lines from the file in string builder:
				StringBuilder sb = new StringBuilder();

				// Use a buffered reader to read from the file:
				FileReader fr = new FileReader(fileToOpen);
				BufferedReader buf = new BufferedReader(fr);

				// A string to store lines from the file temporarily:
				String text;

				// Iterate through the file reading one line at a time:
				while ((text = buf.readLine()) != null) {

					text = text + "\n";
					// Append the line of text to the string builder's accumulated text:
					sb.append(text);

				} // while()

				// Done iterating through the file. End of file reached. Add the entire text to
				// txtMainn:
				txtMain.setText(sb.toString());

				// Important. Close the file
				buf.close();

			} catch (IOException ioe) {
				System.out.println("Error opening file.");
				ioe.printStackTrace();
			}
		} else
			;// Dialogue cancelled. Do nothing.

	}// fileOpener()

	public void showDialogeAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		// Set title, Header and content text for alert box:
		alert.setTitle("About");
		alert.setHeaderText("Seyedsiavash Fanaee - 3019365");
		alert.setContentText("Copyright @2021 - Griffith College Dublin");

		// Set a custom image to an alert:
		Image img = new Image("Siavash.jpg");
		ImageView imView = new ImageView(img);
		alert.setGraphic(imView);

		// Show the alert:
		alert.showAndWait();

	}// showDialogeAbout()

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Set title:
		primaryStage.setTitle("FX Edior");

		// Set image Icon:
		primaryStage.getIcons().add(new Image("Siavash.jpg"));

		// Set size:
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);

		// Create layout:
		BorderPane bpMain = new BorderPane();

		// Add Menu Bar and Text Area to the Border Pane:
		bpMain.setTop(mbar);
		bpMain.setCenter(txtMain);

		// Add Menu Items to the Menus:
		mnuFile.getItems().add(miNew);
		mnuFile.getItems().add(miOpen);
		mnuFile.getItems().add(miSave);
		mnuFile.getItems().add(miSaveAs);
		mnuFile.getItems().add(miClose);
		mnuFile.getItems().add(miQuit);

		mnuEdit.getItems().add(miUndo);
		mnuEdit.getItems().add(miRedo);
		mnuEdit.getItems().add(miCut);
		mnuEdit.getItems().add(miCopy);
		mnuEdit.getItems().add(miPaste);
		mnuEdit.getItems().add(miDelet);
		mnuEdit.getItems().add(miSelectAll);

		mnuHelp.getItems().add(miContents);
		mnuHelp.getItems().add(miAboutUs);

		// Add Menus to Menu Bar:
		mbar.getMenus().add(mnuFile);
		mbar.getMenus().add(mnuEdit);
		mbar.getMenus().add(mnuHelp);

		// Create a scene
		Scene s = new Scene(bpMain);

		// Set the scene
		primaryStage.setScene(s);

		// Show the stage
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
