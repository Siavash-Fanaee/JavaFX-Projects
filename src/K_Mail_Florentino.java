//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Platform;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Components for layout.
import javafx.scene.layout.GridPane;

//Insetting and alignment.
import javafx.geometry.Insets;

import javafx.geometry.Pos;



public class K_Mail_Florentino extends Application {
	// Variables

	Label lblFrom, lblSubject, lblTo;
	TextField txtfFrom, txtfSubject, txtfTo;
	TextArea txtMessage;
	Button btnTo, btnQuit, btnSend;

	// Constructor
	public K_Mail_Florentino() {
		// Initialize Variables
		lblFrom = new Label("From:");
		lblSubject = new Label("Subject:");
		lblTo = new Label("To:");

		txtfFrom = new TextField("Seyedsiavash@Fanaee.com");
		txtfFrom.setMinWidth(160);
		txtfSubject = new TextField();
		txtfTo = new TextField();

		txtMessage = new TextArea();

		btnTo = new Button("...");
		btnQuit = new Button("Quit");
		btnSend = new Button("Send");
		btnTo.setMinWidth(40);
		btnQuit.setMinWidth(60);
		btnSend.setMinWidth(60);
	}



	@Override
	public void init() throws Exception {

		btnQuit.setOnAction(ae -> Platform.exit());

		btnTo.setOnAction(ae -> {
			try {showEmailList();} 	
			catch (IOException e) {e.printStackTrace();}
		});
	}

	private void showEmailList() throws IOException {
		
		Stage stageDialog = new Stage();
		stageDialog.setTitle("Addresses");

		stageDialog.setMinWidth(300);
		stageDialog.setMinHeight(250);

		Button btnCancel = new Button("Cancel");
		Button btnAdd = new Button("Add");
		btnAdd.setDisable(true);
		btnCancel.setMinWidth(60);
		btnAdd.setMinWidth(60);
		

		VBox vbDialogMain = new VBox();
		vbDialogMain.setPadding(new Insets(10));
		vbDialogMain.setSpacing(10);

		ObservableList<String> contacts = readFromFile();
		ListView<String> contactList = new ListView<String>(contacts);

		contactList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
				btnAdd.setDisable(false);
			}
		});

		HBox hbDialogBtns = new HBox();
		hbDialogBtns.setSpacing(10);
		hbDialogBtns.setAlignment(Pos.BASELINE_RIGHT);
		hbDialogBtns.getChildren().addAll(btnAdd , btnCancel);

		vbDialogMain.getChildren().addAll(contactList, hbDialogBtns);

		//Event handling for Dialog buttons(Add - Cancel):
		btnCancel.setOnAction(ae -> stageDialog.close());

		btnAdd.setOnAction(ae -> {
			txtfTo.setText(contactList.getSelectionModel().getSelectedItem().split(":", -1)[1]);
			stageDialog.close();
		});

		Scene s = new Scene(vbDialogMain);
		stageDialog.setScene(s);
		stageDialog.show();
	}

	private ObservableList<String> readFromFile() throws IOException {

		BufferedReader br;
		ObservableList<String> emails = FXCollections.observableArrayList();

		try {
			br = new BufferedReader(new FileReader("./addresses1.csv"));

			String line;
			while ((line = br.readLine()) != null) {
				emails.addAll(line);
			}

		} catch (FileNotFoundException ex) {
			ex.getMessage();
		}

		return emails;

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("EMail_FX v1.0.0");
		primaryStage.setWidth(550);
		primaryStage.setMinHeight(320);
		primaryStage.getIcons().add(new Image("envelope.jpg"));

		VBox vbMain = new VBox();
		vbMain.setSpacing(10);
		vbMain.setPadding(new Insets(15));

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		gp.add(lblFrom, 0, 0);
		gp.add(lblSubject, 0, 1);
		gp.add(lblTo, 0, 2);

		gp.add(txtfFrom, 1, 0);
		gp.add(txtfSubject, 1, 1);
		gp.add(txtfTo, 1, 2);

		gp.add(btnTo, 2, 2);

		HBox btns = new HBox();
		btns.setSpacing(10);
		btns.setAlignment(Pos.BASELINE_RIGHT);
		btns.getChildren().addAll(btnQuit, btnSend);

		// set everything into the vbMain
		vbMain.getChildren().addAll(gp, txtMessage, btns);
		
		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
