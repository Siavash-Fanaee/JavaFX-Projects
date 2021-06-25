
//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//Components for layout.
import javafx.scene.layout.GridPane;

//Insetting and alignment.
import javafx.geometry.Insets;

//Imports for file handling support.
import java.io.File;

//Image for the icon.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class D_ImageViewer_Multiple extends Application {

	Label lblPic = new Label("Pictures:");
	String[] imgs;
	Button btnNext;
	ListView<String> lvImages;
	Image img;
	ImageView ivDisplay;

	// Constructor
	public D_ImageViewer_Multiple() {
		imgs = new File("./image").list();

		ObservableList<String> content = FXCollections.observableArrayList(imgs);

		lvImages = new ListView<String>(content);

		img = new Image("image/" + imgs[0]);
		ivDisplay = new ImageView(img);

		btnNext = new Button("Next Image >>");

	}
	
	@Override
	public void init() throws Exception {

		btnNext.setOnAction(e -> btnNext());

		lvImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ivDisplay.setImage(new Image("./image/" + newValue));

			}
		});
	}
	
	public void btnNext() {
		int index = lvImages.getSelectionModel().getSelectedIndex();
		int imgIndex = (index + 1) % imgs.length;
		ivDisplay.setImage(new Image("./image/" + imgs[imgIndex]));

		if (index == imgs.length - 1) {
			lvImages.getSelectionModel().selectFirst();
		} else {
			lvImages.getSelectionModel().selectNext();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Multiple Images Viewer V.0.0.1");
		primaryStage.setWidth(850);
		primaryStage.setHeight(550);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setHgap(10);
		gpMain.setVgap(10);
		
		gpMain.add(lblPic, 0, 0);
		gpMain.add(lvImages, 0, 1);
		gpMain.add(ivDisplay, 1, 1);
		gpMain.add(btnNext, 1, 2);
		
		
		Scene s = new Scene(gpMain);
		primaryStage.setScene(s);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
