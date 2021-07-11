package com.mine.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

/*
 * 用于进行文本统计的工具类
 * 思路:
 * 	使用两个数据结构存放，一个ArrayList<String>用来存放5500个单词,
 * 	还有一个HashMap<String,Integer>中的key上对应着单词，其中的value则对应出现的次数
 * 	从真题文档中读取出所有的信息，然后进行拆分;
 * 	进行匹配，匹配时需要处理好几个问题，因为从文档上读取出来的单词，可能会加上各种后缀,特殊的还会省略一些东西
 * 		如on,set在各种单词里都会存在
 * 	最后进行打印，看看5500个单词有多少在真题中出现过，同时打印出每个单词出现的频率
 */
public class TextStatUtils {

	static ArrayList<String> allWordLst;
	static HashMap<String, Integer> map;

	public static void main(String[] args) {

//		init();
//		readAllWord();// 读取出5500个单词
		readWordByPDF();
	}

	/*
	 * 读取出所有pdf上的信息
	 */
	static void readWordByPDF() {
		StringBuffer sb;
		try {
			for (int i = 2018; i <= 2018; i++) {
//				String fileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/2018英语一真题.pdf";
				String fileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2019年考研英语一真题.pdf";
//				sb = readSinglePDFByPDFBox(fileName);
				sb = readSinglePDFBySpirePdf(fileName);
//				System.out.println(i+"年的真题 : "+sb.toString());
				System.out.println("\n\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				for (int i = 3; i < pageNum; i++) {
					page = document.getPages().get(i);
					String pdfStr = page.extractText(true);
					System.out.println("读取出来的内容:"+ pdfStr);
					sb.append(pdfStr + "\n");
				}
			}
			document.close();
		}
		return sb;
	}

	/*
	 * 读取出单个pdf的文本信息,使用的PDFBox.jar
	 */
//	static StringBuffer readSinglePDFByPDFBox(String fileName) throws IOException {
//		StringBuffer sb = new StringBuffer();
//		File file = new File(fileName);
//		// 读取PDF试卷上的单词
//		if (file.isFile()) {
//			PDDocument document = PDDocument.load(file);
//			int pageNum = document.getNumberOfPages();
//			PDFTextStripper tStripper = new PDFTextStripper();
//
//			if (pageNum > 0) {
//				for (int i = 1; i <= pageNum; i++) {
//					tStripper.setStartPage(i);
//					tStripper.setEndPage(i);
//					String pdfStr = tStripper.getText(document);
//					sb.append(pdfStr + "\n");
//				}
//			}
//		}
//		return sb;
//	}

	static void init() {
		allWordLst = new ArrayList<>();
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
						allWordLst.add(strArray[1]);
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
