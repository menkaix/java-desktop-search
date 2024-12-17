import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import core.threads.FileSpider;
import core.threads.SpiderPool;

public class TestLocal {

	@Test
	public void passingTest() {
		Assertions.assertTrue(true);
	}

	@Test
	public void some_threads_should_launch() {
		SpiderPool.getInstance().execute(new FileSpider("C:\\Users\\mendrika\\Downloads\\old 20191228"));
		SpiderPool.getInstance().start();
		Assertions.assertTrue(SpiderPool.getInstance().size() > 0);
	}
}
