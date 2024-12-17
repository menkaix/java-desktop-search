package core.file.utils;

import java.io.File;

public class DirectoryTypeFinder {

	public static String getDirectoryType(File[] files) {
		for (File f : files) {
			String fileName = f.getName();

			switch (fileName) {
				case "pom.xml":
					return "MAVEN_PROJECT";
				case "package.json":
					return "NODE_PROJECT";
				case "ProjectSettings":
					for (File f1 : files) {
						if (f1.getName().equals("Assets")) {
							return "UNITY_PROJECT";
						}
					}
					break;
				case "build.gradle":
				case "settings.gradle":
					return "GRADLE_PROJECT";
				case "requirements.txt":
					return "PYTHON_PROJECT";
				case "Gemfile":
					return "RUBY_PROJECT";
				case "composer.json":
					return "PHP_PROJECT";
				case "Cargo.toml":
					return "RUST_PROJECT";
				case "go.mod":
					return "GO_PROJECT";
				case "Makefile":
					return "C_PROJECT";
				case "CMakeLists.txt":
					return "CMAKE_PROJECT";
				case "pubspec.yaml":
					return "DART_PROJECT";
				default:
					break;
			}
		}
		return "UNKNOWN_PROJECT";
	}

	public static final String UNKNOWN = "unknown";
	public static final String MAVEN_PROJECT = "project/maven";
	public static final String NODE_PROJECT = "project/node";
	public static final String UNITY_PROJECT = "project/unity3D";

}
