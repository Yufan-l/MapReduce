import org.apache.hadoop.io.*;
//import org.apache.hadoop.mapreduce.*;
import java.io.*;

public class Rank implements Writable{
	 private double rank;
	 private String[] nodeList=new String[0];
	 private String first;
	 public Rank()
	 {	 
	 }
	 
	 public double getRank()
	 {
		 return rank;
	 }
	 
	 public String[] getNode()
	 {
		 return nodeList;
	 }
	 
	 public String getFirst()
	 {
		 return first;
	 }
	 
	 public Rank(String str)
	 {
		 super();
		 String[]contents=str.split("\t");
		 this.first=contents[0];
		 String[]val=contents[1].split(" ");
		 this.rank=Double.parseDouble(val[val.length-1]); 
		 if(val.length==2)
		 {
			 this.nodeList=val[0].split(",");
		 }
	 }
	 
	 public Rank(double rank, String[] nodeList)
	 {
		 super();
		 this.rank=rank;
		 this.nodeList=nodeList;
	 }
	 
	 public String toString()
	 {
		 String contents="";
		 for(String s : nodeList)
		 {
			 contents+=s+",";
		 }
		 return contents+" "+rank;
	 }
	 
	 public void readFields(DataInput in) throws IOException {
		 				ArrayWritable aw = new ArrayWritable(Text.class);
	        DoubleWritable dw = new DoubleWritable();
	       
	        aw.readFields(in);
	        dw.readFields(in);
	        
	        rank = dw.get();
	        nodeList = aw.toStrings();
	    }
	 
	 
	 public void write(DataOutput out) throws IOException {
		 				ArrayWritable aw = new ArrayWritable(nodeList);
	        DoubleWritable dw = new DoubleWritable(rank);
	        
	        aw.write(out);
	        dw.write(out);
	        
	    }
 

	 


}