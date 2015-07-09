/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mendrika
 */
public class SpiderPoolInternal {
    
    //================= SINGLETON ===========================================
    
    private static SpiderPoolInternal instance ;
    
    public static SpiderPoolInternal getInstance(){
        if(instance == null){
            
            instance = new SpiderPoolInternal();
            
        }
        
        return instance ;
    }
    
    //================ singleton ============================================
    
    
    private ThreadPoolExecutor executor ;
    private  BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>();
   
    
    private SpiderPoolInternal(){
        
        //executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadLimit);
        executor = new ThreadPoolExecutor(0,150,5, TimeUnit.DAYS, queue);
       
    }
    
    
    
    public void execute(Thread pThread){
    	
    	executor.execute(pThread);
    	
    	
    	
    }
    
}
