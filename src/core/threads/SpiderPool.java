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
public class SpiderPool extends Vector<Thread> {
    
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
     
    
    private SpiderPool(){
        
        //executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadLimit);
        //executor = new ThreadPoolExecutor(0,150,5, TimeUnit.DAYS, queue);
       
    }
    
    public void execute(FileSpider sipder){
    	
    }
    
    
    public static class PriorityManager extends Thread{
    	
    }
    
    public class Adder extends Thread{
    	FileSpider spider ;
    	public Adder(FileSpider pSpider) {
    		spider = pSpider ;
    	}
    	
    	public void run(){
    		
    	}
    }
    
	
    
}
