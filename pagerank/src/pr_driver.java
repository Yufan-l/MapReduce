import org.apache.hadoop.fs.*;
//import java.io.*;
//import java.util.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.conf.Configured;


public class pr_driver extends Configured implements Tool{

	public static void main(String[] args)throws Exception {
		   int res = 0;
	        for (int i = 0; i <25; i++) {
	            System.out.println("Round" + i);
	            res = ToolRunner.run(new Configuration(), new pr_driver(),
	                    args);
	        }
	        System.exit(res);
	}
	public int run(String[] arg0) throws Exception {
		
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		FileSystem fs = FileSystem.get(conf);
		job.setJarByClass(pr_driver.class);

		job.setMapperClass(pr_mapper.class);
		//	job.setCombinerClass(pr_reducer.class);
		job.setReducerClass(pr_reducer.class);
		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(ObjectWritable.class);
	//	job.setInputFormatClass(KeyValueTextInputFormat.class);

		

		

		// TODO: specify input and output DIRECTORIES (not files)
				FileInputFormat.addInputPath(job, new Path("input"));
				
				fs.delete(new Path("output"), true);
			
				
				FileOutputFormat.setOutputPath(job, new Path("output"));

		
		int exitCode = job.waitForCompletion(true) ? 0 : 1;
				
		if( exitCode == 0) {			
			 fs.delete(new Path("input"), true);
	    fs.rename(new Path("output"), new Path("input"));
		}
		
		return exitCode;
	}

}
