package com.xulp.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

/**
 * @author xulp
 *
 */
public class Test {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		testFileReaderAndWrite();

	}

	/*
	 * 用来测试对文件进行读取和写入的功能
	 */
	static void testFileReaderAndWrite() {

		ArrayList<String> listWord = new ArrayList<String>();// 存储5500个单词
		ArrayList<String> listTxt = new ArrayList<String>();// 存储试卷上出现的单词，统计其频率
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedReader br = null;// 用于读取词汇的输入流
//		BufferedReader brTxt = null;// 用于读取试卷的输入流
		BufferedWriter bw = null;
		BufferedWriter bwTxt = null;
		String info = "";
		byte[] buf = new byte[1024];
		int len = 0;
		String[] ss;
		try {
			File fInput = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");
			File fOut = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500的复制版.txt");
			File fInputTxt = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/2018英语一真题.pdf");
			File fOutTxt = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语真题/考研英语真题汇总.txt");

			if (!fOut.exists()) {
				fOut.createNewFile();
			} else {
				fOut.delete();
				fOut.createNewFile();
			}

			if (!fOutTxt.exists()) {
				fOutTxt.createNewFile();
			} else {
				fOutTxt.delete();
				fOutTxt.createNewFile();
			}

//			if (fInput.isFile()) {
//				fis = new FileInputStream(fInput);
//				fos = new FileOutputStream(fOut);
//				while ((len = fis.read(buf, 0, buf.length)) != -1) {
//					fos.write(buf, 0, len);
//				}
//			}

//			String regx = "[A-Za-z]";
//			Pattern pattern = Pattern.compile(regx);
//			for (String s : ss) {
//				Matcher ma = pattern.matcher(s);
//				int group = ma.groupCount();
//			}

			// 读取词汇
			if (fInput.isFile()) {
				br = new BufferedReader(new FileReader(fInput));
				bw = new BufferedWriter(new FileWriter(fOut));
				while ((info = br.readLine()) != null) {

					ss = info.split(" ");
					// 开始操作字符串
					if (ss != null && ss.length > 1) {
						bw.write(ss[1] + "\n");
						listWord.add(ss[1]);
						len++;
					}

				}
			}

			// 读取PDF试卷上的单词
			if (fInputTxt.isFile()) {
				bwTxt = new BufferedWriter(new FileWriter(fOutTxt));

				PDDocument document = PDDocument.load(fInputTxt);
//				brTxt = new BufferedReader(new FileReader(fInputTxt));
				int pageNum = document.getNumberOfPages();
				PDFTextStripper tStripper = new PDFTextStripper();

				if (pageNum > 0) {
					for (int i = 1; i <= pageNum; i++) {
						tStripper.setStartPage(i);
						tStripper.setEndPage(i + 1);
						String pdfFileInText = tStripper.getText(document);
						bwTxt.write(pdfFileInText + "\n");
					}
				}

//				String[] lines = pdfFileInText.split(" ");
//
//				if(lines != null)
//				{
//					for(String s : lines)
//					{
//						bwTxt.write(s + "\n");
//						System.out.println("从pdf文件上读取到的数据 : " + s);
//					}
//				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
				if (br != null)
					br.close();
//				if (brTxt != null)
//					brTxt.close();
				if (bw != null)
					bw.close();
				if (bwTxt != null)
					bwTxt.close();
			} catch (Exception e) {

			}

		}
	}

}
