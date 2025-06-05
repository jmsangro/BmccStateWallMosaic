package mosaic;
	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

//TODO investigate GridPane for moasic
//TODO psuedo-randomize frame changing 
public class Main extends Application {
	public static ImageSource imageSource = null;
	public static String stateName;
	public static final double aspectRatio = 1080.0/1920.0 ;
	@Override
	public void start(Stage primaryStage) {
		
		try {
		    String imagesDir = System.getProperty("imagesDir");
		    stateName = System.getProperty("stateName");
			imageSource = new DirTreeImageSource(imagesDir);
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Mosaic.fxml"));
			//maximize on screen
			Rectangle2D screenBounds = Screen.getPrimary().getBounds();
			double width = screenBounds.getHeight() * aspectRatio;
			width = screenBounds.getWidth();
			Scene scene = new Scene(root,width,screenBounds.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
