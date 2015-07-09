package core.events;

import java.util.EventListener;

import core.threads.FileSpider;

public interface SpiderListener extends EventListener {
	
	public void onSpiderBegin(FileSpider source);
	public void onSpiderEnd(FileSpider source);

}
