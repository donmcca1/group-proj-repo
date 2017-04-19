package com.ericsson.group.utilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ericsson.group.dao.UploadDAO;
import org.apache.commons.lang3.math.NumberUtils;

public class Validation {
	String path;
	//importing files NEED TO CHANGE TO GET THE LOCATION DYNAMICALLY
	String baseDataFile;
	String causeCodeFile;
	String mccMncFile;
	String ueFile;
	String failureClassFile;
	
	//formatting
	String line = "";
	String splitBy = ";";
	
	//for storing foreign keys
    Hashtable causeCodeEventIdTable = new Hashtable();
    Hashtable mccMncTable = new Hashtable();
    Hashtable ueTable = new Hashtable();
    Hashtable failureClassTable = new Hashtable();
	
	public Validation() {
		path = UploadDAO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.substring(0, path.indexOf("classes")) + "upload/";
		
		baseDataFile = path + "/base_data.txt";
		causeCodeFile = path + "/event_cause.txt";
		mccMncFile = path + "/mcc_mnc.txt";
		failureClassFile = path + "/failure_class.txt";
		ueFile = path + "/ue.txt";
		
		//clear the base_data_validated file
		BufferedWriter bwc;
		try {
			bwc = clearWriter();
			bwc.write("");
			bwc.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//make the hash tables for foreign key validation
		makeDoubleHashTable(causeCodeFile,splitBy,0,1,causeCodeEventIdTable);
		makeDoubleHashTable(mccMncFile,splitBy,0,1,mccMncTable);
		makeHashTable(ueFile,splitBy,0,ueTable);
		makeHashTable(failureClassFile,splitBy,0,failureClassTable);
		
		//validate each line
		try {
			BufferedReader br = new BufferedReader(new FileReader(baseDataFile));
		//iteration variable removes the headings from the data file
   		int iteration = 0;
   		
   		while ((line = br.readLine()) != null) {
			if (iteration == 0){
				iteration ++;
					continue;
			}
			
			//valid flag
			Boolean lineValid = true;

			String[] currentLine = line.split(splitBy);
	    	
			//fields to be validated
			
			String date = currentLine[0];
			String eventID = currentLine[1];
			String failureClass = currentLine[2];
			String ueType = currentLine[3];
			String market = currentLine[4];
			String operator = currentLine[5];
			String cellID = currentLine[6];
			String duration = currentLine[7];
			String causeCode = currentLine[8];
			String neVersion = currentLine[9];
			String imsi = currentLine[10];

			//---CHECKING THE FOREIGN KEYS---//
			
			//Cause Code/Event ID
			if(validateDoubleHashTable(currentLine,8,1,causeCodeEventIdTable)==false){
				lineValid = false;
			}	
			
			//Mcc/MNC
			if(validateDoubleHashTable(currentLine,4,5,mccMncTable)==false){
				lineValid = false;
			}	
			
			//UE type
			if(validateHashTable(currentLine,3,ueTable)==false){
				lineValid = false;
			}	
			
			//Failure class
			if(validateHashTable(currentLine,2,failureClassTable)==false){
				lineValid = false;
			}	
			
			//---CHECKING THE INTS---//
			//CellID
			//checks it's a number
	       
			if(NumberUtils.isDigits(cellID)){

			} else {
				lineValid = false;
			};
			
	       //Duration
	       //checks it's a number
	   
	       if(NumberUtils.isDigits(duration)){

	       } else {
	    	   lineValid = false;
	       };
	       
	       //IMSI
	       //checks it's a 15-digit number
	       
	       if(NumberUtils.isDigits(imsi) && imsi.length()==15){

	       } else {
	    	   lineValid = false;
	       };
	       
	       //---CHECKING THE IMSI MATCHES THE OPERATOR AND MARKET---//
	       //add a zero to the end of the operator value where needed
/*           if (operator.length()==1){
           	operator=operator+"00";
           }else if (operator.length()==2){
           	operator=operator+"0";
           }

           //saving market and operator 
           String marketOperator = new String(market+operator);
           
           //saving imsi substring
           String subImsi = new String(imsi.substring(0,6));

           if(marketOperator.equals(subImsi)){

           }else{
        	   lineValid = false;
           }*/
           
	       //---need to validate NE Number or w/e it is---//
	       
           //---CHECKING THE DATE---//
			
			if (isValidFormat(date)==false){
				lineValid = false;
			}
           
          // System.out.println(lineValid);
	      // System.out.println(line);
           
           //---WRITE DATA---//
           if(lineValid){
        	   BufferedWriter bw = validatedWriter();
        	   bw.write(line);
        	   bw.newLine();
        	   bw.close();
           } else {
        	   BufferedWriter bw = invalidWriter();
        	   bw.write(line);
        	   bw.newLine();
        	   bw.close();
           }
              
	} //endwhile
	} catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	//***********************//
	//***DATA WRITE METHOD***//
	//***********************//
	private BufferedWriter clearWriter() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(path + "/base_data_validated.txt");
		OutputStream os = (OutputStream)new FileOutputStream(file,false);
	    String encoding = "UTF8";
	    OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
	    BufferedWriter bw = new BufferedWriter(osw);
	    return bw;
	}
	
	private BufferedWriter validatedWriter() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(path + "/base_data_validated.txt");
		OutputStream os = (OutputStream)new FileOutputStream(file,true);
	    String encoding = "UTF8";
	    OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
	    BufferedWriter bw = new BufferedWriter(osw);
	    return bw;
	}
	
