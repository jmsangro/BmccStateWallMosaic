package mosaic;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class DirTreeImageSource implements ImageSource {
	
	private ArrayList<File> allFiles = new ArrayList<File>();
	private Iterator<File> fileIterator;
	File rootDir;
	
	public DirTreeImageSource(String directory) {

		try {
			rootDir = new File(directory);
	        Path dir = Paths.get(directory);
	        Files.walk(dir).forEach(path ->  {allFiles.add(path.toFile());} );
	        fileIterator = allFiles.iterator();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public File getNext() {
		File returnVal = null;
		if (fileIterator.hasNext()) {
			returnVal = fileIterator.next();
		}
		else {//start over
			fileIterator = allFiles.iterator();
			returnVal = fileIterator.next();
		}
		return returnVal;
	}

	@Override
	public File getRoot() {
		return rootDir;
	}

}
