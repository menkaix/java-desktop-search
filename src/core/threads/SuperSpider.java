package core.threads;

import java.io.File;
import java.util.Vector;

import core.events.SpiderListener;

public abstract class SuperSpider extends Thread {
	
	public abstract void eat();
	public abstract void breed(File f);
	
	private Vector<SpiderListener> listeners = new Vector<SpiderListener>() ;
	
	public static long spiderCount = 0 ; 	
	
	public long spiderID = 0 ;
		
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
	
	public void run(){
    	
    	fireSpiderBegin();
    	
    	state = SpiderState.IS_ACTIVE ;
        
        file = new File(filePath);
        
        if(file.isDirectory()){
            File[] children = file.listFiles();
            for(File f : children){
                
            	breed(f);
                
            }
        }
        else {
            eat() ;
            
        }
        fireSpiderEnd();
        state=SpiderState.IS_DONE ;
    }

	public synchronized void start(){
		if(state == SpiderState.IS_READY){
			super.start();
		}
		else{
			System.err.println("Launching "+state.toString()+" Spider : "+filePath);
		}
	}
	
	public synchronized void addSpiderListener(SpiderListener spiderlistener){
		listeners.add(spiderlistener) ;
	}
	
	public SuperSpider(){
	    this(".");
	}
	
	public SuperSpider(String path){
	    filePath = path ;
	    spiderID = ++spiderCount ;
	    
	}
	
	

}
