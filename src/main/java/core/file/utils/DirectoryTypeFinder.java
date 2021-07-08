package core.file.utils;

import java.io.File;

public class DirectoryTypeFinder {
	
	
	
	public static String getDirectoryType(File[] files) {
		
		for(File f : files) {
			
			if(f.getName().equals("pom.xml")) {
				return MAVEN_PROJECT ;
			}
			else if(f.getName().equals("package.json")) {
				return NODE_PROJECT ;
			}
			else if(f.getName().equals("ProjectSettings")) {
				
				for(File f1 : files) {
					if(f1.getName().equals("Assets"))
						return UNITY_PROJECT ;
				}
				
				continue ;
				
			}
			
		}		
		
		
		return UNKNOWN ;
	}
	
	public static final String UNKNOWN = "unknown" ;
	public static final String MAVEN_PROJECT = "project/maven" ;
	public static final String NODE_PROJECT = "project/node" ;
	public static final String UNITY_PROJECT = "project/unity3D" ;

}
