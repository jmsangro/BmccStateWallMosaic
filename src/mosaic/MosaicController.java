package mosaic;

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
	Label stateLabel;
	
	private List<URI> imageList;
	private List<ImageView> viewList = new ArrayList<ImageView>(6);
	private int next;
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewList.add(imageView00);
		viewList.add(imageView01);
		viewList.add(imageView10);
		viewList.add(imageView11);
		viewList.add(imageView20);
		viewList.add(imageView21);
		
		imageList = Main.imageSource.getImageURIs();
		stateLabel.setText(Main.stateName);
		for (int i = 0 ; i < 6 ; i++) {
			try {
				String url = imageList.get(i).toURL().toExternalForm();
				Image image = new Image(url);
				viewList.get(i).setImage(image);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		next = 6;
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask(), 2000, 2000);

		
	};
	
	private static int[] frameChangeOrder = {0,3,4,1,2,5};
	
	private class MyTimerTask extends TimerTask{
		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					int frameIndex = next%6;
					try {
						String url = imageList.get(next).toURL().toExternalForm();
						//Image(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
						Image image = new Image(url);
						viewList.get(frameChangeOrder[frameIndex]).setImage(image);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//change subtext if indicated
					for(int i = 0; i< subTextChangeIndexs.length ; i++){
						if (next == subTextChangeIndexs[i]) {
							subText.setText(subTexts[i]);
						}
					}
					next = (next+1)%imageList.size();
					
				};
			});
		}


		
	}
	
	private static int[] subTextChangeIndexs = {0,5,11,17,25,32};
	private static String[] subTexts = {"Family","Hard Work","Joy","Food","Faith","Place"};
	
}
