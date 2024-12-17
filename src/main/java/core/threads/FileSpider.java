package core.threads;

import java.io.File;
import java.util.Vector;
import java.util.logging.Logger;

import core.events.SpiderListener;
import core.file.utils.MimeTypeFinder;

public class FileSpider extends SuperSpider {

	private static final Logger logger = Logger.getLogger(FileSpider.class.getName());

	public void eat() {
		logger.info("Processing file: " + filePath + " with MIME type: " + MimeTypeFinder.getMimeType(file));
	}

	public FileSpider() {
		super();
	}

	public FileSpider(String s) {
		super(s);
	}

}
