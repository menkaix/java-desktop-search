/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.threads;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import core.events.SpiderListener;

/**
 *
 * @author mendrika
 */
public class SpiderPoolInternal extends Thread implements SpiderListener{
    
    //================= SINGLETON ===========================================
    
    private static SpiderPoolInternal instance ;
    
    public static SpiderPoolInternal getInstance(){
        if(instance == null){
            
            instance = new SpiderPoolInternal();
            
        }
        
        return instance ;
    }
    
    //================ singleton ============================================
    
    
    //private ThreadPoolExecutor executor ;
    private Vector<Thread> queue = new Vector<Thread>();
    private boolean active = false ;
    private int activeSpiderLimit = 10 ;
    
    private  int activeSpiderCount = 0 ;
   
    
    private SpiderPoolInternal(){
        
        //executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadLimit);
        //executor = new ThreadPoolExecutor(0,150,5, TimeUnit.DAYS, queue);
       
    }
    
    
    
    public synchronized void start(){
    	System.out.println("starting pool");
    	if(!active){
    		active = true ;
    		super.start();
    	}
    }
    
    public void run(){
    	System.out.println("running pool");
    	while(!queue.isEmpty()){
    		
    		System.out.println("Active Spider count "  + activeSpiderCount);
    		
    		if(activeSpiderCount<activeSpiderLimit){
    			System.out.println("running Spider");
//    				try {
//    					
//    					//queue.poll(3, TimeUnit.SECONDS).start();
//    				
//    				} catch (InterruptedException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				};
    				
				
    			
    		}
    		
    	}
    }



	public void onSpiderBegin(FileSpider source) {
		System.out.println("running new Spider "+source.filePath);
		activeSpiderCount ++ ;
		
	}



	public void onSpiderEnd(FileSpider source) {
		System.out.println("ending new Spider"+source.filePath);
		activeSpiderCount -- ;
		
	}



	public void execute(Thread pThread){
		
		
		//executor.execute(pThread);
		
			queue.add(pThread);
			System.out.println("new Spider #"+queue.size());
			this.start();
				
	}
    
}
