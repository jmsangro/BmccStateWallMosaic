package mosaic;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;


public class Main extends Application {
	public static ImageSource imageSource = null;
	public static final double aspectRatio = 1080.0/1920.0 ;
	@Override
	public void start(Stage primaryStage) {
		
		try {
		    String imagesDir = System.getProperty("imagesDir");
		    String screenStr = System.getProperty("screen");
		    int screenIndex = ((screenStr == null)? 0 : Integer.parseInt(screenStr));
			imageSource = new DirTreeImageSource(imagesDir);
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Mosaic.fxml"));
			//maximize on screen
			ObservableList<Screen> screenList = Screen.getScreens();
			Screen targetScreen = Screen.getPrimary();
			if (screenIndex < screenList.size()) {
				targetScreen = screenList.get(screenIndex);
			}
			
			Rectangle2D screenBounds = targetScreen.getVisualBounds();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			 primaryStage.setX(screenBounds.getMinX());
			 primaryStage.setY(screenBounds.getMinY());
			 primaryStage.setWidth(screenBounds.getWidth());
			 primaryStage.setHeight(screenBounds.getHeight());
		    String styleSheet = System.getProperty("styleSheet");
		    if (styleSheet == null) {
		    	System.out.println("WARNING: No stylesheet specified with -DstyleSheet command line option. Reverting to application.css");
		    	styleSheet = "application.css";
		    }
			Scene scene = new Scene(root,screenBounds.getWidth(),screenBounds.getHeight());
			scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
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
