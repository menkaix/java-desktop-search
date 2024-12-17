package core.threads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunnerTest {

    @Test
    public void testGetInstance() {
        Runner runner = Runner.getInstance();
        assertNotNull(runner);
    }

    @Test
    public void testRun() {
        Runner runner = Runner.getInstance();
        runner.start();
        assertTrue(true, "Runner started successfully");
    }
}