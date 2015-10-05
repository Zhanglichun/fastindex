package com.example.quickindexbar;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.text.TextUtils;

public class pingYinUtil {

	public static String getPingYin(String hanzi){
		String pinying = "";
		if (TextUtils.isEmpty(hanzi)) return pinying;
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		//只可以对单个汉字进行转换
		char[] arr = hanzi.toCharArray();
		for (char c : arr) {
			if (Character.isWhitespace(c)) continue;
			if (c > 127) {
				
				try {
					//多音字的存在。
					String[] resultStrings = PinyinHelper.toHanyuPinyinStringArray(c, format);
					if (resultStrings == null) {
						pinying += c;
					}else{
						pinying += resultStrings[0];
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					pinying += c;
				}
				
			}else{
				pinying += c;
			}
		}
		return pinying;
	}
}
