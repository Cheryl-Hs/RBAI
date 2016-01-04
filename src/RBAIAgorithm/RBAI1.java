/**
 * 论文：基于规则的中文地址要素解析方法
 * 作者：Cheryl
 * 时间：2015.12.23
 */
package RBAIAgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class RBAI1 {
	
	Hashtable <String, String> mainDic_datatable = new Hashtable<String, String>();
	Hashtable <String, String> additionalDic_datatable = new Hashtable<String, String>();
	
	public RBAI1() {
		Init();
	}
	
	public List<String> readOrg() {
		List lst = new ArrayList<String>();
		
		try {
			//读取存放地址数据的文件
			File f = new File("Data/addressData.txt");
			if (f.isFile() && f.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String linStr = br.readLine();
				while (linStr != null) {
					lst.add(linStr);
					linStr = br.readLine();
				}
				br.close();
				isr.close();
	
			} 
		} catch (Exception e) {
				e.printStackTrace();
	    }
		return lst;
		
		
	}
	
	public void mainAlgorithm(List<String> lst) {
		
		String Address_Before="";
		String Address_After="";
		
		rulesMatch match = new rulesMatch();
		strToNum Numbers = new strToNum();
		
		try {
			File f =new File("Data/RBAI_result.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			BufferedWriter write = new BufferedWriter(result);
			
			for (int i=0;i<lst.size();i++) {
				Address_Before = lst.get(i);
				int len = Address_Before.length();
				String Numbers_Before = "";
				String Numbers_temp = "";
				String Numbers_After = "";
				//输出原地址
				write.write(Address_Before + "::");
				//将每个字符对应到数字
				for (int k=0;k<len;k++) {       //遍历每个地址中的每一个字符
					if (isExistM(Address_Before.substring(k, k+1))) {   //判断字符是否与主特征辞典匹配
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
					else if (isExistA(Address_Before.substring(k, k+1))) {   //字符是附属特征字,"2"
						Numbers_Before += "2";
						//System.out.print(2);
					}
					else {
						Numbers_Before += "0";//System.out.print(0);  //普通字符用“0”
					}
					
				}
				write.write(Numbers_Before + "::");
				//System.out.print("\n");
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
				write.write(Numbers_temp + "::");
				Numbers_After = match.rulesMatch(Numbers_temp);//调用rulesMatch对Numbers_Before进行规则匹配
				//System.out.print(Numbers_Before + "\n");
				write.write(Numbers_After);
				write.write("\n");

			}
			write.close();

		} catch (Exception e) {
			e.printStackTrace();
		}	 
	}
	
	public boolean isExistM(String value) {
		if (mainDic_datatable.containsKey(value))
			return true;
		else
			return false;	
	}
	
	public boolean isExistA(String value) {
		if (additionalDic_datatable.containsKey(value))
			return true;
		else
			return false;	
	}
	
 	private void Init() {
		try {
			File mainDic = new File("Data/mainDic.txt");
			File additionalDic = new File("Data/additionalDic.txt");
			if (mainDic.isFile() && mainDic.exists()) {
				//按行读取mainDic中的特征字,存到mainDic_datatable中
				InputStreamReader isr1 = new InputStreamReader(new FileInputStream(mainDic),"UTF-8");
				BufferedReader br1 = new BufferedReader(isr1);
				String str1 = br1.readLine();
				while (str1 != null) {
					if (!mainDic_datatable.containsKey(str1))
						mainDic_datatable.put(str1, str1);
					str1 = br1.readLine();
				}
			}
			
			if (additionalDic.isFile() && additionalDic.exists()) {
				//按行读取additionalDic中的特征字，存到additionalDic_datatable中
				InputStreamReader isr2 = new InputStreamReader(new FileInputStream(additionalDic),"UTF-8");
				BufferedReader br2 = new BufferedReader(isr2);
				String str2 = br2.readLine();
				while (str2 != null) {
					if (!additionalDic_datatable.containsKey(str2))
						additionalDic_datatable.put(str2, str2);
					str2 = br2.readLine();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 


	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		
		RBAI1 seg = new RBAI1();
		List lst = seg.readOrg();
		seg.mainAlgorithm(lst);
		
		long endTime = System.currentTimeMillis();
		System.out.print("分词时间: " + (endTime - startTime) + "ms");

	}

}