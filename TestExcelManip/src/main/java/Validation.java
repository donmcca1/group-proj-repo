import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.lang3.math.NumberUtils;

public class Validation {
	
	//importing files NEED TO CHANGE TO GET THE LOCATION DYNAMICALLY
	String baseDataFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/base_data.txt";
	String causeCodeFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/event_cause.txt";
	String mccMncFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/mcc_mnc.txt";
	String ueFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/ue.txt";
	String failureClassFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/failure_class.txt";
	
	//formatting
	String line = "";
	String splitBy = ";";
	
	//for storing foreign keys
    Hashtable causeCodeEventIdTable = new Hashtable();
    Hashtable mccMncTable = new Hashtable();
    Hashtable ueTable = new Hashtable();
    Hashtable failureClassTable = new Hashtable();
    
    //for validating date
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	
	//valid flag
	Boolean lineValid = true;
	
	public Validation(){
		try (BufferedReader br = new BufferedReader(new FileReader(baseDataFile))) {
         	
   		 //iteration variable removes the headings from the data file
   		int iteration = 0;
        	
   		while ((line = br.readLine()) != null) {
   			
			if (iteration == 0){
				iteration ++;
					continue;
			}

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
			makeDoubleHashTable(causeCodeFile,splitBy,0,1,causeCodeEventIdTable);
			validateDoubleHashTable(baseDataFile,splitBy,8,1,causeCodeEventIdTable);
			
			//Mcc/MNC
			makeDoubleHashTable(mccMncFile,splitBy,0,1,mccMncTable);
			validateDoubleHashTable(baseDataFile,splitBy,4,5,mccMncTable);
			
			//UE type
			makeHashTable(ueFile,splitBy,0,ueTable);
			validateHashTable(baseDataFile,splitBy,3,ueTable);
			
			//Failure class
			makeHashTable(failureClassFile,splitBy,0,failureClassTable);
			validateHashTable(baseDataFile,splitBy,2,failureClassTable);
			
			//---CHECKING THE INTS---//
			//CellID
			//checks it's a four digit number
	       
			if(NumberUtils.isDigits(cellID) && cellID.length()==4){
				
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
           if (operator.length()==1){
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
           }
           
           //---CHECKING THE DATE---//
           if (date.trim().length() != format.toPattern().length()){
        	   lineValid = false;
           }
	       
	
	} //endwhile
	} catch (IOException e) {
	        e.printStackTrace();
	   }
	}
	
	//************************//
	//***HASH TABLE METHODS***//
	//************************//
	
	//--- GENERATE KEYS FROM BASE DATA AND LOOKUP IN HASH TABLE ---//
		public void validateHashTable(String fileLocation, String splitBy, int index, Hashtable hashtable){
			try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
	         	
				 //iteration variable removes the headings from the data file
				int iteration = 0;
	         	
				while ((line = br.readLine()) != null) {
					
	             	if (iteration == 0){
	             		iteration ++;
	             			continue;
	             	}
	             	
	             	// use splitBy as separator to read fields in data line
	             	String[] currentLine = line.split(splitBy);
	             	
	             	//read in the the foreign key
	            	String foreignKey = currentLine[index];
	            	
	            	if(foreignKey.equals("(null)")){

	            	}else if (hashtable.containsValue(foreignKey)==false){
	            		lineValid=false;
	            	}
	            }
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
		
		//two foreign keys
		public void validateDoubleHashTable(String fileLocation, String splitBy, int index1, int index2, Hashtable hashtable){
			try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
	         	
				 //iteration variable removes the headings from the data file
				int iteration = 0;
	         	
				while ((line = br.readLine()) != null) {
					
	             	if (iteration == 0){
	             		iteration ++;
	             			continue;
	             	}
	             	
	             	// use splitBy as separator to read fields in data line
	             	String[] currentLine = line.split(splitBy);
	             	
	             	//read in the foreign keys
	             	String key1 = currentLine[index1];
	            	String key2 = currentLine[index2];
	            	String foreignKey = new String(key1+key2);
	            	
	            	if(key1.equals("(null)") || key2.equals("(null)")){
	            
	            	}else if (hashtable.containsValue(foreignKey)==false){
	            		lineValid=false;
	            	}
	            }
	             
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
		}
		
		//--- MAKE HASH TABLE FOR FOREIGN KEY VALIDATION ---//
		//one foreign key
		public void makeHashTable(String fileLocation, String splitBy, int index, Hashtable hashtable){
			try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
	         	
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
			try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
	         	
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
	
	public static void main(String[] args) {
		new Validation();
	
	}

}
