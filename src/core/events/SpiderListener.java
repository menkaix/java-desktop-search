package core.events;

import java.util.EventListener;

import core.threads.SuperSpider;

public interface SpiderListener extends EventListener {
	
	public void onSpiderBegin(SuperSpider source);
	public void onSpiderEnd(SuperSpider source);

}
