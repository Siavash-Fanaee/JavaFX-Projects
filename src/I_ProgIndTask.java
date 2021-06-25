//Standard javafx imports.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


//Imports for components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ProgressBar;



//Imports for layout.
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


//Imports to support concurrency. One-off task.
import javafx.concurrent.Task;

public class I_ProgIndTask extends Application {
	
	Label lblProgress;
	ProgressIndicator progInd;
	Button btnStart, btnCancel;
	
	public I_ProgIndTask() {
		
		Task <Void> task;

		
		lblProgress = new Label("Progress:");
		progInd = new ProgressIndicator();
		progInd.setScaleX(1.1);
		progInd.setScaleY(1.1);
		progInd.setStyle("-fx-accent: green;");

		btnStart = new Button("Start");
		btnCancel = new Button("Cancel");
		
		btnStart.setMinWidth(60);
		btnCancel.setMinWidth(60);
		
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Threads: One-off Task");
		
		//Set the width and height.
		primaryStage.setWidth(150);
		primaryStage.setHeight(300);
				
		//Create a layout.
		VBox vbMain = new VBox();
		vbMain.setAlignment(Pos.CENTER);		//Don't use BASELINE.CENTER
		vbMain.setSpacing(10);
		vbMain.setPadding(new Insets(10));
				
		//Add components to the layout.
		vbMain.getChildren().addAll(lblProgress, progInd, btnStart, btnCancel);
					
		//Create a scene.
		Scene s = new Scene(vbMain);
		
		//Set the scene.
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
