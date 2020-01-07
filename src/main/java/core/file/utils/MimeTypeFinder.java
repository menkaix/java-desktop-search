package core.file.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MimeTypeFinder {
	
	public static String getMimeType(File file){
		
		try {
			if(file.isDirectory()){
				return "Direcory";
			}
			else{
				return Files.probeContentType(Paths.get(file.getAbsolutePath()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Error" ;
	}

}
