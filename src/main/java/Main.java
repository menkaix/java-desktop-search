import java.util.logging.Level;
import java.util.logging.Logger;
import core.threads.FileSpider;
import core.threads.SpiderPool;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	public static String ROOT_DIRECTORY = "F:\\tmp";// "D:\\tmp" ;
	// public static String ROOT_DIRECTORY = "D:\\tmp" ;

	public static void main(String[] args) {
		try {
			System.out.println("Hello world");
			SpiderPool.getInstance().execute(new FileSpider(ROOT_DIRECTORY));
			SpiderPool.getInstance().start();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occurred in the main method", e);
		}
	}
}
