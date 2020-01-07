import org.junit.Assert;
import org.junit.Test;

import core.threads.FileSpider;
import core.threads.SpiderPool;



public class TestLocal {
	
	@Test
	public void passingTest() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void some_threads_should_launch() {
		SpiderPool.getInstance().execute(new FileSpider("C:\\Users\\mendrika\\Downloads\\old 20191228"));
		SpiderPool.getInstance().start();
		Assert.assertTrue(SpiderPool.getInstance().size()>0);
	}

}
