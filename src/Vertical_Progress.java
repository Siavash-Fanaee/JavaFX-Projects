
//Standard javafx libraries:
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

//Components for layout.
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

//Insetting and alignment.
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Image for the icon.
import javafx.scene.image.Image;

public class Vertical_Progress extends Application {

	//Class Level Declaration:
	Label lblName;
	ProgressBar progressBar;
	ProgressIndicator progressInd;
	Button btnStart, btnCancel;

	public Vertical_Progress() {

		//Variable Instantiation:
		lblName = new Label("Progress:");
		progressInd = new ProgressIndicator(0);
		progressBar = new ProgressBar(0);
		progressBar.setMaxWidth(300);
		btnStart = new Button("Increase");
		btnCancel = new Button("Decrease");

	}

	@Override
	public void init() throws Exception {

		//Event Handling for Buttons(Start , Cancel):
		btnStart.setOnAction(e -> startTask());
		
		btnCancel.setOnAction(e -> cancelTask());

	}

	public void startTask() {
		double prog = progressBar.getProgress();
		prog += 0.05;

		if (prog >= 1) {
			progressBar.setProgress(1);
		} else {
			progressBar.setProgress(prog);
		}

	}

	public void cancelTask() {

		double prog = progressBar.getProgress();
		prog -= 0.05;

		if (prog < 0.01) {
			progressBar.setProgress(0);
		} else {
			progressBar.setProgress(prog);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Vertical Progress");
		primaryStage.getIcons().add(new Image("Siavash.jpg"));
		primaryStage.setWidth(220);
		primaryStage.setHeight(360);

		progressBar.progressProperty().bindBidirectional(progressInd.progressProperty());

		//Main layout:
		VBox vbMain = new VBox();
		vbMain.setPadding(new Insets(15));
		vbMain.setSpacing(15);
		vbMain.setAlignment(Pos.TOP_CENTER);
		
		HBox progBar = new HBox();
		progBar.getChildren().add(progressBar);
		progBar.setRotate(-90);
		progBar.setAlignment(Pos.CENTER);

		//Add components to the layout:
		VBox.setVgrow(progBar, Priority.ALWAYS);
		vbMain.getChildren().addAll(lblName, progBar, progressInd, btnStart, btnCancel);

		Scene s = new Scene(vbMain);
		primaryStage.setScene(s);
		s.getStylesheets().add("style.css");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
