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
public class Z_ElectroCalc extends Application {
	
	Label lblPrevious, lblCurrent, lblCost, lblCostPrint , lblVat , lblVatPrint, lblTotal , lblTotalPrint;
	TextField txtfPrevious, txtfCurrent;
	Button btnSetting, btnClose, btnCalculate;
	

	public Z_ElectroCalc() {
		
		lblPrevious = new Label ("Previous Reading:");
		lblCurrent = new Label ("Current Reading:");
		lblCost = new Label ("Cost before VAT:");
		lblCostPrint = new Label ();
		lblVat = new Label ("VAT:");
		lblVatPrint = new Label ();
		lblTotal = new Label ("Total:");
		lblTotalPrint = new Label ();
		
		txtfPrevious = new TextField();
		txtfCurrent = new TextField();
		
		btnSetting = new Button("Setting");
		btnClose = new Button("Close");
		btnCalculate = new Button("Calculate");
		btnSetting.setMinWidth(70);
		btnClose.setMinWidth(70);
		btnCalculate.setMinWidth(70);
	}//Constructor()

	@Override
	public void init() {
		
		btnClose.setOnAction(ae -> Platform.exit());
		
		btnSetting.setOnAction(ae -> settinbHandler());
		
		btnCalculate.setOnAction(ae -> CalculateHandler());
		
		
	}
	
	private void CalculateHandler() {
		double costbeforeVAT;
		double vat;
		double total;
		
		try {
			if (txtfCurrent.getText()!= null && txtfPrevious.getText()!= null && (Integer.parseInt(txtfCurrent.getText()) > (Integer.parseInt(txtfPrevious.getText())))) {
				costbeforeVAT = (Integer.parseInt(txtfCurrent.getText()) - (Integer.parseInt(txtfPrevious.getText())))*0.1814 + 27.06;
				vat = costbeforeVAT * 0.135;
				total = costbeforeVAT + vat;
								
				lblCostPrint.setText(Double.toString(costbeforeVAT) + " €");
				lblVatPrint.setText(Double.toString(vat) + " €");
				lblTotalPrint.setText(Double.toString(total) + " €");
								
			}

			
		}
		catch (Exception e) {
			System.out.println("Inappropriate Entry!");
			
		}
		
		

		
		
		

		
	}
	
	private void settinbHandler() {
		
		Label lblUnit = new Label ("Cost per unit:");
		Label lblCharge = new Label ("Standing charge:");
		Label lblVATrate = new Label ("Value Added Tax rate:");
		
		TextField txtfUnit = new TextField();
		TextField txtfCharge = new TextField();
		TextField txtfVATrate = new TextField();
		
		Button btnOk = new Button("Ok");
		Button btnCancel = new Button("Cancel");
		btnOk.setMinWidth(60);
		btnCancel.setMinWidth(60);
		
		Stage settingStage = new Stage();
		settingStage.setTitle("Set New Information");
		settingStage.setWidth(380);
		settingStage.setHeight(320);
		
		GridPane gpSetting = new GridPane();
		gpSetting.setPadding(new Insets(10));
		gpSetting.setVgap(10);
		gpSetting.setHgap(10);
		
		gpSetting.add(lblUnit, 0, 0);
		gpSetting.add(txtfUnit, 1, 0);
		gpSetting.add(lblCharge, 0, 1);
		gpSetting.add(txtfCharge, 1, 1);
		gpSetting.add(lblVATrate, 0, 2);
		gpSetting.add(txtfVATrate, 1, 2);

		
		HBox hbButton = new HBox();
		hbButton.setSpacing(10);
		hbButton.getChildren().addAll(btnOk , btnCancel);
		hbButton.setAlignment(Pos.BASELINE_RIGHT);
		
		gpSetting.add(hbButton, 1, 5);
		
		
		Scene s = new Scene(gpSetting);
		settingStage.setScene(s);
		settingStage.show();
		
		btnCancel.setOnAction(ae -> settingStage.close());
		btnOk.setOnAction(ae -> {
			
			
		});
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("ElectroCalc V.1.0.0");
		primaryStage.setWidth(400);
		primaryStage.setHeight(350);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setVgap(10);
		gpMain.setHgap(10);
		
		gpMain.add(lblPrevious, 0, 0);
		gpMain.add(txtfPrevious, 1, 0);
		gpMain.add(lblCurrent, 0, 1);
		gpMain.add(txtfCurrent, 1, 1);
		gpMain.add(lblCost, 0, 2);
		gpMain.add(lblCostPrint, 1, 2);
		gpMain.add(lblVat, 0, 3);
		gpMain.add(lblVatPrint, 1, 3);
		gpMain.add(lblTotal, 0, 4);
		gpMain.add(lblTotalPrint, 1, 4);
		
		HBox hbButton = new HBox();
		hbButton.setSpacing(10);
		hbButton.getChildren().addAll(btnSetting , btnClose , btnCalculate);
		hbButton.setAlignment(Pos.BASELINE_RIGHT);
		
		gpMain.add(hbButton, 1, 5);
		
		
		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}

}
