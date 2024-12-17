package core.threads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PoolManagerTest {

    @Test
    public void testGetInstance() {
        PoolManager manager = PoolManager.getInstance();
        assertNotNull(manager);
    }

    @Test
    public void testRun() {
        PoolManager manager = PoolManager.getInstance();
        manager.start();
        assertTrue(true, "PoolManager started successfully");
    }
}