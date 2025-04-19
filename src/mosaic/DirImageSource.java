package mosaic;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DirImageSource implements ImageSource {
	
	File rootDir;
	File[] valueDirs;
	int curValIndex = 0;//index into the valueDirs array of current child of root being traversed.
	File[] curImageFiles; //list of files in current value directory;
	int curImageIndex = 0;// index into curImageFiles of current image
	
	public DirImageSource(String directory) {

		try {
			rootDir = new File(new URI(directory));
			
			if (rootDir.isDirectory()) {
				valueDirs = rootDir.listFiles();
				curImageFiles = valueDirs[curValIndex].listFiles();
			}
			else {
				System.err.println("Not a valid directory:"+rootDir);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public File getNext() {
		File returnVal = null;
		if (curImageIndex >= curImageFiles.length) {
			curImageIndex = 0;
			curValIndex ++;
			if (curValIndex >= valueDirs.length) {
				curValIndex = 0;
			}
			curImageFiles = valueDirs[curValIndex].listFiles();
			returnVal = valueDirs[curValIndex];
		}
		else {
			returnVal = curImageFiles[curImageIndex];
			curImageIndex++;
		}
		return returnVal;
	}
	
	@Override
	public File getRoot() {
		return rootDir;
	}

}
