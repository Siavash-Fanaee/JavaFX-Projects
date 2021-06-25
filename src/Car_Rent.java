//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//Components for layout.
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

//Insetting and alignment.
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Imports for alerts.
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//Date handling.
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.DatePicker;


public class Car_Rent extends Application {
	
	Label lblStart, lblEnd, lblCost;
	TextField txtfStart, txtfEnd, txtfCost;
	Button btn;

	public Car_Rent() {
		
		lblStart = new Label("Hire Start Date:");
		lblEnd = new Label("Hire End Date:");
		lblCost = new Label("Total Hire Cost:");;
		txtfStart = new TextField();
		txtfEnd = new TextField();
		txtfCost = new TextField();
		txtfCost.setMinWidth(230);

		btn = new Button("New Hire Booking");
		btn.setMinWidth(80);

	}
	
	@Override
	public void init() {
		
		btn.setOnAction(ae -> bookingHandler());
		
	}
	
	private void bookingHandler() {
		
		Label lblS = new Label("Hire Start Date:"); 
		Label lblE = new Label("Hire End Date:");
		Label lblC = new Label("Cost (Euro):");
		TextField txtfC = new TextField();
		DatePicker dpStart = new DatePicker();
		DatePicker dpEnd = new DatePicker();
		dpStart.setValue(LocalDate.now());
		dpEnd.setValue(LocalDate.now());
		Button btnCancel = new Button("Cancel");
		Button btnOk = new Button("Ok");
		btnCancel.setMinWidth(60);
		btnOk.setMinWidth(60);
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Date and Cost");
		dialogStage.setWidth(380);
		dialogStage.setHeight(280);
		
		GridPane gpDialog = new GridPane();
		gpDialog.setPadding(new Insets(10));
		gpDialog.setVgap(10);
		gpDialog.setHgap(10);
		
		gpDialog.add(lblS, 0, 0);
		gpDialog.add(dpStart, 1, 0);
		gpDialog.add(lblE, 0, 1);
		gpDialog.add(dpEnd, 1, 1);
		gpDialog.add(lblC, 0, 2);
		gpDialog.add(txtfC, 1, 2);
		
		HBox hbDialogBtns = new HBox();
		hbDialogBtns.setSpacing(10);
		hbDialogBtns.getChildren().addAll(btnCancel , btnOk);
		hbDialogBtns.setAlignment(Pos.BASELINE_RIGHT);
		
		gpDialog.add(hbDialogBtns, 1, 3);
		
		Scene dialogS = new Scene(gpDialog);
		dialogStage.setScene(dialogS);
		dialogS.getStylesheets().add("style.css");
		dialogStage.show();
		
		
		btnCancel.setOnAction(ae -> dialogStage.close());
		
		btnOk.setOnAction(ae -> {
			LocalDate startDate = dpStart.getValue();
			LocalDate endDate = dpEnd.getValue();
			
			if (endDate.isAfter(startDate)) {
				//Get the time elapsed in years between the two dates.
				long days = ChronoUnit.DAYS.between(startDate, endDate);
				
				Long totalCost = days * Long.parseLong(txtfC.getText());
				
				txtfStart.setText(startDate.toString());
				txtfEnd.setText(endDate.toString());
				
				txtfCost.setText("Days Booked:  "+ Long.toString(days)+ ". Total Cost: " + Long.toString(totalCost) + " Euro");
				
				//Now, just close the dialog.
				dialogStage.close();
				
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
		
		primaryStage.setTitle("Hire Machine");
		primaryStage.getIcons().add(new Image("Siavash.jpg"));
		primaryStage.setWidth(500);
		primaryStage.setHeight(350);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(20));
		gpMain.setHgap(20);
		gpMain.setVgap(30);
		
		gpMain.add(lblStart, 0, 0);
		gpMain.add(txtfStart, 1, 0);
		gpMain.add(lblEnd, 0, 1);
		gpMain.add(txtfEnd, 1, 1);
		gpMain.add(lblCost, 0, 2);
		gpMain.add(txtfCost, 1, 2);
		
		HBox hbButton = new HBox();
		hbButton.getChildren().add(btn);
		hbButton.setSpacing(10);
		gpMain.add(hbButton, 0, 3);
		
		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style.css");
		primaryStage.show();
		
		}

	public static void main(String[] args) {
		launch();
	}

}