	private BufferedWriter invalidWriter() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(path + "/base_data_invalid.txt");
		OutputStream os = (OutputStream)new FileOutputStream(file,true);
	    String encoding = "UTF8";
	    OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
	    BufferedWriter bw = new BufferedWriter(osw);
	    return bw;
	}
	
	//************************//
	//***HASH TABLE METHODS***//
	//************************//
	
	//--- GENERATE KEYS FROM BASE DATA AND LOOKUP IN HASH TABLE ---//
		public Boolean validateHashTable(String[] currentLine, int index, Hashtable hashtable){
			Boolean hashBoolean = true;
			
         	//read in the the foreign key
        	String foreignKey = currentLine[index];
        	
        	if (hashtable.containsValue(foreignKey)==false){
        		hashBoolean = false;
        	}
	            	
			return hashBoolean;
		}
		
		//two foreign keys
		public Boolean validateDoubleHashTable(String[] currentLine, int index1, int index2, Hashtable hashtable){
			Boolean hashBoolean = true;
	             	
         	//read in the foreign keys
         	String key1 = currentLine[index1];
        	String key2 = currentLine[index2];
        	String foreignKey = new String(key1+key2);
        	
        	if (hashtable.containsValue(foreignKey)==false){
        		hashBoolean = false;
        	}
	         
			return hashBoolean;
		}
		
		//--- MAKE HASH TABLE FOR FOREIGN KEY VALIDATION ---//
		//one foreign key
		public void makeHashTable(String fileLocation, String splitBy, int index, Hashtable hashtable){
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));
				 //iteration variable removes the headings from the data file
				int iteration = 0;
	         	
				while ((line = br.readLine()) != null) {
					
	             	if (iteration == 0){
	             		iteration ++;
	             			continue;
	             	}
	             	
	             	// use splitBy as separator to read fields in data line
	             	String[] currentLine = line.split(splitBy);
	             	
	             	//read in the Cause Code and EventID
	             	String foreignKey = currentLine[index];
	            	
	            	hashtable.put(foreignKey, foreignKey);
	             }
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
		
		//two foreign keys
		public void makeDoubleHashTable(String fileLocation, String splitBy, int index1, int index2, Hashtable hashtable){
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));
				 //iteration variable removes the headings from the data file
				int iteration = 0;
	         	
				while ((line = br.readLine()) != null) {
					
	             	if (iteration == 0){
	             		iteration ++;
	             			continue;
	             	}
	             	
	             	// use splitBy as separator to read fields in data line
	             	String[] currentLine = line.split(splitBy);
	             	
	             	//read in the Cause Code and EventID
	             	String key1 = currentLine[index1];
	            	String key2 = currentLine[index2];
	            	String foreignKey = new String(key1+key2);
	            	
	            	hashtable.put(foreignKey, foreignKey);
	             }
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
		
		//*****************//
		//***DATE METHOD***//
		//*****************//
		
		public static boolean isValidFormat(String value) {
	        Date date = null;
	        
	        String regex = "([0-9]{2})/(.*?)";
	        Matcher m = Pattern.compile(regex).matcher(value);
	        
	        if(m.matches()){
	        	
	        	try {
		            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
		            date = sdf.parse(value);
		            if (!value.equals(sdf.format(date))) {
		                date = null;
		            }
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
		        return date != null;
	        	
	        } else {
	        
		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yy HH:mm");
		            date = sdf.parse(value);
		            if (!value.equals(sdf.format(date))) {
		                date = null;
		            }
		        } catch (ParseException ex) {
		            ex.printStackTrace();
		        }
		        return date != null;
		        
	        }
	    }
		
		//source: http://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
	
	public static void main(String[] args) {
		new Validation();
	
	}

}
