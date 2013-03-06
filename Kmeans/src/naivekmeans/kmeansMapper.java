package naivekmeans;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class kmeansMapper extends Mapper<LongWritable, Text, Text, Text> {

protected void map(
     LongWritable key,
            Text value,
            org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, Text>.Context context)
            throws java.io.IOException, InterruptedException {
    
        
        String content[]=value.toString().split("\t");
        if(content.length<=1)
        	return;
        
        double closest=10000.0;
        String closerCenter=" ";
        for(int i=1;i<content.length;i++)
        {
        	double distance = Distance.rs(content[0], content[i]);
        	if(distance<closest)
        	{
        		closest=distance;
        		closerCenter=content[i];
        	}
        	
        }
        
        
  
        


        context.write(new Text(closerCenter), new Text(content[0]));
    };
}