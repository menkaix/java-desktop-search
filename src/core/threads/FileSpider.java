/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.threads;

import java.io.File;



/**
 *
 * @author mendrika
 */
public class FileSpider extends Thread{
    
    private String filePath ;
    
    public FileSpider(){
        this(".");
    }
    
    public FileSpider(String path){
        filePath = path ;
    }
    
    public void run(){
        
        File file = new File(filePath);
        
        if(file.isDirectory()){
            File[] children = file.listFiles();
            for(File f : children){
                
            	SpiderPoolInternal.getInstance().execute(new FileSpider(f.getAbsolutePath())) ;
                
            }
        }
        else {
            eat() ;
        }
        
    }
    
    public void eat(){
    	System.out.println(filePath);
    }
    
}
