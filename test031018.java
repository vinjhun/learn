package test_node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class test031018 {
	
	public static void main(String[] args) {
		
		int[] case1 = {2,3,1,4,5}; //3   --> 2,3,1,4,5
		int[] case2 = {4,2,1,3,5}; //3   --> 2,4,1,3,5
		int[] case3 = {4,5,1,3,2}; //2
		int[] case4 = {2,3,1,5,4}; //2
		int[] case5 = {2,3,4,5,1}; //1

		List<Integer> case6List = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		Collections.shuffle(case6List); //shuffling
		
		int[] case6 = case6List.stream().mapToInt(i->i).toArray();	//unboxing imba
		
		for(int i : case6)
		{
			System.out.print(i + ", ");
		}
		
		testSolution1(case6);
	}
	
	private static void testSolution1(int[] arr)
	{
		int[] newArr = new int[arr.length];
		int turnedCount = 0;
		int isTurnedCount = 0;
		int minValue = (isTurnedCount + 1) ;
		
		for(int i = 0; i < arr.length ; i++)
		{
			//put one into the newArr
			int currentVal = arr[i];
			
			if(currentVal <= arr.length)
			{
				newArr[(currentVal - 1)] = 1;
				
				if(currentVal == minValue)
				{
					turnedCount++;
					
					//extra condition to check when 1st is turned, the next sequential value will turn or not
					int j = isTurnedCount;
					while(j < newArr.length)
					{
						if(newArr[j] == 1)
						{
							isTurnedCount++;
						}else {
							break;		//as long as meet 0 in the way, break from the loop
						}
						
						j++;
					}
				}
				
				if(isTurnedCount > 0)
				{
					minValue = (isTurnedCount + 1) ;
				}
			}
		}
		
		System.out.println("Turned Count : " + turnedCount);
	}

}
