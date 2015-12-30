import java.util.ArrayList;
import java.util.List;


public class rulesMatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String str = "01010110212019";
		//System.out.print(str);
		//String[] strs = str.split("f");
 		//System.out.print(str.substring(0,str.indexOf("f"))+'\n');
 		//System.out.print(str.substring(str.indexOf("f")+1, str.length()));
		//System.out.print(strs[0]);

		
//规则匹配方法一：
		
		//String Numbers_Before = "01010110212019";
		//String Numbers_Before = "01011023456789";
		String Numbers_Before = "021012012010209";
		String Numbers_After = "";
		String Numbers_Left = "";
		String Numbers_right = "";
		int k = 0;
		int m = 0;
		//int n = 0;
		
		List<String> rules = new ArrayList();
		rules.add("010f2");
		rules.add("0110f3");
		rules.add("0113f2");
		rules.add("0120f2");
		rules.add("0122f2");
		rules.add("021f3");
		rules.add("09f1");
		rules.add("1309f3");
		rules.add("19f1");
		rules.add("2019f3");
		rules.add("320101f4");
		rules.add("32012f4");
		rules.add("329f2");
		
		
        for (int i = 0;i<Numbers_Before.length();i++) {
        	for (int n = i+1;n<=Numbers_Before.length();n++) {
	        	for (int j = 0;j<rules.size();j++) { //遍历规则，进行匹配
	        		k = rules.get(j).indexOf("f");
	        		//System.out.print(k+" "+Numbers_Before.substring(i,n)+" " +rules.get(j).substring(0,k) +"\n");
	        		//判断字符串是否匹配上规则，匹配则切分
	        		if (Numbers_Before.substring(i,n).equals(rules.get(j).substring(0,k))) {
	        			m = Integer.parseInt(rules.get(j).substring(k+1, rules.get(j).length()));
	        			Numbers_Left += Numbers_Before.substring(i, i+m);
	        			//System.out.print(Numbers_Left);
	        			Numbers_Left +="|";
	        			//System.out.print(Numbers_Left+"\n");
	        			i = i+m;
	        			break;
	        		}
	        	}
	        	//没有匹配上的字符串不处理，直接输出
	        	if (n == Numbers_Before.length()) {
	        		//System.out.print(Numbers_Before.substring(i, i+1));
	        		Numbers_Left += Numbers_Before.substring(i, i+1);
	        	}
        	}
        }
        System.out.print(Numbers_Left);
		
//规则匹配方法二：	
		
//		List<String> rules = new ArrayList();
//		rules.add("010f2");
//		rules.add("01102f3");
//		rules.add("0113f2");
//		rules.add("0120f2");
//		rules.add("0122f2");
//		rules.add("021f3");
//		rules.add("09f1");
//		rules.add("1309f3");
//		rules.add("19f1");
//		rules.add("2019f3");
//		rules.add("320101f4");
//		rules.add("32012f4");
//		rules.add("329f2");
//		
//		String Numbers_After = "";
//		String Numbers_Left = "";
//		String Numbers_Right = "";
//		String Numbers_temp = "";
//		//String Numbers_Before = "01010110212019";
//		//String Numbers_Before_temp = "01010110212019";
//		String Numbers_Before = "2021010101109";
//		String Numbers_Before_temp = "2021010101109";
//		int k = Numbers_Before.length();
//		int m = 0;
//		int n = 0;
//		int p = 0;
//		
//		for (int i = 1;i<=k;i++) {
//			Numbers_Right = Numbers_Before.substring(i, k);
//			//System.out.print(Numbers_Left+'\n');
//			for (int j = 0;j<rules.size();j++) {
//				n = rules.get(j).indexOf("f");
//        		//判断字符串是否匹配上规则，匹配上则切分
//        		if (Numbers_Before.substring(0, i).equals(rules.get(j).substring(0,n))) {   
//        			m = Integer.parseInt(rules.get(j).substring(n+1, rules.get(j).length()));
//        			Numbers_Left += Numbers_Before.substring(0, m);
//        			Numbers_Right = Numbers_Before.substring(m, k);
//        			Numbers_Left += "|";
//        			p += m;
//        			//System.out.print(Numbers_Left+'\n');
//        			Numbers_Before = Numbers_Right;
//        			k = Numbers_Right.length();
//        			i = 1;
//        			break;
//        		}
//			}
//
//		}
//		Numbers_After += Numbers_Left;
//		System.out.print(Numbers_After );
//      //如果有字符串没有切分出来则输出
//		if (Numbers_Before.substring(0,k).equals(Numbers_Before_temp.substring(p, Numbers_Before_temp.length()))) {
//			//Numbers_After += Numbers_Before.substring(0,k);
//			System.out.print(Numbers_Before.substring(0,k));
//					
//		}

		
	}

}

