package core.threads;

public class PoolManager extends Thread{
	
	int emptyTry = 0 ;
	
	public void run(){
		
		while(emptyTry<10){
			//System.out.println("Managing");
			int n = SpiderPool.getInstance().size();
			int i = 0 ;
			if(n>0){
				for(SuperSpider spider : SpiderPool.getInstance() ){
	    			if(spider.state == SpiderState.IS_DONE){
	    				i++ ;
	    			}
	    		}
				if(i/n > 0.5){
					SpiderPool.getInstance().defrag();
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
	
private static PoolManager instance ;
	
	public static PoolManager getInstance(){
		if(instance == null){
			instance = new PoolManager();
		}
		
		return instance ;
	}
	
	private PoolManager(){
		super() ;
	}
	
	
}
