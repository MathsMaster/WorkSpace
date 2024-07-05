package com.mine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.spire.pdf.exporting.xps.schema.Break;

/*
 * 用于进行文本统计的工具类
 * 思路:
 * 	使用三个数据结构存放，一个ArrayList<String>用来存放5500个单词,一个ArrayList<String>用来存放从试卷中读取出来的所有字符串,
 * 	还有一个HashMap<String,Integer>中的key上对应着单词，其中的value则对应出现的次数
 * 	从真题文档中读取出所有的信息，然后进行提纯;
 * 	进行匹配，匹配时需要处理好几个问题，因为从文档上读取出来的单词，可能会加上各种后缀,特殊的还会省略一些东西
 * 		如on,set在各种单词里都会存在
 * 	最后进行打印，看看5500个单词有多少在真题中出现过，同时打印出每个单词出现的频率
 */
public class TextStatUtils {

	static ArrayList<String> allWordList, allPaperWordList;// 分别存放从标准单词集和真题集里读取出来的单词,真题集里的单词可能存在一定的重复
//	static HashMap<String, Integer> toRepeatMap;// 用于对真题集中去重复后的单词(看来不应该使用toRepeatMap,)
	static HashMap<String, Integer> resultMap; // 作为最终结果的map 以单词作为key,value是真题中具有相关性的数量
	final static String fileName_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一真题.txt";
	static final String fileName_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二真题.txt";
	final static String fileName_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009真题.txt";

	public static void main(String[] args) {

		// 读取数据
		init();
		readAllStandardWord();// 读取出5500个单词
		readWordByTestPaper(fileName_1);// 读取试卷中的单词
		readWordByTestPaper(fileName_2);// 读取试卷中的单词
		readWordByTestPaper(fileName_3);// 读取试卷中的单词

		// 统计数据
		System.out.println("标准单词数量 : " + allWordList.size());
		System.out.println("最近20年的英语真题单词总量 : " + allPaperWordList.size());
		statisWord(allPaperWordList, "真题集中所有单词");
//		System.out.println("一共存储来多少个key : " + map.keySet().size());
//		calcuateWordLength(allWordList);//对5500单词长度的统计
//		calcuateWordLength(allPaperWordList);//对真题的单词长度的统计

		// 操作数据进行匹配
		matchAllWord();// 进行

		// 输出结果
		outPutResult();
	}

	static void outPutResult() {
		IOUtils.outPutData("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/统计后匹配上的部分.txt", resultMap);
		System.out.println("没有匹配上的单词数量 : " + allPaperWordList.size());
		IOUtils.outPutData("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/剩下的没有匹配上的部分.txt", allPaperWordList);
//		statisWord(allPaperWordList, "没有匹配上的单词");

	}

