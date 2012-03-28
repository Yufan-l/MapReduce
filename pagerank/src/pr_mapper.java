import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class pr_mapper extends Mapper<LongWritable, Text, Text, ObjectWritable> {
	
	protected void map(
    		LongWritable key,
            Text value,
            org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, Text, ObjectWritable>.Context context)
            throws java.io.IOException, InterruptedException {
    
        
        Rank record = new Rank(value.toString());
        
        for (String u : record.getNode()) {
         
            //double newRank =  record.getRank(); new ObjectWritable(
            double newRank = record.getNode().length == 0 ? 0 : record
                    .getRank()/ (record.getNode().length);
            
            context.write(new Text(u), new ObjectWritable(new DoubleWritable(
                    newRank)));
        }


        context.write(new Text(record.getFirst()), new ObjectWritable(record.getNode()));
    };
}