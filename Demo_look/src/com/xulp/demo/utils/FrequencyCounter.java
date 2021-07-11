package com.xulp.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.xulp.binarytree.bean.BinarySearchTreeMap;
import com.xulp.demo.bean.BaseMap;

public class FrequencyCounter {

	public static void test(BinarySearchTreeMap map) {
		if (map == null)
			return;
		String[] data = getData(4);
		Stopwatch watch = new Stopwatch();
		for (String key : data) {
			map.put(key, map.get(key) + 1);
		}
		System.out.println("存储数据耗费的时间 : " + watch.elapsedTime());

	}

	public static void test(BaseMap<String, Integer> map) {
		if (map == null)
			return;
		String[] data = getData(4);
		Stopwatch watch = new Stopwatch();
		for (String key : data) {
			map.put(key, map.get(key) + 1);
		}
		System.out.println("存储数据耗费的时间 : " + watch.elapsedTime());

		String maxKey = "";
		for (Object key : map.keys()) {
			if (map.get(key + "") > map.get(maxKey)) {
				maxKey = key + "";
			}
		}
		System.out.println("出现频率最高的字符 : " + maxKey + " 次数 : " + map.get(maxKey));

	}

	// 按照无序的方式，将数据读取出来
	static String[] getData(int wordMinLen) {
		String[] ss = null;
		try {
			// 初始化文本数据
			File file = new File("/Users/xulp/Documents/学习资料/数据结构资料/算法第四版资料/algs4-data/tale.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));

			String info;
			StringBuffer sb = new StringBuffer();
			while ((info = br.readLine()) != null) {
				sb.append(info);
			}
			String[] data = sb.toString().split(" ");

			int bigWordNum = 0;
			for (String s : data) {
				if (s.length() > wordMinLen)
					bigWordNum++;
			}

			ss = new String[bigWordNum];
			int index = 0;
			for (String s : data) {
				if (s.length() > wordMinLen) {
					ss[index] = s;
					index++;
				}
			}
		} catch (Exception e) {
		}
		return ss;
	}

}
