package com.xulp.search.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.xulp.basic.VisualAccumulator;
import com.xulp.search.BinarySearchST;
import com.xulp.search.UnOrderSearchST;
import com.xulp.utils.Stopwatch;

/**
 * 用来计算查找算法模型的成本
 * 
 * @author xulp
 *
 */
public class FrequencyCounter {

	public static void main(String[] args) {

		testBinaryOrderST();
		testUnOrderST();
	}
	
	//基于二分查找数组构建的符号表
	static void testBinaryOrderST()
	{
		String[] ss = getData(0);
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(150000);
		System.out.println("二分查找符号表 准备的数据量大小 : " + ss.length);
		// 进行数据的存储
		Stopwatch watch = new Stopwatch();
		for (String key : ss) {
			if (st.contains(key)) {
				Integer value = st.get(key);
				st.put(key, value + 1);
			} else {
				st.put(key, 1);
			}
		}
		System.out.println("二分查找符号表 存储耗费时间 : " + watch.elapsedTime());
	}
	
	//测试链表构建的符号表
	static void testUnOrderST()
	{
		String[] ss = getData(0);
		UnOrderSearchST<String, Integer> st = new UnOrderSearchST<String, Integer>();
		System.out.println("准备的数据量大小 : " + ss.length);
		// 进行数据的存储
//		VisualAccumulator va = new VisualAccumulator(ss.length, ss.length);
		Stopwatch watch = new Stopwatch();
		for (String key : ss) {
			if (st.contains(key)) {
				Integer value = st.get(key);
				int count = st.put(key, value + 1);
//				va.addDataValue(count);
			} else {
				int count = st.put(key, 1);
//				va.addDataValue(count);
			}
		}
		System.out.println("存储耗费时间 : " + watch.elapsedTime());
		// 找出出现最大次数的词
		String maxKey = " ";
		st.put(maxKey, 0);
		for (String key : st.keys()) {
			if (st.get(key) > st.get(maxKey))
				maxKey = key;
		}

		Stopwatch watch2 = new Stopwatch();
		int maxValue = st.get(maxKey);
		System.out.println("查找最大值耗费时间 : " + watch2.elapsedTime()+" 最大的 " + maxKey + " : " + maxValue);
	}
	
	//按照无序的方式，将数据读取出来
	static String[] getData(int wordMinLen) {
		String[] ss = null;
		try {
			// 初始化文本数据
			File file = new File("/Users/xulp/Documents/学习资料/书籍/算法第四版/algs4-data/tale.txt");
//			File file = new File("/Users/xulp/Documents/学习资料/书籍/算法第四版/algs4-data/tinyTale.txt");
//		File file = new File("/Users/xulp/Documents/学习资料/书籍/算法第四版/algs4-data/leipzig1M.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));

			String info;
			StringBuffer sb = new StringBuffer();
			while ((info = br.readLine()) != null) {
				sb.append(info);
			}
			String [] data = sb.toString().split(" ");
			
			int bigWordNum = 0;
			for(String s : data)
			{
				if(s.length() > wordMinLen)
					bigWordNum++;
			}
			
			ss = new String[bigWordNum];
			int index = 0;
			for(String s : data)
			{
				if(s.length() > wordMinLen)
				{
					ss[index] = s;
					index++;
				}
			}
		} catch (Exception e) {
		}
		return ss;
	}
}
