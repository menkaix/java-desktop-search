package core.threads;

import java.io.File;
import java.util.Vector;

import core.events.SpiderListener;
import core.file.utils.MimeTypeFinder;


public class FileSpider extends Thread{
    
    private Vector<SpiderListener> listeners = new Vector<SpiderListener>() ;
	
    public String filePath ;
	public SpiderState state ;
	public int priority = 0 ;
	public File file ;
    
    private synchronized void fireSpiderEnd() {
		
		for(SpiderListener l : listeners){
			l.onSpiderEnd(this);
		}
		
	}

	private synchronized void fireSpiderBegin() {
		
		for(SpiderListener l : listeners){
			l.onSpiderBegin(this);
		}
		
	}

	public synchronized void start(){
		if(state == SpiderState.IS_READY){
			super.start();
		}
		else{
			System.err.println("Launching "+state.toString()+" Spider : "+filePath);
		}
	}
	
	public void run(){
    	
    	fireSpiderBegin();
    	
    	state = SpiderState.IS_ACTIVE ;
        
        file = new File(filePath);
        
        if(file.isDirectory()){
            File[] children = file.listFiles();
            for(File f : children){
                
            	SpiderPool.getInstance().execute(new FileSpider(f.getAbsolutePath())) ;
                
            }
        }
        else {
            eat() ;
            
        }
        fireSpiderEnd();
        state=SpiderState.IS_DONE ;
    }
    
    public synchronized void addSpiderListener(SpiderListener spiderlistener){
    	listeners.add(spiderlistener) ;
    }
    
    public void eat(){
    	System.out.println("("+Thread.activeCount()+") "+MimeTypeFinder.getMimeType(file)+" : "+filePath);
    }

	public FileSpider(){
	    this(".");
	}

	public FileSpider(String path){
	    filePath = path ;
	    
	}
    
}
