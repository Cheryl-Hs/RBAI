/**
 * 论文：基于规则的中文地址要素解析方法
 * 功能：将地址字符串匹配主特征字、附属特征字字典，变为数字字符串
 * 作者：Cheryl
 * 时间：2016.1.2
 */
package RBAIAgorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class strToNum {
	
	//将地址字符串Address_Before匹配主特征字、附属特征字字典，变为数字字符串Numbers_Before
	public String strToNum1(String Address_Before) {
		String Numbers_Before = "";
		int len = Address_Before.length();
		RBAI isExist = new RBAI();
		
//		try {
//			File f =new File("Data/strToNumResult1_Numbers_Brfore.txt");
//			if (!f.exists()) {
//				f.createNewFile();
//			}
//			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f,true),"UTF-8");
//			BufferedWriter write = new BufferedWriter(result);
			
			//将每个字符对应到数字
			for (int k=0;k<len;k++) {       //遍历每个地址中的每一个字符
				if (isExist.isExistM(Address_Before.substring(k, k+1))) {   //判断字符是否与主特征辞典匹配
					Numbers_Before += "1";
					//System.out.print(1);
					if ((k+1)<len && 
							Address_Before.substring(k, k+1).equals(Address_Before.substring(k+1, k+2))) { 
						//连续的两个字符相同且都是主特征辞则在11后面加字符“3”
						Numbers_Before += "1";
						//System.out.print(1);
						Numbers_Before += "3";
						//System.out.print(3);
						k=k+1;
					}
				}
				else if (isExist.isExistA(Address_Before.substring(k, k+1))) {   //字符是附属特征字,"2"
					Numbers_Before += "2";
					//System.out.print(2);
				}
				else {
					Numbers_Before += "0";//System.out.print(0);  //普通字符用“0”
				}
				
			}

//			write.write(Numbers_Before + "\n");
//			write.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return Numbers_Before;
	}
	
	//将数字字符串Numbers_Before中的连续0变成一个0，且在字符串末尾加结束字符“9”，变成Numbers_temp;
	public String strToNum2(String Numbers_Before) {
		String Numbers_temp = "";
		
//		try {
//			File f =new File("Data/strToNumResult2_Numbers_temp.txt");
//			if (!f.exists()) {
//				f.createNewFile();
//			}
//			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f,true),"UTF-8");
//			BufferedWriter write = new BufferedWriter(result);
		
			//将数字串里面的连续0合并成一个0
			for (int j =0;j<Numbers_Before.length();j++) {
				if ( ! Numbers_Before.substring(j, j+1).equals("0")) {
					Numbers_temp += Numbers_Before.substring(j, j+1);
				}
				else if (Numbers_Before.substring(j, j+1).equals("0")) {
					if (j<Numbers_Before.length()-1) {
						Numbers_temp += Numbers_Before.substring(j, j+1);
						if (Numbers_Before.substring(j+1, j+2).equals("0")) {
							int m = 0;
							for (m=j;m<Numbers_Before.length();m++) {
								if (Numbers_Before.substring(m, m+1).equals("0")){
									continue;
								}
								break;
							}
							j = m-1;
						}
					}
					else {
						Numbers_temp += Numbers_Before.substring(j, j+1);
					}
				}
				
			}
			//在数字串末尾加一个结束字符9
			Numbers_temp += "9";
//			write.write(Numbers_temp + "\n");
//			write.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
			
		return Numbers_temp;
	}
}
