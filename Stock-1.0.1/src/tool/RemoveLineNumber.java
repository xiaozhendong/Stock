package tool;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

  
public class RemoveLineNumber  
{  
    public static void main(String[] args) throws IOException 
    
    {  
    	 FileReader read = new  FileReader("D:\\test\\test.txt");
    	  
		BufferedReader bufferedInputStream=new BufferedReader(read);
    	  String lineTxt;
    	  String finaltestString;
    	  BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream("D:\\xzd2.txt"));
    	  while((lineTxt =bufferedInputStream.readLine()) != null){
    		  byte[] bs=lineTxt.getBytes();
    		  byte[] newbs=new byte[bs.length];
    		  int y=0;
    		  for (int i = 4; i < bs.length; i++) {
			   if (i<4) {
				continue;
			}
			   newbs[y]=bs[i];
			   ++y;
			}
    		  
    		  finaltestString=new String(newbs);
               outputStream.write(newbs);
               outputStream.write('\n');
              
    		 // System.out.println(finaltestString);
    	  }
    	  outputStream.close();
    	  bufferedInputStream.close();
    	  read.close();
       
    }  
}

