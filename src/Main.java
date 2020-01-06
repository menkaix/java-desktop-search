import core.threads.FileSpider;
import core.threads.SpiderPool;


public class Main {

	public static void main(String[] args) {
		
		
		System.out.println("Hello world");
		SpiderPool.getInstance().execute(new FileSpider("C:\\Users\\mendrika\\Downloads\\old 20191228"));
		SpiderPool.getInstance().start();
		
	}

}
