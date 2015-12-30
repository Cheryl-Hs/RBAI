

public class strToNumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String Number_Before = "12000300200";
		String Number_After = "";
		// TODO Auto-generated method stub
		for (int j =0;j<Number_Before.length();j++) {
			if ( ! Number_Before.substring(j, j+1).equals("0")) {
				Number_After+=Number_Before.substring(j, j+1);
				//System.out.print(Number_After+'\n');
			}
			else if (Number_Before.substring(j, j+1).equals("0")) {
				if (j<Number_Before.length()-1) {
					Number_After+=Number_Before.substring(j, j+1);
					//System.out.print("ok"+'\n');
					if (Number_Before.substring(j+1, j+2).equals("0")) {
						int m = 0;
						for (m=j;m<Number_Before.length();m++) {
							if (Number_Before.substring(m, m+1).equals("0")){
								continue;
							}
							break;
						}
						j = m-1;
					}
				}
				else Number_After+=Number_Before.substring(j, j+1);
			}
			
		}
		System.out.print(Number_After);
		

	}

}
