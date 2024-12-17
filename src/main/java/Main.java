import core.threads.FileSpider;
import core.threads.SpiderPool;


public class Main {

	public static String ROOT_DIRECTORY =  "C:\\Users\\Mendrika\\Documents\\Projets Saison 8 - Kobayashi\\PLV\\ACS 2022\\ACS 2\\Trunk\\Activ-screen\\Assets" ;//"D:\\tmp" ;
	//public static String ROOT_DIRECTORY =  "D:\\tmp" ;
	
	public static void main(String[] args) {
		
		
		System.out.println("Hello world");
		SpiderPool.getInstance().execute(new FileSpider(ROOT_DIRECTORY));
		SpiderPool.getInstance().start();
		
	}

}
