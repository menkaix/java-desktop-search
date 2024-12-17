
package core.file.utils;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectoryTypeFinderTest {

    @Test
    public void testGetDirectoryType_MavenProject() {
        File[] files = { new File("pom.xml") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("MAVEN_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_NodeProject() {
        File[] files = { new File("package.json") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("NODE_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_UnityProject() {
        File[] files = { new File("ProjectSettings"), new File("Assets") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("UNITY_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_GradleProject() {
        File[] files = { new File("build.gradle") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("GRADLE_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_PythonProject() {
        File[] files = { new File("requirements.txt") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("PYTHON_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_RubyProject() {
        File[] files = { new File("Gemfile") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("RUBY_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_PhpProject() {
        File[] files = { new File("composer.json") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("PHP_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_RustProject() {
        File[] files = { new File("Cargo.toml") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("RUST_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_GoProject() {
        File[] files = { new File("go.mod") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("GO_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_CProject() {
        File[] files = { new File("Makefile") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("C_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_CMakeProject() {
        File[] files = { new File("CMakeLists.txt") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("CMAKE_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_DartProject() {
        File[] files = { new File("pubspec.yaml") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("DART_PROJECT", result);
    }

    @Test
    public void testGetDirectoryType_UnknownProject() {
        File[] files = { new File("unknown.file") };
        String result = DirectoryTypeFinder.getDirectoryType(files);
        assertEquals("UNKNOWN_PROJECT", result);
    }
}