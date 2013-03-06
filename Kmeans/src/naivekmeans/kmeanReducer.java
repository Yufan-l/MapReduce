package naivekmeans;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.util.*;

public class kmeanReducer extends Reducer<Text,Text,Text,Text>{
	//Iterable< new Rank(rank,nodeList)
	protected void reduce(Text key, Iterable<Text> val, org.apache.hadoop.mapreduce.Reducer<Text, Text, Text, Text>.Context context) throws java.io.IOException, InterruptedException {
	int k=0;
	ArrayList<Double> sum=new ArrayList<Double>();
	for(Text ss: val)
	{
		
		String content[]=ss.toString().split(",");
		if(k==0)
		{
			for(int i=0;i<content.length;i++)
				sum.add(Double.parseDouble(content[i]));
		}
		else
		{
			for(int i=0;i<content.length;i++)
				sum.set(i, sum.get(i)+Double.parseDouble(content[i]));
		}
		k++;
	}
	String centriod="";
	
	for(int j=0;j<sum.size();j++)
	{
		centriod+=Double.toString(sum.get(j)/k)+",";
	}
	
	
	for(Text ss: val)
	{
		context.write(ss, new Text(centriod));
	}
	
	

	

};

}