package com.mine.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class IOUtils {

	/*
	 * 按顺序对map中的进行输出
	 */
	public static void outPutData(String fileName, HashMap<String, Integer> map) {
		
		//对map中的数据进行排序,value值高的先打印
		
		
		FileOutputStream fos = null;
		BufferedWriter bw = null;
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
				bw.write("数量 : " + map.get(key) +" : "+key);
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
	
	public static void outPutData(String fileName, ArrayList<String> list) {
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

			for (String key : list) {
				bw.write(key);
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
