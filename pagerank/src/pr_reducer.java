import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
//import java.util.Iterator;

public class pr_reducer extends Reducer<Text, ObjectWritable,Text,Rank>{
	//Iterable<  new Rank(rank,nodeList)
	protected void reduce(Text key, Iterable<ObjectWritable> val,  org.apache.hadoop.mapreduce.Reducer<Text, ObjectWritable, Text, Rank>.Context context) throws java.io.IOException, InterruptedException {
		double rank=0.0;
		double d=0.75;
		String nodeList[]=new String[0];
		/*while(val.hasNext())
		{
		rank +=  val.next().get();
		}*/
		for(ObjectWritable ss: val)
		{
			 if (ss.getDeclaredClass().toString().equals(
	                    DoubleWritable.class.toString())) {
	                rank += ((DoubleWritable) ss.get()).get();
	            }
	            if (ss.getDeclaredClass().toString().equals(
	                    String[].class.toString())) {
	                nodeList = (String[])ss.get();
	            }

			
		}
		rank = (1 - d) + rank;

		context.write(key, new Rank(rank,nodeList) );
		
	};

}