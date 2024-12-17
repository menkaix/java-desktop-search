package core.threads;

import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import core.events.SpiderListener;
import core.file.utils.DirectoryTypeFinder;
import core.file.utils.MimeTypeFinder;
import core.threads.mimes.ZipSpider;

public abstract class SuperSpider extends Thread {

	private static final Logger logger = Logger.getLogger(SuperSpider.class.getName());

	public abstract void eat();

	private Vector<SpiderListener> listeners = new Vector<SpiderListener>();

	public static long spiderCount = 0;

	public long spiderID = 0;

	public String filePath;
	public SpiderState state;
	public int priority = 0;
	public File file;

	protected void breed(File f) {
		try {
			String mimeType = MimeTypeFinder.getMimeType(file);
			if (f.getAbsolutePath().endsWith(".zip") || f.getAbsolutePath().endsWith(".rar")) {
				SpiderPool.getInstance().execute(new ZipSpider(f.getAbsolutePath()));
			} else {
				SpiderPool.getInstance().execute(new FileSpider(f.getAbsolutePath()));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occurred while breeding spiders for file: " + f.getAbsolutePath(), e);
		}
	}

	private synchronized void fireSpiderEnd() {

		for (SpiderListener l : listeners) {
			l.onSpiderEnd(this);
		}

	}

	private synchronized void fireSpiderBegin() {

		for (SpiderListener l : listeners) {
			l.onSpiderBegin(this);
		}

	}

	public void run() {

		file = new File(filePath);
		if (!filePath.contains("$") && !filePath.contains("C:\\Windows") && !filePath.contains("C:\\Config.Msi")
				&& !filePath.startsWith(".")) {
			try {
				if (file.isDirectory()) {

					File[] children = file.listFiles();

					String directoryType = DirectoryTypeFinder.getDirectoryType(children);

					if (directoryType.equals(DirectoryTypeFinder.UNKNOWN)) {

						// System.out.println("unknown directory "+filePath+" => breeding");

						for (File f : children) {

							breed(f);

						}
					} else {
						// System.out.println("-->"+directoryType+" => "+filePath);
						// eat() ;
					}

				} else {

					eat();

				}
			} catch (NullPointerException e) {
				logger.log(Level.SEVERE, "NullPointerException in file: " + filePath, e);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "An error occurred while processing file: " + filePath, e);
			}
		}

		fireSpiderEnd();
		state = SpiderState.IS_DONE;
	}

	public synchronized void start() {
		if (state == SpiderState.IS_READY) {

			state = SpiderState.IS_ACTIVE;

			fireSpiderBegin();

			super.start();

		} else {
			System.err.println("Launching " + state.toString() + " Spider : " + filePath);
		}
	}

	public synchronized void addSpiderListener(SpiderListener spiderlistener) {
		listeners.add(spiderlistener);
	}

	public SuperSpider() {
		this(".");
	}

	public SuperSpider(String path) {
		filePath = path;
		spiderID = ++spiderCount;

	}

}
