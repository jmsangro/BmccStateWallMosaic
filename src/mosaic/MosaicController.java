package mosaic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MosaicController implements Initializable  {
	@FXML
	ImageView imageView00;
	@FXML
	ImageView imageView01;
	@FXML
	ImageView imageView10;
	@FXML
	ImageView imageView11;
	@FXML
	ImageView imageView20;
	@FXML
	ImageView imageView21;
	@FXML
	ImageView imageView30;
	@FXML
	ImageView imageView31;
	@FXML
	Label subText;
	@FXML 
	Label titleLabel;
	@FXML
	GridPane gridPane;
	
//	private List<URI> imageList;
	private List<ImageView> viewList = new ArrayList<ImageView>(6);
	private ImageSource imageSource;

		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewList.add(imageView00);
		viewList.add(imageView10);
		viewList.add(imageView01);
		viewList.add(imageView11);
		viewList.add(imageView20);
		viewList.add(imageView21);
		viewList.add(imageView30);
		viewList.add(imageView31);

		
		imageSource = Main.imageSource;
	    String changeIntStr = System.getProperty("changeInterval");
	    int changeInterval = ((changeIntStr == null)? 2000 : Integer.parseInt(changeIntStr));
	    
	    String frameOrderStr = System.getProperty("frameOrder");
	    if (frameOrderStr != null) {
	    	String[] splitStr = frameOrderStr.split(",");
	    	for (int i=0 ; i < splitStr.length ; i++) {
	    		frameChangeOrder[i] = Integer.parseInt(splitStr[i]);
	    	}
	    }
	    
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask(), 2000, changeInterval);

		
	};
	
	private int[] frameChangeOrder = {0,3,4,7,1,2,5,6};
	int frameIndex = 0;
	
	private class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			System.gc();
			Platform.runLater( () -> processNextFile() );
		}


		
	}
	
	public void processNextFile() {
		try {
			File nextFile = imageSource.getNext();
			if (nextFile.isDirectory()) {
				if (imageSource.getRoot().equals(nextFile.getParentFile() ) ) {
					processTitleLevelChange(nextFile);
				}
				else {
					processSubtextLevelChange(nextFile);
				}
			}
			else {//process single image change
				String url = nextFile.toURI().toURL().toExternalForm();
				//Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
				Image image = new Image(url);
				ImageView iv = viewList.get(frameChangeOrder[frameIndex]);
				setImageSize(iv);
				iv.setImage(image);
				frameIndex = (frameIndex+1)%frameChangeOrder.length;

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setImageSize(ImageView iv) {
		//double aspectRatio = 8.0/10.0;
		iv.setFitWidth(gridPane.getWidth()/2);
		iv.setFitHeight(gridPane.getHeight()/3);
		
	}

	private void processTitleLevelChange(File nextFile) {
		titleLabel.setText(nextFile.getName());
		for (ImageView iView : viewList) {
			iView.setImage(null);
		}
		processNextFile();
		
	}

	private void processSubtextLevelChange(File nextFile) {
		subText.setText(nextFile.getName());
		//frameIndex = 0; 
		processNextFile();
	};
	
}
