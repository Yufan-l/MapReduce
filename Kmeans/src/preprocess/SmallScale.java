package preprocess;
import java.util.*;
import java.io.*;


public class SmallScale {
	
	public static void addcenter(int k, int range,String loc)throws IOException
	{
		ArrayList <Integer>centerIndex=new ArrayList<Integer>();
		ArrayList <String>centers=new ArrayList<String>();
		HashSet<Integer> Unique=new HashSet<Integer>();
		
		for(int i=0;i<k;i++)
		{
			Random rd=new Random();
			int cand=rd.nextInt(range);
			while(!Unique.contains(cand))
			{
				cand=rd.nextInt(range);
			}
			centerIndex.add(cand);
			Unique.add(cand);
		
		}
		Collections.sort(centerIndex);
		Scanner SC=new Scanner(new FileReader(loc));
		int index=0;
		int index1=0;
		while(SC.hasNext())
		{
			String tmp=SC.nextLine();
			if (index==centerIndex.get(index1))
			{
				centers.add(tmp);
				index1++;
			}
			index++;
		}
		
		
		
		Scanner SC1=new Scanner(new FileReader(loc));
		File F1=new File(loc+"_processed");
		if(!F1.exists())
			F1.createNewFile();
		FileWriter fw=new FileWriter(F1.getAbsoluteFile());
		final BufferedWriter bw=new BufferedWriter(fw);
		
		
		while(SC.hasNext())
		{
			String tmp=SC.nextLine();
			for(int i=0;i<centers.size();i++)
			{
				tmp+="\t"+centers.get(i);
			}
			bw.write(tmp);
			bw.newLine();
			
		}
		
		
		bw.close();
		SC1.close();
		SC.close();
		
	}
	
	
	public static void main(String args[])throws IOException
	{
		int k=Integer.parseInt(args[0]);
		int range=Integer.parseInt(args[1]);	
		String loc=args[2];
		addcenter(k,range,loc);
		
	}
	
	
	

}
