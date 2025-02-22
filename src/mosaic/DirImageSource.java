package mosaic;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DirImageSource implements ImageSource {
	
	private List<URI> imageURIs;
	
	public DirImageSource(String directory) {
		File dir;
		
		imageURIs = new ArrayList<URI>();
		try {
			dir = new File(new URI(directory));
			if (dir.isDirectory()) {
				File[] children = dir.listFiles();
				for (File child : children ) {
					imageURIs.add(child.toURI());		
				}
			}
			else {
				System.err.println("Not a valid directory:"+dir);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<URI> getImageURIs() {

		return imageURIs;
	}

}
