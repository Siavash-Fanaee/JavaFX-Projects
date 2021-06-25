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

public class H_InterestCalculator extends Application {
	
	Label lblCapital, lblInterestRate, lblInvTerm;
	TextField txtfCapital, txtfInterestRate, txtfInvTerm;
	Button btnDialog, btnQuit, btnCalculate;
	CheckBox chkSimple, chkCompound;
	TextArea txtMain;
	
	
	public H_InterestCalculator() {
		
		lblCapital = new Label("Capital:");
		lblInterestRate = new Label("Interset Rate:");
		lblInvTerm = new Label("Investment Term (yrs):");
		
		txtfCapital = new TextField(); 
		txtfInterestRate = new TextField();
		txtfInvTerm = new TextField();
		
		btnDialog = new Button("...");
		btnQuit = new Button("Quit");
		btnCalculate = new Button("Calculate");
		
		chkSimple = new CheckBox("Simple interest");
		chkCompound = new CheckBox("Compound interest");
		
		txtMain = new TextArea();
		
		
		btnQuit.setMinWidth(80);
		btnCalculate.setMinWidth(80);
		
		
	}
	
	@Override
	public void init() {
		
		btnQuit.setOnAction(ae -> Platform.exit());
				
		btnDialog.setOnAction(ae -> showTermDialog());
		
		btnCalculate.setOnAction(ae -> showInterestAnalysis ());
		
	}
	

	private void showInterestAnalysis() {
		double capital = 0;
		double intRate = 0;
		double years = 0;
		
		try {
			capital = Double.parseDouble(txtfCapital.getText());
			intRate = Double.parseDouble(txtfInterestRate.getText());
			years = Double.parseDouble(txtfInvTerm.getText());
		}
		catch (NumberFormatException nfe) {
			System.err.print("No data entered." + nfe.toString());
		}
		
		
		if (chkSimple.isSelected()) {
			showSimpleInterest(capital, intRate, years);
		}
		
		if (chkCompound.isSelected()) {
			showCompoundInterest(capital, intRate, years);
		}
	}


	private void showSimpleInterest(double cap, double iRate, double yrs) {
		Double simpleInterest = cap * (iRate/100) * yrs;
		txtMain.setText(simpleInterest.toString());
	}
	
	private void showCompoundInterest(double cap, double iRate, double yrs) {
		
	}



	private void showTermDialog() {
		
		Label lblStart = new Label("Investment Start Date"); 
		Label lblEnd = new Label("Investment End Date");
		DatePicker dpStart = new DatePicker(); 
		DatePicker dpEnd = new DatePicker();
		dpStart.setValue(LocalDate.now());
		dpEnd.setValue(LocalDate.now());
		Button btnCancel = new Button("Cancel");
		Button btnOk = new Button("Ok");
		btnCancel.setMinWidth(60);
		btnOk.setMinWidth(60);
		
		Stage termDialogStage = new Stage();
		termDialogStage.setTitle("Select Investment Term");
		termDialogStage.setWidth(380);
		termDialogStage.setHeight(180);
		
		VBox vbDialogMain = new VBox();
		vbDialogMain.setPadding(new Insets(10));
		vbDialogMain.setSpacing(10);
		
		GridPane gpDialog = new GridPane();
		gpDialog.setPadding(new Insets(10));
		gpDialog.setVgap(10);
		gpDialog.setHgap(10);
		
		gpDialog.add(lblStart, 0, 0);
		gpDialog.add(lblEnd, 0, 1);
		gpDialog.add(dpStart, 1, 0);
		gpDialog.add(dpEnd, 1, 1);
		
		HBox hbDialogBtns = new HBox();
		hbDialogBtns.setSpacing(10);
		hbDialogBtns.getChildren().addAll(btnCancel , btnOk);
		hbDialogBtns.setAlignment(Pos.BASELINE_RIGHT);
		
		vbDialogMain.getChildren().addAll(gpDialog , hbDialogBtns);
		
		Scene s = new Scene(vbDialogMain);
		termDialogStage.setScene(s);
		s.getStylesheets().add("style.css");
		termDialogStage.show();
		
		//Event Handling for Date picker Dialog box:
		btnCancel.setOnAction(ae -> termDialogStage.close());
		
		btnOk.setOnAction(ae -> {
			LocalDate startDate = dpStart.getValue();
			LocalDate endDate = dpEnd.getValue();
			
			if (endDate.isAfter(startDate)) {
				//Get the time elapsed in years between the two dates.
				long years = ChronoUnit.YEARS.between(startDate, endDate);
				
				//Set the interval in years back into the main UI textfield.
				txtfInvTerm.setText(years + "");
				
				//Now, just close the dialog.
				termDialogStage.close();
				
			}
			else {
				Alert alertDate = new Alert(AlertType.ERROR);
				alertDate.setTitle("Date Entry Error");
				alertDate.setHeaderText("Inalid Data Entry");
				alertDate.setContentText("The end date occures before start date!");
			
				alertDate.showAndWait();
			}
		});
		
		
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Interest Calculator 2021");
		primaryStage.setWidth(450);
		primaryStage.setHeight(300);
		
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(10));
		vbMain.setSpacing(10);
		
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		
		vbMain.getChildren().add(gp);
		
		gp.add(lblCapital, 0, 0);
		gp.add(lblInterestRate, 0, 1);
		gp.add(lblInvTerm, 0, 2);
		
		gp.add(txtfCapital, 1, 0);
		gp.add(txtfInterestRate, 1, 1);
		gp.add(txtfInvTerm, 1, 2);
		gp.add(btnDialog, 2, 2);
		gp.add(chkSimple, 1, 3);		
		gp.add(chkCompound, 1, 4);
		
		vbMain.getChildren().add(txtMain);
		
		HBox hbButtons = new HBox ();
		hbButtons.setSpacing(10);
		
		hbButtons.getChildren().addAll(btnQuit , btnCalculate);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		vbMain.getChildren().add(hbButtons);
		
		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style.css");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();

	}

}
