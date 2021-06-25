//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

//Components for layout.
import javafx.scene.layout.GridPane;

//Insetting and alignment.
import javafx.geometry.Insets;

//Support for quitting.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//Imports for file handling support.

import java.io.File;

//Image for the icon.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Multiple_Picture_Viewer extends Application {
	
	Label lblPic;
	ListView <String> lvImages;
	Image img;
	ImageView imvMain;
	Button btnNext;

	public Multiple_Picture_Viewer() {
		
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
