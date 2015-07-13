package core.threads;

import java.io.File;
import java.util.Vector;

import core.events.SpiderListener;


public class FileSpider extends Thread{
    
    public String filePath ;
    private Vector<SpiderListener> listeners = new Vector<SpiderListener>() ;
    
    public FileSpider(){
        this(".");
    }
    
    public FileSpider(String path){
        filePath = path ;
        
    }
    
    public void run(){
    	
    	fireSpiderBegin();
        
        File file = new File(filePath);
        
        if(file.isDirectory()){
            File[] children = file.listFiles();
            for(File f : children){
                
            	//SpiderPool.getInstance().execute(new FileSpider(f.getAbsolutePath())) ;
                
            }
        }
        else {
            eat() ;
            
        }
        fireSpiderEnd();
    }
    
    private void fireSpiderEnd() {
    	
		for(SpiderListener l : listeners){
			l.onSpiderEnd(this);
		}
		
	}

	private void fireSpiderBegin() {
		
		for(SpiderListener l : listeners){
			l.onSpiderBegin(this);
		}
		
	}

	public void eat(){
    	System.out.println(filePath);
    }
    
}
