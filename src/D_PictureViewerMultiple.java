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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

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

public class D_PictureViewerMultiple extends Application {
	
	Label lblPic;
	ListView <String> lvImages;
	Image img;
	ImageView imvMain;
	Button btnNext;

	public D_PictureViewerMultiple() {
		
		lblPic = new Label("Pictures:");
		lvImages = new ListView <String>();
		imvMain = new ImageView();
		btnNext = new Button(" Next Image > ");
		
		lvImages.setMaxWidth(100);
		lvImages.setMaxHeight(400);
		
		String[] imgs = new File("image").list();
	    ObservableList<String> content = FXCollections.observableArrayList(imgs);
	    lvImages = new ListView<String>(content);

	    img = new Image("image/"+ imgs[0]);
	    imvMain = new ImageView(img);


	}


	@Override
	public void init() {
		
		
		lvImages.setOnMousePressed(ae -> {
			
			//Get the name that is selected.
			String selectedName = lvImages.getSelectionModel().getSelectedItem().toString();
			
			//Now call showEmailAndAddr() with the name as arg.
			showPicture(selectedName);
		});
		
	
	}
	
	private void showPicture(String selectedName) {
		try {
			
			String[] imgs = new File("image").list();

			img = new Image ("image/"+imgs[0]);
			imvMain.setImage(img);

		} // try
		catch (Exception e) {

			System.out.println("Error reading contacts file.");
			e.toString();

		} // catch()
		
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Multiple Image Viewer");
		primaryStage.setWidth(850);
		primaryStage.setHeight(550);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setHgap(10);
		gpMain.setVgap(10);
		
		gpMain.add(lblPic, 0, 0);
		gpMain.add(lvImages, 0, 1);
		gpMain.add(imvMain, 1, 1);
		gpMain.add(btnNext, 1, 2);
		
		
		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
