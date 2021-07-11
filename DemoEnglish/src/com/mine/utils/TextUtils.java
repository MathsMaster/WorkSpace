package com.mine.utils;

import java.util.regex.Pattern;

public class TextUtils {

	public static boolean isEmpty(String info) {
		if (info == null || info.trim().equals(""))
			return true;
		return false;
	}

	public static boolean isContainEnChar(String info) {
		if (isEmpty(info))
			return false;
		Pattern p = Pattern.compile("[a-zA-Z]");
		if (p.matcher(info).find()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 对传进来的单词进行分割 isHead 表示是从头还是从尾巴开始拆分 stepNum 表示拆分时的步数,如 step从头切割的步数为1，则切割后是 tep
	 * 如果切割的步数大于其单词长度的话，就直接返回
	 */
	public static String subString(boolean isHead, int stepNum, String word) {

		if (isEmpty(word) || word.length() < stepNum)
			return word;
		int beginIndex = 0;
		int endIndex = word.length();
		if (isHead) {
			beginIndex += stepNum;
			word = word.substring(beginIndex);
		} else {
			endIndex -= stepNum;
			word = word.substring(beginIndex, endIndex);
		}
		return word;
	}

	public static String filterString(String str) {
		// 在这里完成过滤操作(20 points) SHEET. comments. 3)​give briefly, “Whether companies”
		// text? 40. ​[D] (OECD) tax),
		if (isEmpty(str))
			return "";

		int index = -1;
		if (str.contains("(") || str.contains("[") || str.contains("“")) {
			int index_1 = str.indexOf("(");
			int index_2 = str.indexOf("[");
			int index_3 = str.indexOf("“");
			index = index > index_1 ? index : index_1;
			index = index > index_2 ? index : index_2;
			index = index > index_3 ? index : index_3;
			str = str.substring(index + 1);
		} else if (str.contains(")") || str.contains(".") || str.contains(",") || str.contains("”") || str.contains("?")
				|| str.contains("]")) {
			int index_1 = str.lastIndexOf(")");
			int index_2 = str.lastIndexOf(".");
			int index_3 = str.lastIndexOf(",");
			int index_4 = str.lastIndexOf("”");
			int index_5 = str.lastIndexOf("?");
			int index_6 = str.lastIndexOf("]");
			index = index > index_1 ? index : index_1;
			index = index > index_2 ? index : index_2;
			index = index > index_3 ? index : index_3;
			index = index > index_4 ? index : index_4;
			index = index > index_5 ? index : index_5;
			index = index > index_6 ? index : index_6;
			str = str.substring(0, index);
		}

		if (str.contains("]")) {
			index = -1;
			int index_2 = str.lastIndexOf("]");

			index = index > index_2 ? index : index_2;
			str = str.substring(0, index);
		}

		return str;
	}

}
