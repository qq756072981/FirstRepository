package com.itheima.crawler.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownloadUtil {

	public static void download(String folderPath, String picUrl) throws Exception {
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		// https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.jpg
		// p480747492.jpg
		String regex = ".*/(.*)";
		String picName = picUrl.replaceAll(regex, "$1");

		File file = new File(folder, picName);

		URL url = new URL(picUrl);
		InputStream inputStream = url.openStream();
		OutputStream outputStream = new FileOutputStream(file);

		BufferedInputStream bis = new BufferedInputStream(inputStream, 1024 * 8);
		BufferedOutputStream bos = new BufferedOutputStream(outputStream);

		int len;
		while ((len = bis.read()) != -1) {
			bos.write(len);
		}

		bos.close();
		bis.close();

	}

}
