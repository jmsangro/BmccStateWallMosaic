package mosaic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
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
		viewList.add(imageView01);
		viewList.add(imageView10);
		viewList.add(imageView11);
		viewList.add(imageView20);
		viewList.add(imageView21);

		
		imageSource = Main.imageSource;
		//titleLabel.setText(Main.stateName);
//		for (int i = 0 ; i < 6 ; i++) {
//			try {
//				String url = imageList.get(i).toURL().toExternalForm();
//				Image image = new Image(url);
//				viewList.get(i).setImage(image);
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		next = 6;
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask(), 2000, 2000);

		
	};
	
	private static int[] frameChangeOrder = {0,3,4,1,2,5};
	int frameIndex = 0;
	
	private class MyTimerTask extends TimerTask{
		@Override
		public void run() {
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
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frameIndex = (frameIndex+1)%frameChangeOrder.length;
	}
	
	private void setImageSize(ImageView iv) {
		double aspectRatio = 8.0/10.0;
		iv.setFitWidth(gridPane.getWidth()/2);
		iv.setFitHeight(gridPane.getWidth()/2*aspectRatio);
		
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
		frameIndex = 0; 
		processNextFile();
	};
	
	private static int[] subTextChangeIndexs = {0,5,11,17,25,32};
	private static String[] subTexts = {"Family","Hard Work","Joy","Food","Faith","Place"};
	
}
