package naivekmeans;

import java.util.*;

public class Distance {
	
	public static double rs(String a,String b)
	{
		String contentA[]=a.split(",");
		String contentB[]=b.split(",");
		double distance=0.0;
		for(int i=0;i<contentA.length;i++)
			distance+=(Double.parseDouble(contentA[i])-Double.parseDouble(contentB[i]))*(Double.parseDouble(contentA[i])-Double.parseDouble(contentB[i]));
		
		return Math.sqrt(distance);
		
	
		
	}
	
	

}
