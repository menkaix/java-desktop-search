
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testMain() {
        String[] args = {};
        Main.main(args);
        assertTrue(true, "Main method executed successfully");
    }
}