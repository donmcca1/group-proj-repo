import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class FKValidation {
	
	//variables and storage
	
	//for importing files
	String baseDataFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/base_data.txt";
	String causeCodeFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/event_cause.txt";
	String mccMncFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/mcc_mnc.txt";
	String ueFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/ue.txt";
	String failureClassFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/failure_class.txt";
    String line = "";
    String splitBy = ";";
    
    //for storing foreign keys
    Hashtable causeCodeEventIdTable = new Hashtable();
    Hashtable mccMncTable = new Hashtable();
    Hashtable ueTable = new Hashtable();
    Hashtable failureClassTable = new Hashtable();

	//constructor
	public FKValidation(){
		
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
		
	}
	
	//--- GENERATE KEYS FROM BASE DATA TO LOOKUP IN HASH TABLE ---//
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
             	
             	//read in the Cause Code and EventID
            	String foreignKey = currentLine[index];
            	
            	if(foreignKey.equals("(null)")){
            		System.out.println("No foreign key present.");
            	}else{
            		System.out.println(hashtable.containsValue(foreignKey));
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
             	
             	//read in the Cause Code and EventID
             	String key1 = currentLine[index1];
            	String key2 = currentLine[index2];
            	String foreignKey = new String(key1+key2);
            	
            	if(key1.equals("(null)") || key2.equals("(null)")){
            		System.out.println("No foreign key present.");
            	}else{
            		System.out.println(hashtable.containsValue(foreignKey));
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
	
	
	//main method
	public static void main(String[] args) {
		new FKValidation();
	}

}
