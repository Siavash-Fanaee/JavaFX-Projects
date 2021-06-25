import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;

//Imports for components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

//Support for date entry.
import javafx.scene.control.DatePicker;

//Icons etc.
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Layout, containers etc.
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Support for quitting.
import javafx.application.Platform;


//Date handling.
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


//Currency output formatting.
import java.text.NumberFormat;
import java.util.Locale;

//Alerts...
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class H_InterestCalculatorPaddy extends Application {

	//Components that need class scope.
	Label lblCapital, lblInterestRate, lblInvTerm;
	
	
	TextField txtfCapital, txtfInterestRate, txtfInvTerm;
	
	Button btnQuit, btnCalculate, btnDialog;
		
	CheckBox chkSimple, chkCompound;
	
	TextArea txtMain;
		
	

	public H_InterestCalculatorPaddy() {
		//Instantiate components with 'new'.
		lblCapital = new Label("Capital:");
		
		lblInterestRate = new Label("Interest rate:");
		lblInvTerm = new Label("Investment term (yrs):");
		
		txtfCapital = new TextField();
		txtfInterestRate = new TextField();
		txtfInvTerm = new TextField();
				
		btnQuit = new Button("Quit");
		btnCalculate = new Button("Calculate");
		btnDialog = new Button("...");
		
		//Set button sizes.
		btnQuit.setMinWidth(80);
		btnCalculate.setMinWidth(80);
		
		chkSimple = new CheckBox("Simple interest");
		chkCompound = new CheckBox("Compound interest");
				
		txtMain = new TextArea();
				
		
	}//constructor()
	
	@Override
	public void init() {
		//Event handling: Respond to program events.
		
		//Quitting the application.
		btnQuit.setOnAction(ae -> Platform.exit());
		
		//Show the investment term dialog.
		btnDialog.setOnAction(ae -> showTermDialog());
		
		//Calculate the interest due and show analysis in the main text area.
		btnCalculate.setOnAction(ae -> showInterestAnalysis());
				
		
		
	}//init()
	
	public void showInterestAnalysis(){
		
		//Local variables to store values from user.
		double capital = 0;
		double intRate = 0;
		double years = 0;
		
		
		//Get the user data and assign to the local variables. Use a try{}.
		//Fields may have been left unfilled etc.
		try {
			
			capital = Double.parseDouble(txtfCapital.getText());
			intRate = Double.parseDouble(txtfInterestRate.getText());
			years = Double.parseDouble(txtfInvTerm.getText());
						
		}//try
		catch(NumberFormatException nfe) {
			System.err.print("No data entered." + nfe.toString());
			
		}//catch()
		
		
		//Check if the simple interest check box is selected.
		if(chkSimple.isSelected()) {
			
			//Show the simple interest analysis in the main text area.
			showSimpleInterest(capital, intRate, years);
			
			
		}//if chkSimple()
		
		//Check if the compound interest checkbox is selected.
		if(chkCompound.isSelected()) {
			
			showCompoundInterest(capital, intRate, years);
			
			
		}//if chkCompound()
		
	}//showInterestAnalysis()
	
	
	
	private void showSimpleInterest(double cap, double iRate, double yrs) {
		
		double interest = 0;
		double increasedCapital;
		String analysisString = "";
		
		
		//The increased capital is the amount * iRate * yrs.
		double interestAmount = getSimpleInterest(cap, iRate, yrs);
		
		//The increased capital is got by adding on the interest.
		increasedCapital = cap + interestAmount;
		
		//Now show the analysis string in the main text area.
		analysisString += "\nSimple interest:\nYear " + yrs +
				" Initial capital: " + cap +
				" Interest earned: " + interestAmount + 
				" Final amount: " + increasedCapital;
		
		//Now show the analysis in the main text area.
		txtMain.setText(txtMain.getText() + analysisString);
			
		
	}//showSimpleInterest()
	
	
	private void showCompoundInterest(double cap, double iRate, double yrs) {
		
		double interestAmt = 0;
		double increasedCapital = cap;
		double totalInterest = 0;
		String analysisString = "";
		
		//Use a loop (iteration) to apply simple interest to an
		//increasing capital amount repeatedly = compound interest.
		for(int i = 1; i <= yrs; i++) {
			interestAmt = getSimpleInterest(increasedCapital, iRate, 1);
			totalInterest = totalInterest + interestAmt;		//Total interest is increasing.
			increasedCapital = increasedCapital + interestAmt;
						
		}//for()
		
		NumberFormat currFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
		
		
		//Show the analysis in the main text area.
		analysisString += "\n\n Compound Interest: Year " + yrs + 
				" \tInitial capital:" + currFormat.format(cap) + 
				" \tInterest earned:" + currFormat.format(totalInterest) + 
				" \tFinal amount :" + currFormat.format(increasedCapital);
		
		
		//Set the analysis string into the main text area.
		txtMain.setText(txtMain.getText()+ analysisString);
			
		
	}//showCompoundInterest()
	
	
	
	//Method to calculate simple interest.
	private double getSimpleInterest(double cap, double iRate, double yrs) {
		
		double interest = 0;
		
		interest = cap * (iRate/100) * yrs;
		
		return interest;		
		
	}//getSimpleInterest()
	

	
	private void showTermDialog(){
		
		//Create a secondary stage.
		Stage termDialogStage = new Stage();
		
		//Set the title.
		termDialogStage.setTitle("Select Investment Term");
		
		//Add the icon.
		termDialogStage.getIcons().add(new Image("./124.jpg"));
		
		//Size the dialog.
		termDialogStage.setWidth(350);
		termDialogStage.setHeight(180);
		
		
		//Create a layout.
		VBox vbDialogMain = new VBox();				//Main dialog container.
		vbDialogMain.setPadding(new Insets(10));
		
		GridPane gpDialog = new GridPane();			//Contains labels and datepickers.
		
		//Set the gaps for the gridpane.
		gpDialog.setHgap(10); 						//Horizontal gaps.
		gpDialog.setVgap(10);						//Vertical gaps.
		
		//Put the gp container into the vb main container.
		vbDialogMain.getChildren().add(gpDialog);
		vbDialogMain.setSpacing(10);
		
		
		//Create components for the dialog.
		Label lblTermStart = new Label("Investment start date:");
		Label lblTermEnd = new Label("Investment end date:");
		
		//Datepickers support uniform format date entry.
		DatePicker dpStart = new DatePicker();
		DatePicker dpEnd = new DatePicker();
		
		//The default date in each datepicker is today's date.
		dpStart.setValue(LocalDate.now());
		dpEnd.setValue(LocalDate.now());
				
		
		//Cancel and OK buttons for the dialog.
		Button btnCancel = new Button("Cancel");
		Button btnOK = new Button("OK");
		
		//Manage button sizes.
		btnCancel.setMinWidth(60);
		btnOK.setMinWidth(60);		
				
		//A hbox for the OK and Cancel buttons.
		HBox hbDlgButtons = new HBox();
		
		//Set the spacing for the button box.
		hbDlgButtons.setSpacing(10);
		
		//Add the buttons to the hbox.
		hbDlgButtons.getChildren().addAll(btnCancel, btnOK);
		
		//Now align the buttons right.
		hbDlgButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		//Now add the button hbox to the main container vbDialogMain.
		vbDialogMain.getChildren().add(hbDlgButtons);
		
		
				
		//Add controls to the layout.
		gpDialog.add(lblTermStart, 0, 0);
		gpDialog.add(dpStart, 1, 0);
		
		gpDialog.add(lblTermEnd, 0, 1);
		gpDialog.add(dpEnd, 1, 1);
		
		
		
		
		//Create a scene for the dialog.
		Scene s = new Scene(vbDialogMain);
		
		
		//Set the scene.
		termDialogStage.setScene(s);
		
		//Apply a stylesheet.
		s.getStylesheets().add("style.css");
						
		
		//Show it.
		termDialogStage.show();
		
		//Dialog event handling.
		//The Cancel button. Close the dialog stage.
		btnCancel.setOnAction(ae -> termDialogStage.close());
		
		btnOK.setOnAction(ae -> {	
						
			//Get the dates entered by the user.
			LocalDate startDate = dpStart.getValue();
			LocalDate endDate = dpEnd.getValue();
			
			if(endDate.isAfter(startDate)) {
			
			//Get the time elapsed in years between the two dates.
			long years = ChronoUnit.YEARS.between(startDate, endDate);
			
			//Set the interval in years back into the main UI textfield.
			txtfInvTerm.setText(years + "");
			
			//Now, just close the dialog.
			termDialogStage.close();
			}
			else {
				//Flag invalid dates using an alert.
				Alert alert = new Alert(AlertType.ERROR);
				alert.setResizable(true);
				
				//Configure the alert.
				alert.setTitle("Date entry error");
				alert.setHeaderText("Invalid date entry");
				alert.setContentText("The end date occurs after the start date.");
				
				//Just show it.
				alert.showAndWait();
				
				
			}//else
						
			
		});
		
		
			
		
		
	}//showTermDialog()
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//User interface construction.
		//Set the title.
		primaryStage.setTitle("InterestCalculator V1.0.0");
		
		
		//Add an appropriate icon.
		primaryStage.getIcons().add(new Image("./124.jpg"));
		
		
		
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		
		
		//Create a layout.
		VBox vbMain = new VBox();					//Main container.
		vbMain.setPadding(new Insets(10));
		vbMain.setSpacing(10);
		
		GridPane gp = new GridPane();				//Contains textfields, labels, a button and checkboxes.
		gp.setHgap(10);								//Horizontal gaps.
		gp.setVgap(10);
		
		
		HBox hbButtons = new HBox();				//Just contains the Quit and Calculate buttons.
		hbButtons.setSpacing(10);
		
		//Put the gp into the main container.
		vbMain.getChildren().add(gp);
		
		
		
		
		//Add components to the layout.
		gp.add(lblCapital, 0, 0);
		gp.add(txtfCapital, 1, 0);
		
		gp.add(lblInterestRate, 0, 1);
		gp.add(txtfInterestRate, 1, 1);
		
		gp.add(lblInvTerm, 0, 2);
		gp.add(txtfInvTerm, 1, 2);
		gp.add(btnDialog, 2, 2);
		
		gp.add(chkSimple, 1, 3);
		gp.add(chkCompound, 1, 4);
		
		vbMain.getChildren().add(txtMain);
		
		//Add the buttons to the buttons hbox.
		hbButtons.getChildren().addAll(btnQuit, btnCalculate);
		
		//Now, add the button box to the main container.
		vbMain.getChildren().add(hbButtons);
		hbButtons.setAlignment(Pos.BASELINE_RIGHT);
		
		
		
		
		
				
		
		//Create a scene.
		Scene s = new Scene(vbMain);
		
		
		//Set the scene.
		primaryStage.setScene(s);
		
		//Apply a stylesheet.
		s.getStylesheets().add("style.css");
						
		
		//Show the stage.
		primaryStage.show();
		

	}//start()


	public static void main(String[] args) {
		//Launch the application.
		launch();
		

	}//main()

}//class
