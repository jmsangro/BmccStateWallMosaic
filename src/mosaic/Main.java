package mosaic;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

//TODO investigate GridPane for moasic
//TODO psuedo-randomize frame changing 
public class Main extends Application {
	public static ImageSource imageSource = null;
	public static String stateName;
	@Override
	public void start(Stage primaryStage) {
		
		try {
		    String imagesDir = System.getProperty("imagesDir");
		    stateName = System.getProperty("stateName");
			imageSource = new DirImageSource(imagesDir);
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Mosaic.fxml"));
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
