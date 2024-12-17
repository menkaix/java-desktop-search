package core.threads.mimes;

import java.util.logging.Logger;
import core.threads.SuperSpider;

public class ZipSpider extends SuperSpider {

	private static final Logger logger = Logger.getLogger(ZipSpider.class.getName());

	public ZipSpider() {
	}

	public ZipSpider(String path) {
		super(path);
	}

	@Override
	public void eat() {
		logger.info("->" + filePath + " is a zip archive file");
	}
}
