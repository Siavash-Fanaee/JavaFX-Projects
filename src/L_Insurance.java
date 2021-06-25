
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

public class L_Insurance extends Application {

	Label lblDetail, lblHome, lblHomeValue, lblOption, lblOptionShow, lblCost, lblCoatShow;
	Button btnInsuranceQuote;

	public L_Insurance() {

		lblDetail = new Label("Insurance Detail");
		lblDetail.setStyle("-fx-font-weight: bold;");
		
		lblHome = new Label("Home Value:");
		lblHomeValue = new Label();
		lblOption = new Label("Insurance Options Selected:");
		lblOptionShow = new Label();
		lblCost = new Label("Total Cost:");
		lblCoatShow = new Label();
		btnInsuranceQuote = new Button("Insurance Quote");
	}

	@Override
	public void init() {

		btnInsuranceQuote.setOnAction(ae -> ButtonHandler());

	}

	private void ButtonHandler() {

		Label lblValue = new Label("Home Value: ");
		Label lblOption = new Label("Optional Cover: ");

		TextField txtfValue = new TextField();

		CheckBox cbHomeEmerg = new CheckBox("Home emergency");
		CheckBox cbLegalExp = new CheckBox("Legal expenses");
		CheckBox cbPersonalInj = new CheckBox("Personal injury");

		Button btnOk = new Button("Ok");
		Button btnCancel = new Button("Cancel");
		btnOk.setMinWidth(60);
		btnOk.setMinWidth(60);

		Stage quoteStage = new Stage();
		quoteStage.setTitle("Insurance Quote:");
		quoteStage.setWidth(400);
		quoteStage.setHeight(300);

		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(15));
		gpMain.setVgap(10);
		gpMain.setHgap(10);

		gpMain.add(lblValue, 0, 0);
		gpMain.add(txtfValue, 1, 0);
		gpMain.add(lblOption, 0, 1);

		VBox vbCheckBox = new VBox();
		vbCheckBox.setSpacing(10);
		vbCheckBox.getChildren().addAll(cbHomeEmerg, cbLegalExp, cbPersonalInj);
		vbCheckBox.setAlignment(Pos.BASELINE_LEFT);
		gpMain.add(vbCheckBox, 1, 1);

		HBox hbButtons = new HBox();
		hbButtons.setPadding(new Insets(15));
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnOk, btnCancel);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		gpMain.add(hbButtons, 1, 2);

		Scene sc = new Scene(gpMain);
		quoteStage.setScene(sc);
		quoteStage.show();

		btnCancel.setOnAction(ae -> quoteStage.close());

		btnOk.setOnAction(ae -> {
			
			double TotalValue = Double.valueOf(txtfValue.getText().toString()) * 0.002;
			
			if (cbHomeEmerg.isSelected() && cbLegalExp.isSelected() && cbPersonalInj.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.15 + TotalValue * 0.1 + TotalValue * 0.1;
				lblOptionShow.setText("-Home emergency\n-Legal expenses\n-Personal injury");
			}
			else if (cbHomeEmerg.isSelected() && cbLegalExp.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.15 + TotalValue * 0.1;
				lblOptionShow.setText("-Home emergency\n-Legal expenses");
			}			
			else if (cbLegalExp.isSelected() && cbPersonalInj.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.1 + TotalValue * 0.1;
				lblOptionShow.setText("-Legal expenses\n-Personal injury");
			}
			else if (cbHomeEmerg.isSelected() && cbPersonalInj.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.15 + TotalValue * 0.1;
				lblOptionShow.setText("-Home emergency\n-Personal injury");
			}
			else if (cbHomeEmerg.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.15;
				lblOptionShow.setText("-Home emergency");
			}
			else if (cbLegalExp.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.1;
				lblOptionShow.setText("Legal expenses");
			}
			else if (cbPersonalInj.isSelected()) {
				TotalValue = TotalValue + TotalValue * 0.1;
				lblOptionShow.setText("Personal injury");
			}
			else {}
			
			lblCoatShow.setText(String.valueOf(TotalValue));
			lblHomeValue.setText(txtfValue.getText().toString());
			
			quoteStage.close();
			
		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("FX_Insurance v.0.0.1");
		primaryStage.setWidth(500);
		primaryStage.setHeight(400);

		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(20));
		gpMain.setVgap(20);
		gpMain.setHgap(20);

		gpMain.add(lblDetail, 0, 0);
		gpMain.add(lblHome, 0, 1);
		gpMain.add(lblHomeValue, 1, 1);
		gpMain.add(lblOption, 0, 2);
		gpMain.add(lblOptionShow, 1, 2);
		gpMain.add(lblCost, 0, 3);
		gpMain.add(lblCoatShow, 1, 3);

		HBox hbButton = new HBox();
		hbButton.setSpacing(10);
		hbButton.getChildren().add(btnInsuranceQuote);
		hbButton.setAlignment(Pos.BASELINE_LEFT);
		gpMain.add(hbButton, 2, 4);

		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();

	}

}
