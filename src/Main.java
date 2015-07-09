import core.file.FileSpider;
import core.threads.SpiderPoolInternal;


public class Main {

	public static void main(String[] args) {
		
		
		System.out.println("Hello world");
		SpiderPoolInternal.getInstance().execute(new FileSpider());
		System.out.println("Done");
	}

}
