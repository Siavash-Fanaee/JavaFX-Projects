//Standard javafx libraries.
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Components in this application.
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

//Components for layout.
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

//Insetting and alignment.
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//Support for quitting.
import javafx.application.Platform;

//Image for the icon.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Imports for alerts.
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



public class ZZ_DialogSimple extends Application {
	
	MenuBar mnuBar;
	Menu mnuFile , mnuHelp;
	MenuItem miShow, miQuit, miAbout, miHelp;
	TextArea txt;
	
	
	public ZZ_DialogSimple() {
		
		mnuBar = new MenuBar();
		
		mnuFile = new Menu("File");
		mnuHelp = new Menu("Help");
		miShow = new MenuItem("Show Dialog");
		miQuit = new MenuItem("Quit");
		miAbout = new MenuItem("About");
		miHelp = new MenuItem("Help");
		
		txt = new TextArea();
	}
	
	@Override
	public void init() {
		
		miQuit.setOnAction(ae -> Platform.exit());
		
		miAbout.setOnAction(ae -> aboutHandler());
		
		miShow.setOnAction(ae -> showDialog());
		
	}
	
	private void showDialog() {
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Info Entry Form");
		dialogStage.setWidth(380);
		dialogStage.setHeight(280);
		
		Label lblName = new Label("Name: ");
		Label lblAddress = new Label("Address:");
		TextField txtfName = new TextField();
		TextField txtfAddr = new TextField();
		Button btnClose = new Button("Close");
		btnClose.setMinWidth(60);
		Button btnOk = new Button("OK");
		btnOk.setMinWidth(60);
		
		GridPane gpMain = new GridPane();
		gpMain.setPadding(new Insets(10));
		gpMain.setHgap(10);
		gpMain.setVgap(10);
		
		gpMain.add(lblName, 0, 0);
		gpMain.add(txtfName, 1, 0);
		gpMain.add(lblAddress, 0, 1);
		gpMain.add(txtfAddr, 1, 1);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.getChildren().addAll(btnClose , btnOk);
		hbButtons.setAlignment(Pos.BASELINE_LEFT);
		
		gpMain.add(hbButtons, 0, 2);
		
		Scene s = new Scene(gpMain);
		dialogStage.setScene(s);
		dialogStage.show();
		
		btnClose.setOnAction(ae -> dialogStage.close());
		
		btnOk.setOnAction(ae -> {
			txt.appendText(txtfName.getText() + "\n" + txtfAddr.getText());
			dialogStage.close();
		});
		
		
	}
	
	private void aboutHandler() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Seyedsiavash Fanaee - 3019365");
		alert.setContentText("HDC - 2020,2021");
		
		Image img = new Image("Siavash.jpg");
		ImageView imgView = new ImageView(img);
		alert.setGraphic(imgView);
		
		alert.showAndWait();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Simple Dialog v.1.0.0");
		primaryStage.getIcons().add(new Image("Siavash.jpg"));
		primaryStage.setWidth(400);
		primaryStage.setHeight(300);
		
		BorderPane bpMain = new BorderPane();
		
		mnuFile.getItems().add(miShow);
		mnuFile.getItems().add(miQuit);
		
		mnuHelp.getItems().add(miAbout);
		mnuHelp.getItems().add(miHelp);
		
		mnuBar.getMenus().add(mnuFile);
		mnuBar.getMenus().add(mnuHelp);
		
		bpMain.setTop(mnuBar);
		bpMain.setCenter(txt);
		
		Scene s = new Scene(bpMain);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}
