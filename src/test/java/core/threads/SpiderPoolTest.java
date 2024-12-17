package core.threads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpiderPoolTest {

    @Test
    public void testGetInstance() {
        SpiderPool pool = SpiderPool.getInstance();
        assertNotNull(pool);
    }

    @Test
    public void testExecute() {
        SpiderPool pool = SpiderPool.getInstance();
        SuperSpider spider = new FileSpider("testPath");
        pool.execute(spider);
        assertEquals(SpiderState.IS_READY, spider.state);
    }
}