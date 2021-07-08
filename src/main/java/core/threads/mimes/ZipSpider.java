package core.threads.mimes;

import core.threads.SuperSpider;

public class ZipSpider extends SuperSpider {

	public ZipSpider() {
		
	}

	public ZipSpider(String path) {
		super(path);
		
	}

	@Override
	public void eat() {
		System.out.println("->"+filePath+" is a zip archive file");

	}

}
