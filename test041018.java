package test_node;

import java.util.List;

public class test041018 {
	
	public static void main(String[] args) {
		String case1 = "  - 189 909 201    19 01";
		String case2 = "981 -111--892-111   19  01";
		String case3 = "---2278191--2718 222-111 91";
		String case4 = "90291 ----28222-1111 2910 -222";
		
		testSolution(case3);
	}
	
	public static void testSolution(String str)
	{
		String test = str.replaceAll("\\-|\\s", "");
		String[] splitStr = test.split("");
		StringBuilder stringBuilder = new StringBuilder();
		
		System.out.println(test);
		
		for(int i = 1 ; i <= splitStr.length ; i++)
		{
			if(splitStr.length%3 == 0)
			{
				//normal condition : just append for every 3rd 
				if((i-1)%3 == 0 && (i-1) != 0)
				{
					stringBuilder.append("-").append(splitStr[(i-1)]);
				}else {
					stringBuilder.append(splitStr[(i-1)]);
				}
			} else {
				//special condition as having remainder
				if((i-1)%3 == 0 && (i-1) != 0 )
				{
					stringBuilder.append("-").append(splitStr[(i-1)]);
				}else {
					
					if((splitStr.length - (i-1)) == 2)
					{
						stringBuilder.append("-").append(splitStr[(i-1)]);
					} else {
						stringBuilder.append(splitStr[(i-1)]);
					}
				}
			}
		}
		
		System.out.print(stringBuilder.toString());
	}
	
}
