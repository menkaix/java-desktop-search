package core.threads;

import java.io.File;
import java.util.Vector;

import core.events.SpiderListener;
import core.file.utils.MimeTypeFinder;


public class FileSpider extends SuperSpider{
    
    

	public void eat(){
		//System.out.println("----------<"+Thread.activeCount()+"> ["+MimeTypeFinder.getMimeType(file)+"] => "+filePath);
		
	}

	public FileSpider(){
		super();
	}
	
	public FileSpider(String s){
		super(s);
	}
	
	
    
}
