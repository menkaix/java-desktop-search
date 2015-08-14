package core.threads;

import core.events.SpiderListener;

public class Runner extends Thread implements SpiderListener{
	
	int spiderlaunched = 0 ;
	int runningSpider = 0 ;
	int emptyCount = 0 ;
	
	final int maxRunning = 12 ; 
	
	final Object lock = new Object() ;
	

	private static Runner instance ;


	private Runner(){
		super() ;
	}

	public static Runner getInstance(){
		if(instance == null){
			instance = new Runner();
			instance.runnerID = ++runnerCount ;
		}
		
		return instance ;
	}

	public void onSpiderBegin(SuperSpider source) {
		
		runningSpider ++ ;
		System.out.println(runnerID+"["+source.spiderID+"-"+SuperSpider.spiderCount+"]"+source.filePath+">"+runningSpider+" ("+Thread.activeCount()+")");
		
	}

	public void onSpiderEnd(SuperSpider source) {
		
		runningSpider -- ; 
		spiderlaunched -- ;
		System.out.println(runnerID+"["+source.spiderID+"-"+SuperSpider.spiderCount+"]"+source.filePath+"<"+runningSpider+" ("+Thread.activeCount()+")");
		
	}
	
	public void run(){
		
		while(emptyCount  < 2){
			
			if(runningSpider<0 || runningSpider>maxRunning){
				System.err.println("Illegal running spider count : "+runningSpider);
				System.exit(1);
			}
			
			
//			int r = maxRunning-runningSpider ;
			int r = maxRunning-spiderlaunched ;
			
			int size = r<SpiderPool.getInstance().size() ? r : SpiderPool.getInstance().size() ;
			if(size<0)size=0 ;
			SuperSpider [] runs = new SuperSpider[size];
			
			//System.out.println("Preparing "+(size)+" Spiders of "+SpiderPool.this.size()+" in Queue and "+runningSpider+" running");
			
			int maxPriority = 0 ;
			
			if(SpiderPool.getInstance().size()>0 ){
						
				synchronized(SpiderPool.getInstance()){
					if(runs.length >0){
						for(SuperSpider spider : SpiderPool.getInstance()){
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
				
				synchronized (SpiderPool.getInstance()) {
					for(SuperSpider spider : SpiderPool.getInstance()){
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
	
	public static long runnerCount = 0 ;
	public  long runnerID = 0 ;
	
	
}