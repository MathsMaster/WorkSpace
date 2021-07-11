package com.mine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

/*
 * 用于进行文本统计的工具类
 * 思路:
 * 	使用三个数据结构存放，一个ArrayList<String>用来存放5500个单词,一个ArrayList<String>用来存放从试卷中读取出来的所有字符串,
 * 	还有一个HashMap<String,Integer>中的key上对应着单词，其中的value则对应出现的次数
 * 	从真题文档中读取出所有的信息，然后进行拆分;
 * 	进行匹配，匹配时需要处理好几个问题，因为从文档上读取出来的单词，可能会加上各种后缀,特殊的还会省略一些东西
 * 		如on,set在各种单词里都会存在
 * 	最后进行打印，看看5500个单词有多少在真题中出现过，同时打印出每个单词出现的频率
 */
public class TextStatUtils {

	static ArrayList<String> allWordList, allPaperWordList;// 分别存放从标准单词集和真题集里读取出来的单词
	static HashMap<String, Integer> map;// 以单词作为key,计算其数量value
	final static String fileName_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一真题.txt";
	static final String fileName_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二真题.txt";
	final static String fileName_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009真题.txt";

	public static void main(String[] args) {
		
		//读取数据
		init();
		readAllWord();// 读取出5500个单词
		readWordByTestPaper(fileName_1);// 读取试卷中的单词
		readWordByTestPaper(fileName_2);// 读取试卷中的单词
//		readWordByTestPaper(fileName_3);// 读取试卷中的单词
		
		//统计数据
		statisOutputPaperWord();
		System.out.println("最单词集中单词数量 : " + allWordList.size());
		System.out.println("最近10年的英语真题单词数量 : " + allPaperWordList.size());
//		System.out.println("一共存储来多少个key : " + map.keySet().size());
//		calcuateWordLength(allWordList);//对5500单词长度的统计
//		calcuateWordLength(allPaperWordList);//对真题的单词长度的统计
		
		//操作数据
//		matchAllWord();// 进行
//		readWordByPDF();

	}
	
	/*
	 * 统计真题里的各种单词
	 */
	static void statisPaperWord()
	{
		
	}

	//统计单词输出
	static void statisOutputPaperWord() {
		HashMap<String, Integer> hashMap = new HashMap<>();
		for (String word : allPaperWordList) {
			hashMap.put(word, hashMap.get(word) == null ? 1 : hashMap.get(word) + 1);
		}
		System.out.println("真题集中统计的不重复的单词数量 : "+hashMap.keySet().size());
		IOUtils.outPutData("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/对真题单词长度的统计.txt", hashMap);

	}

	/*
	 * 计算出真题集中单词的各个长度，以及对应长度的单词的数量
	 */
	static void calcuateWordLength(ArrayList<String> list) {
		for (String word : list) {
			Object obj = map.get(word.length() + "");
			int num = obj == null ? 0 : (int) obj;
			map.put(word.length() + "", ++num);
		}
		for (String key : map.keySet()) {
			System.out.println("单词长度 : " + key + " 数量: " + map.get(key));
		}

	}

	/*
	 * 需求: 将尽可能多的单词，与单词集中的一个单词对应上 进行精确匹配的思路: 将真题中提取出来的所有单词，挨个的和5500个单词进行比较第一轮
	 * 1，直接比较下，看是否相互包含，包含的话，就以5500个单词中的词作为key ,将value + 1
	 * 不包含的话，就去除后缀进行三轮包含性质的比较，看下剩余部分是否包含 还不包含的话，就去除前缀进行两轮包含的比较，看下剩余部分是否包含
	 * 如果还不包含的话，就当作是个新的单词作为key加入map中
	 * 
	 * 这样最后map中的key可以分为两类，一类是5500单词中的部分，一类是5500单词外面的部分
	 */
	static void matchAllWord() {

		String standardWord;// 对应着标准单词集里的单词
		String matchWord;
		// 将wordPaperLis中的单词取出来，与map中的key进行粗略的匹配
		for (int i = allPaperWordList.size() - 1; i >= 0; i--) {// 这是第一轮匹配
			matchWord = allPaperWordList.get(i);
			standardWord = compareWord(matchWord);
			if (standardWord == null)// 表示没有匹配上
			{

			} else {// 表示匹配上了，并返回了对应的标准单词
				int num = (map.get(standardWord) == null) ? 0 : map.get(standardWord);
				map.put(standardWord, ++num);
				// 对真题集中的单词进行删除
				allPaperWordList.remove(i);
			}
		}

		// 进行第二轮操作
		for (int i = allPaperWordList.size() - 1; i >= 0; i--) {// 这是第二轮匹配
			matchWord = allPaperWordList.get(i);
//			matchWord = matchWord.substring(ma);//对拿到的匹配单词进行拆分
			standardWord = compareWord(matchWord);
			if (standardWord == null)// 表示没有匹配上
			{

			} else {// 表示匹配上了，并返回了对应的标准单词
				int num = (map.get(standardWord) == null) ? 0 : map.get(standardWord);
				map.put(standardWord, ++num);
				// 对真题集中的单词进行删除
				allPaperWordList.remove(i);
			}
		}

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

	/*
	 * 读取出所有pdf上的信息
	 */
	static void readWordByPDF() {
		StringBuffer sb;
		try {
			for (int i = 2018; i <= 2018; i++) {
				String fileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/2018英语一真题.pdf";
//				String fileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2019年考研英语一真题.pdf";
//				sb = readSinglePDFByPDFBox(fileName);
				sb = readSinglePDFBySpirePdf(fileName);
				System.out.println(i + "年的真题 : " + sb.toString());
				System.out.println("\n\n\n");
			}
		} catch (Exception e) {

		} finally {

		}
	}

	/*
	 * 使用spirepdf的包去读取pdf文件
	 */
	static StringBuffer readSinglePDFBySpirePdf(String fileName) throws Exception {
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		// 读取PDF试卷上的单词
		if (file.isFile()) {
			PdfDocument document = new PdfDocument();
			document.loadFromFile(fileName);
			int pageNum = document.getPages().getCount();
			PdfPageBase page;
			if (pageNum > 0) {
				for (int i = 0; i < pageNum; i++) {
					page = document.getPages().get(i);
					String pdfStr = page.extractText(true);
					sb.append(pdfStr + "\n");
				}
			}
			document.clone();
		}
		return sb;
	}

	/*
	 * 读取出单个pdf的文本信息,使用的PDFBox.jar
	 */
	static StringBuffer readSinglePDFByPDFBox(String fileName) throws IOException {
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		// 读取PDF试卷上的单词
		if (file.isFile()) {
			PDDocument document = PDDocument.load(file);
			int pageNum = document.getNumberOfPages();
			PDFTextStripper tStripper = new PDFTextStripper();

			if (pageNum > 0) {
				for (int i = 1; i <= pageNum; i++) {
					tStripper.setStartPage(i);
					tStripper.setEndPage(i);
					String pdfStr = tStripper.getText(document);
					sb.append(pdfStr + "\n");
				}
			}
		}
		return sb;
	}

	static void init() {
		allWordList = new ArrayList<>();
		allPaperWordList = new ArrayList<String>();
		map = new HashMap<>();
	}

	/*
	 * 读取出所有的单词存放在List<String>中
	 */
	static void readAllWord() {
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
