package com.mine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;

public class IOUtils {

	public static void outPutData(String fileName, HashMap<String, Integer> map) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		String info = "";
		try {
			File fOut = new File(fileName);

			if (!fOut.exists()) {
				fOut.createNewFile();
			} else {
				fOut.delete();
				fOut.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(fOut));

			for (String key : map.keySet()) {
				bw.write(key + " 数量  " + map.get(key));
				bw.write("\n");
				bw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (fos != null)
					fos.close();
				if (bw != null)
					bw.close();
			} catch (Exception e) {

			}
		}
	}
}
