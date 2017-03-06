import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class CauseCodeEventIdFK {
	
	//variables and storage
	
	//for importing files
	String baseDataFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/base_data.txt";
	String causeCodeFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/event_cause.txt";
    String line = "";
    String splitBy = ";";
    
    //for storing foreign keys
    Hashtable causeCodeEventIdTable = new Hashtable();
   

	//constructor
	public CauseCodeEventIdFK(){
		makeHashTable(causeCodeFile,splitBy);
		validateCauseCodeEventIdKey(baseDataFile,splitBy);
	}
	
	//--- GENERATE KEYS FROM BASE DATA TO LOOKUP IN HASH TABLE ---//
	public void validateCauseCodeEventIdKey(String fileLocation, String splitBy){
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
             	String causeCode = currentLine[8];
            	String eventId = currentLine[1];
            	String foreignKey = new String(causeCode+eventId);
            	
            	if(causeCode.equals("(null)")){
            		System.out.println("No foreign key present.");
            	}else{
            		System.out.println("does cause code/event id exist: " + causeCodeEventIdTable.containsValue(foreignKey));
            	}
            }
             
         } catch (IOException e) {
             e.printStackTrace();
         }
	}
	
	//--- MAKE HASH TABLE FOR FOREIGN KEY VALIDATION ---//
	public void makeHashTable(String fileLocation, String splitBy){
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
             	String causeCode = currentLine[0];
            	String eventId = currentLine[1];
            	String foreignKey = new String(causeCode+eventId);
            	
            	causeCodeEventIdTable.put(foreignKey, foreignKey);
             }
             
         } catch (IOException e) {
             e.printStackTrace();
         }
	}
	
	
	//main method
	public static void main(String[] args) {
		new CauseCodeEventIdFK();
	}

}
