package core.threads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PoolManager extends Thread {

	private static final Logger logger = Logger.getLogger(PoolManager.class.getName());
	int emptyTry = 0;

	public void run() {
		try {
			while (emptyTry < 10) {
				int n = SpiderPool.getInstance().size();
				int i = 0;
				if (n > 0) {
					for (SuperSpider spider : SpiderPool.getInstance()) {
						if (spider.state == SpiderState.IS_DONE) {
							i++;
						}
					}
					if (i / n > 0.5) {
						SpiderPool.getInstance().defrag();
					}
				} else {
					emptyTry++;
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "PoolManager thread interrupted", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occurred in PoolManager", e);
		}
	}

	private static PoolManager instance;

	public static PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager();
		}

		return instance;
	}

	private PoolManager() {
		super();
	}

}
