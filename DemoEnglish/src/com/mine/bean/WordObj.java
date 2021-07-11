package com.mine.bean;

import java.util.List;

/*
 * 定义一个类，表示一个单词对象
 */
public class WordObj{
	private String word;
	private List<Integer> list;//该数据用来存放当前与之能匹配上的单词的索引
	private List<String> tempStr;//与list相对应,用来存放匹配的部分
	
	public List<String> getTempStr() {
		return tempStr;
	}
	public void setTempStr(List<String> tempStr) {
		this.tempStr = tempStr;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}
}