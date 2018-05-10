package com.coska.lab.selenium.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileUtil {

	public static void makeFile(String content, String filePath) throws Exception{
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			
			fos = new FileOutputStream(filePath);
			bw = new BufferedWriter(
					new OutputStreamWriter(fos, "UTF-8")
				);
			
			bw.write(content);
			
			Log.trace("maden file path : "+ filePath);
			
		} finally {
			bw.close();
			fos.close();
		}
	}
}
