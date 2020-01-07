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
    	manager = new PoolManager() ;
    	runner = new Runner();
    	
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
    
    
    public class PoolManager extends Thread{
    	
    	int emptyTry = 0 ;
    	
    	public void run(){
    		
    		
			while(emptyTry<10){
				//System.out.println("Managing");
				int n = SpiderPool.this.size();
				int i = 0 ;
				if(n>0){
					for(SuperSpider spider : SpiderPool.this ){
						if(spider.state == SpiderState.IS_DONE){
							i++ ;
						}
					}
					if(i/n > 0.5){
						SpiderPool.this.defrag();
					}
				}
				else {
					
					emptyTry++;
					
				}
				
				//System.out.println("Managing "+n+" elements with "+i+" idles");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		
    		
    	}
    	
    }
    
    public class Runner extends Thread implements SpiderListener{
    	
    	int spiderlaunched = 0 ;
    	int runningSpider = 0 ;
    	int emptyCount = 0 ;
    	
    	final int maxRunning = 12 ; 
    	
    	final Object lock = new Object() ;

		public void onSpiderBegin(SuperSpider source) {
			
			runningSpider ++ ;
			source.priority = 0 ;
//			System.out.println("["+source.spiderID+"-"+SuperSpider.spiderCount+"]"+source.filePath+">"+runningSpider);
			
		}

		public void onSpiderEnd(SuperSpider source) {
			
			runningSpider -- ; 
			spiderlaunched -- ;
//			System.out.println("["+source.spiderID+"-"+SuperSpider.spiderCount+"]"+source.filePath+"<"+runningSpider);
			
		}
		
		public void run(){
			
			while(emptyCount  < 2){
				
				if(runningSpider<0 || runningSpider>maxRunning){
					System.err.println("Illegal running spider count : "+runningSpider);
					System.exit(1);
				}
				
				
//				int r = maxRunning-runningSpider ;
				int r = maxRunning-spiderlaunched ;
				
				int size = r<SpiderPool.this.size() ? r : SpiderPool.this.size() ;
				if(size<0)size=0 ;
				SuperSpider [] runs = new SuperSpider[size];
				
				//System.out.println("Preparing "+(size)+" Spiders of "+SpiderPool.this.size()+" in Queue and "+runningSpider+" running");
				
				int maxPriority = 0 ;
				
				if(SpiderPool.this.size()>0 ){
							
					synchronized(SpiderPool.this){
						if(runs.length >0){
							for(SuperSpider spider : SpiderPool.this){
								if(spider.state == SpiderState.IS_READY && spider.priority>=maxPriority){
									for(int i=runs.length - 1 ; i>0 ; i--){
										runs[i] = runs[i-1];
									}
									runs[0] = spider ;
								}
							}
						}
						
											
					}
						
					for(int i=0 ; i<runs.length ; i++){
						if(runs[i]!=null && runs[i].state == SpiderState.IS_READY){
							//System.out.println("Running "+(i+1)+" of "+runs.length);
							runs[i].addSpiderListener(this); 
							try{
								spiderlaunched++;
								runs[i].start();
							}catch (IllegalThreadStateException e){
								//System.err.println(runs[i].filePath+" ->" +e.getMessage() +" : "+runs[i].getState());
							}
						}
						
					}
					
					synchronized (SpiderPool.this) {
						for(SuperSpider spider : SpiderPool.this){
							if(spider.state ==  SpiderState.IS_READY){
								spider.priority++ ;
							}
						}
					}
					
					
				}
				else
				{
					emptyCount ++ ;
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
    	
    }
    
	
    
}
