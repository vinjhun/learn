package test_node;

import java.util.ArrayList;
import java.util.List;

public class test051018 {

	public static void main(String[] args) {
		int test = 654;
		testSolution(test);
	}
	
	public static void testSolution(int num)
	{
		
		List<Integer> binaryNumList = new ArrayList<Integer>();
		Integer tempNumber = num;
		int j = 0;
		
		while(tempNumber > 0)
		{
			binaryNumList.add((int)tempNumber%2);
			tempNumber = (int)Math.floor(tempNumber/2);
			
			j++;
		}
		
		for(Integer i : binaryNumList)
		{
			System.out.print(i);
		}
		
		System.out.println("");
		System.out.println("======");
		System.out.println("Binary Length : " + j);
		
		System.out.println("======");
		
 		for (int p = 1; p < 1 + j ; ++p) 
	    {
	        boolean ok = true;
	        for (int i = 0; i < j - p; ++i) 
	        {
	            if (binaryNumList.get(i) != binaryNumList.get(i+p)) 
	            {
	                ok = false;
	                break;
	            }
	        }

	        if (ok && p <= j/2) 	/// <--changes at here for P <= Q(total)/2 why i so stupid
	        {
	            System.out.println("Period : "  +p);
	        }
	    }
 		
 		System.out.println("No Period");
	}
}
