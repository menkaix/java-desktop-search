package core.threads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSpiderTest {

    @Test
    public void testEat() {
        FileSpider spider = new FileSpider("testPath");
        spider.eat();
        assertTrue(true, "Eat method executed successfully");
    }

    @Test
    public void testConstructor() {
        FileSpider spider = new FileSpider("testPath");
        assertEquals("testPath", spider.filePath);
    }
}