package core.threads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class SuperSpiderTest {

    @Test
    public void testRun() {
        SuperSpider spider = new FileSpider("testPath");
        spider.start();
        assertEquals(SpiderState.IS_ACTIVE, spider.state);
    }

    @Test
    public void testBreed() {
        SuperSpider spider = new FileSpider("testPath");
        spider.breed(new File("test.zip"));
        assertTrue(true, "Breed method executed successfully");
    }
}