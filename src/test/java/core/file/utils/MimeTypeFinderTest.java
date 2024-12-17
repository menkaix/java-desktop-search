
package core.file.utils;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MimeTypeFinderTest {

    @Test
    public void testGetMimeTypeForFile() {
        File file = new File("path/to/your/testfile.txt");
        String mimeType = MimeTypeFinder.getMimeType(file);
        assertEquals("text/plain", mimeType);
    }

    @Test
    public void testGetMimeTypeForDirectory() {
        File directory = new File("path/to/your/testdirectory");
        String mimeType = MimeTypeFinder.getMimeType(directory);
        assertEquals("directory", mimeType);
    }

    @Test
    public void testGetMimeTypeForUnknownFile() {
        File file = new File("path/to/your/unknownfile.unknown");
        String mimeType = MimeTypeFinder.getMimeType(file);
        assertEquals("unknown", mimeType);
    }

    @Test
    public void testGetMimeTypeForNonExistentFile() {
        File file = new File("path/to/your/nonexistentfile.txt");
        String mimeType = MimeTypeFinder.getMimeType(file);
        assertEquals("Error", mimeType);
    }
}