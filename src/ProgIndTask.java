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


public class ProgIndTask extends Application {
	
	//The one-off task. Declare it at class scope.
	Task <Void> task;
		
	
	
	
	//Declare variables/components that need class scope.
	Label lblProgress;
	
	ProgressIndicator progInd;
	
		
	Button btnStart, btnCancel;
	
	


	public ProgIndTask() {
		//Instantiate components with 'new'.
		lblProgress = new Label("Progress:");
		
		progInd = new ProgressIndicator(0);
		
		progInd.setScaleX(1.1);
		progInd.setScaleY(1.1);
		progInd.setStyle("-fx-accent: green;");
			
		btnStart = new Button("Start");
		btnCancel = new Button("Cancel");
		
		//Manage button sizes.
		btnStart.setMinWidth(60);
		btnCancel.setMinWidth(60);
		
		
	}//constructor()
	
	@Override
	public void init() {
		//Event handling...
		btnStart.setOnAction(ae -> startTask());
		
		//Handle cancelling of the task.
		btnCancel.setOnAction(ae -> cancelTask());
		
	}//init()
	
	
	private void cancelTask() {
		
		task.cancel();
		
		btnStart.setDisable(false);
		
		
	}//cancelTask()
	
	
	private void startTask() {
		
		btnStart.setDisable(true);
				
		task = new Task<Void>() {
			@Override
			public Void call() {
				//The functionality for this application.
				final long max = 1000000000;
				
				//Loop to simulate a long task.
				for(long i = 1; i <= max; i++) {
					
					if(isCancelled()) {
						updateProgress(0, max);
						break;
					}//if()
					//Update the progress of the task.
					updateProgress(i, max);
					
				}//for()
				btnStart.setDisable(false);
				return null;
			}//call()
			
		};//task
		
		//Now update the progress shown in the progress indicator by binding it to the task progress.
		progInd.progressProperty().bind(task.progressProperty());
		
		//Now start the thread.
		new Thread(task).start();
		//task.start();						Can't do this.
				
	}//startTask()
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Set the title.
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

		//Show the stage.
		primaryStage.show();
		
	}//start()


	public static void main(String[] args) {
		//Launch the application.
		launch();
		

	}//main()

}//class