package com.example.quickindexbar;

public class Frined implements Comparable<Frined>{

	private String name;
	private String pinyin;
	
	public Frined(String name) {
		super();
		this.name = name;
		this.pinyin = pingYinUtil.getPingYin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Frined arg0) {
		// TODO Auto-generated method stub
		return this.pinyin.compareTo(arg0.pinyin);
	}
}
