/**
 * 论文：基于规则的中文地址要素解析方法
 * 功能：将切分好的数字字符串Numbers_After还原成带切分符的地址字符串Address_After
 * 作者：Cheryl
 * 时间：2016.1.2
 */
package RBAIAgorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class numToStr {
	
	//将带分隔符的数字字符串Numbers_After与Numbers_Before对比，还原成带分隔符的没有压缩"0"的数字字符串Address_temp
	public String numToStr1(String Numbers_After , String Numbers_Before) {
		String Address_temp = "";
		int j = 0;
		
//		try{
//			File f = new File("Data/numToStrResult1_Address_temp.txt");
//			if (!f.exists()) {
//				f.createNewFile();
//			}
//			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f,true),"UTF-8");
//			BufferedWriter write = new BufferedWriter(result);
			
			for (int i = 0;i<Numbers_After.length();i++) {
				for (;j<Numbers_Before.length();j++) {
					//如果数字不为0，则输出不为0的数字到Address_temp中，并写出与Numbers_After对应数字后面的分隔符“|”
					if (Numbers_After.charAt(i) != '0' &&
							Numbers_After.charAt(i) == Numbers_Before.charAt(j)) {
						Address_temp += Numbers_Before.charAt(j);
						if (Numbers_After.charAt(i+1) == '|') {
							Address_temp += "|";
							i++;
							j++;
							break;
						} else {
							j++;
							break;
						}
					} else if (Numbers_After.charAt(i) == '0' && 
							Numbers_After.charAt(i) == Numbers_Before.charAt(j)) { //判断字符是不是0
						if (j == Numbers_Before.length()-1) { //判断是不是Numbers_Before得最后一个0，是则写入Address_temp中
							Address_temp += Numbers_Before.charAt(j);
							Address_temp += "|";
							break;
						} else { 
							if (Numbers_Before.charAt(j+1) == '0' ) { //如果不是最后一个0，则输出Numbers_Before的连续0到Address_temp中
								Address_temp += Numbers_Before.charAt(j);
							} else {
								Address_temp += Numbers_Before.charAt(j);
								j++;
								break;
							}
						}
					} else {
						break;
					}
				}
			}
//			write.write(Address_temp + "\n");
//			write.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return Address_temp;
		
	}
	
	//将带分割符的数字字符串Address_temp与Address_Before对比，还原成带分隔符的地址字符串Address_After
	public String numToStr2(String Address_temp , String Address_Before) {
		String Address_After = "";
		int j = 0;
		
//		try {
//			File f = new File("Data/numToStrResult2_Address_After.txt");
//			if (!f.exists()) {
//					f.createNewFile();
//			}
//			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f,true),"UTF-8");
//			BufferedWriter write = new BufferedWriter(result);
			
			for (int i = 0;i<Address_temp.length();i++) {
				if (j<Address_Before.length()) {	
					if (Address_temp.charAt(i) == '|') {
						Address_After += "|";
					} else {
						Address_After += Address_Before.charAt(j);
						j++;
					}
				}
			}
//			write.write(Address_After + "\n");
//			write.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return Address_After;
		
	}

}
