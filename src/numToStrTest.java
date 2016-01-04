/**
 * 论文：基于规则的中文地址要素解析方法
 * 功能：将切分好的数字字符串Numbers_After还原成带切分符的地址字符串Address_After,test
 * 作者：Cheryl
 * 时间：2016.1.4
 */
public class numToStrTest {

	public static void main(String[] args) {
		
		//将切分好的数字字符串Numbers_After还原成没有进行合并0处理的切分的字符串Address_temp
		String Address_temp = "";
		String Numbers_After = "201|011|01|0|9";
		String Numbers_Before = "20100110000010000";
		int j = 0;
		
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
		System.out.print(Address_temp + "\n");
		
		//将切分好的数字字符串Address_temp还原成带切分符的地址字符串Address_After
		String Address_After = "";
		//String Address_temp = "201|0011|000001|0000";
		String Address_Before = "南翔镇古猗园路158弄5号401室";
		int k = 0;
		
		for (int i = 0;i<Address_temp.length();i++) {
			if (k<Address_Before.length()){
				if (Address_temp.charAt(i) == '|') {
					Address_After += "|";
				} else {
					Address_After += Address_Before.charAt(k);
					k++;
				}
			}
		}
		
		System.out.print(Address_After);

	}

}
