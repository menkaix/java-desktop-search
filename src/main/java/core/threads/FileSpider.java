package core.threads;

import java.io.File;
import java.util.Vector;

import core.events.SpiderListener;
import core.file.utils.MimeTypeFinder;


public class FileSpider extends SuperSpider{
    
    

	public void eat(){
		System.out.println("<"+Thread.activeCount()+"> "+MimeTypeFinder.getMimeType(file)+" => "+filePath);
	}

	
	
	public void breed(File f){
		SpiderPool.getInstance().execute(new FileSpider(f.getAbsolutePath())) ;
		
	}
	
	public FileSpider(){
		super();
	}
	
	public FileSpider(String s){
		super(s);
	}
	
	
    
}