	/*
	 * 统计传进来的list中有多少不重复的单词
	 */
	static void statisWord(ArrayList<String> list, String tag) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// 统计真题集里不重复的单词数量
		for (String word : list) {
			map.put(word, map.get(word) == null ? 1 : map.get(word) + 1);
		}
		System.out.println(tag + " 除重后数量 : " + map.keySet().size());
		IOUtils.outPutData("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/" + tag + "-统计.txt", map);
	}

	/*
	 * 计算出真题集中单词的各个长度，以及对应长度的单词的数量
	 */
	static void calcuateWordLength(ArrayList<String> list) {
		for (String word : list) {
			Object obj = resultMap.get(word.length() + "");
			int num = obj == null ? 0 : (int) obj;
			resultMap.put(word.length() + "", ++num);
		}
		for (String key : resultMap.keySet()) {
			System.out.println("单词长度 : " + key + " 数量: " + resultMap.get(key));
		}

	}

	/*
	 * 需求: 将去除重复后的单词，与单词集中的一个单词对应上 进行精确匹配的思路: 将真题中提取出来的所有单词，挨个的和5500个单词进行比较第一轮
	 * 1，直接比较下，看是否相互包含，包含的话，就以5500个单词中的词作为key ,将value增加
	 * 不包含的话，就去除后缀进行三轮包含性质的比较，看下剩余部分是否包含 还不包含的话，就去除前缀进行两轮包含的比较，看下剩余部分是否包含
	 * 如果还不包含的话，就当作是个新的单词作为key加入map中
	 * 
	 * 这样最后resultMap中的key可以分为两类，一类是5500单词中的部分，一类是5500单词外面的部分
	 */
	static void matchAllWord() {

		String standardWord;// 对应着标准单词集里的单词
		String paperWord;// 对应着真题集中的单词

		// 将真题集中的单词取出来，与map中的key进行粗略的匹配
		for (int i = allPaperWordList.size() - 1; i >= 0; i--) {
			paperWord = allPaperWordList.get(i);
			for (int j = 0; j < allWordList.size(); j++)// 取出标准单词
			{
				standardWord = allWordList.get(j);
				if (compareWord(standardWord, paperWord)) {
					if (resultMap.get(standardWord) == null)// 表示该单词还没进行存储
					{
						resultMap.put(standardWord, 1);
					} else {
						int value = resultMap.get(standardWord);
						resultMap.put(standardWord, value + 1);
					}
					allPaperWordList.remove(i);// 如果匹配上了，就从真题集中删除对应的元素
					break;// 匹配上后,就跳出内层循环，使用下个单词继续和5500个单词比较
				}
			}
		}

		// 将toRepeatMap中的单词取出来，与map中的key进行粗略的匹配
//		for (int i = allPaperWordList.size() - 1; i >= 0; i--) {// 这是第一轮匹配
//			paperWord = allPaperWordList.get(i);
//			standardWord = compareWord(paperWord);
//			if (standardWord == null)// 表示没有匹配上
//			{
//
//			} else {// 表示匹配上了，并返回了对应的标准单词
//				int num = (resultMap.get(standardWord) == null) ? 0 : resultMap.get(standardWord);
//				resultMap.put(standardWord, ++num);
//				// 对真题集中的单词进行删除
//				allPaperWordList.remove(i);
//			}
//		}
//
//		// 进行第二轮操作
//		for (int i = allPaperWordList.size() - 1; i >= 0; i--) {// 这是第二轮匹配
//			paperWord = allPaperWordList.get(i);
////			matchWord = matchWord.substring(ma);//对拿到的匹配单词进行拆分
//			standardWord = compareWord(paperWord);
//			if (standardWord == null)// 表示没有匹配上
//			{
//
//			} else {// 表示匹配上了，并返回了对应的标准单词
//				int num = (resultMap.get(standardWord) == null) ? 0 : resultMap.get(standardWord);
//				resultMap.put(standardWord, ++num);
//				// 对真题集中的单词进行删除
//				allPaperWordList.remove(i);
//			}
//		}

	}

	/*
	 * 将传进来的单词与5500个词进行一轮比较；包含的话，就直接返回单词集中对应的词 word : 传进来的字符串，可能是经过削减的
	 * 
	 * @return : 返回的是标准单词集中的字符串；如果没有匹配上，则直接返回null
	 */
	static String compareWord(String word) throws NullPointerException {
		if (TextUtils.isEmpty(word))
			return null;
		for (String key : allWordList) {
			if (key.contains(word) || word.contains(key))
				return key;
		}
		return null;
	}

	/*
	 * 匹配算法，如果之后要继续优化的话，只需修改这里即可
	 */
	static boolean compareWord(String standardWord, String matchedWord) throws NullPointerException {
		if (TextUtils.isEmpty(standardWord) || TextUtils.isEmpty(matchedWord))
			return false;

		// 传进来的单词首先进行大小写的处理
		matchedWord = matchedWord.toLowerCase();
		standardWord = standardWord.toLowerCase();

		if (standardWord.equals(matchedWord))// 进行严格的匹配
			return true;

		// ing,ed,ily,s,es, im,un
		// 如果严格的匹配不成功的话，再进行精细的匹配
		if (matchedWord.endsWith("ing") || matchedWord.endsWith("ily") || matchedWord.endsWith("ed")
				|| matchedWord.endsWith("es") || matchedWord.endsWith("s") || matchedWord.startsWith("im")
				|| matchedWord.startsWith("un")) {

			if (matchedWord.endsWith("ing") || matchedWord.endsWith("ily")) {
				if (matchedWord.length() <= 4)
					return false;
				matchedWord = matchedWord.substring(0, matchedWord.length() - 4);
			} else if (matchedWord.endsWith("ed") || matchedWord.endsWith("es")) {
				if (matchedWord.length() <= 3)
					return false;
				matchedWord = matchedWord.substring(0, matchedWord.length() - 3);
			} else if (matchedWord.endsWith("s")) {
				matchedWord = matchedWord.substring(0, matchedWord.length() - 1);
			} else if (matchedWord.startsWith("im") || matchedWord.startsWith("un")) {
				matchedWord = matchedWord.substring(2, matchedWord.length());
			}
			if (standardWord.contains(matchedWord))// 进行匹配
				return true;
		}

		return false;
	}

	/*
	 * 通过文本文件读取出所有的真题信息
	 */
	static void readWordByTestPaper(String fileName) {
		FileInputStream fis = null;
		BufferedReader br = null;// 用于读取词汇的输入流

		String info = "";
		String[] strArray;
		try {
			File fInput = new File(fileName);

			// 读取词汇以及读取单词意思
			if (fInput.isFile() && fInput.canRead()) {
				br = new BufferedReader(new FileReader(fInput));
				while ((info = br.readLine()) != null) {
					if (TextUtils.isEmpty(info) || !TextUtils.isContainEnChar(info))// 过滤空行以及没有包含英文字符的部分
						continue;

					strArray = info.split(" ");
//					// 将单词分离出来
					if (strArray != null && strArray.length > 1) {
						for (String str : strArray) {
							// 在这里完成过滤操作(20 points) SHEET. comments. 3)​give briefly, “Whether companies”
							// text? 40. ​[D] (OECD) tax),
							str = TextUtils.filterString(str);
							allPaperWordList.add(str);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (fis != null)
					fis.close();
				if (br != null)
					br.close();
			} catch (Exception e) {

			}

		}
	}

	static void init() {
		allWordList = new ArrayList<>();
		allPaperWordList = new ArrayList<String>();
		resultMap = new HashMap<>();
//		toRepeatMap = new HashMap<>();
	}

	/*
	 * 读取出所有的5500单词存放在List<String>中
	 */
	static void readAllStandardWord() {
		FileInputStream fis = null;
		BufferedReader br = null;// 用于读取词汇的输入流

		String info = "";
		String[] strArray;
		try {
			File fInput = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");

			// 读取词汇以及读取单词意思
			if (fInput.isFile() && fInput.canRead()) {
				br = new BufferedReader(new FileReader(fInput));
				while ((info = br.readLine()) != null) {

					strArray = info.split(" ");// 拆分后的样式: 5376 whistle n.口哨,汽笛;口哨声,汽笛声 v.吹口哨;鸣笛
					// 将单词分离出来
					if (strArray != null && strArray.length > 1) {
						allWordList.add(strArray[1]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (fis != null)
					fis.close();
				if (br != null)
					br.close();
			} catch (Exception e) {

			}

		}
	}

}
