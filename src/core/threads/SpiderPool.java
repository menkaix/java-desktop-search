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
public class SpiderPool extends Vector<SuperSpider> {
    
    //================= SINGLETON ===========================================
    
    private static SpiderPool instance ;
    
    public static SpiderPool getInstance(){
        if(instance == null){
            
            instance = new SpiderPool();
            
        }
        
        return instance ;
    }
    
    //================ singleton ============================================
    
    
    //private ThreadPoolExecutor executor ;
    
    private static int firstEmptyIndex ;
    private static int lastNonEmpty ;
         
    private PoolManager manager ;
    private Runner runner ;
        
    private SpiderPool(){
    	super() ;
        
        //executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadLimit);
        //executor = new ThreadPoolExecutor(0,150,5, TimeUnit.DAYS, queue);
    	manager = PoolManager.getInstance() ;
    	runner = Runner.getInstance();
    	
    }
    
    public void start(){
    	manager.start();
    	runner.start();
    }
    
    public void execute(SuperSpider spider){
    	
    	spider.state = SpiderState.IS_READY ;    	
    	this.add(spider);
    	   	
    }
    
    public synchronized void defrag(){
    	Vector<SuperSpider> tmp = new Vector<SuperSpider>();
    	
    	for(SuperSpider spider : this){
    		if(spider.state != SpiderState.IS_DONE){
    			tmp.add(spider);
    		}
    	}
    	
    	this.clear();
    	this.addAll(tmp);
    }
    
    
   
    
    
	
    
}
