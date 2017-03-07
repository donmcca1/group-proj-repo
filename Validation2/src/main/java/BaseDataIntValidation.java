import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang3.math.NumberUtils;

public class BaseDataIntValidation {
	
	//importing file"s"(as needed)
	String baseDataFile = "C:/group-repo/group-proj-repo/TestExcelManip/upload/base_data.txt";
	String line = "";
    String splitBy = ";";
    
    //constructor
    public BaseDataIntValidation(){
    
    	intValidation();
    }
    
    //validation method
    public void intValidation(){
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(baseDataFile))) {
         	
    		 //iteration variable removes the headings from the data file
    		int iteration = 0;
         	
    		while ((line = br.readLine()) != null) {
    			
             	if (iteration == 0){
             		iteration ++;
             			continue;
             	} //endif
             	
             // use comma as separator to read fields in data line
             	String[] currentLine = line.split(splitBy);
             	
                //fields to be validated
                String eventID = currentLine[1];
                String failureClass = currentLine[2];
                String ueType = currentLine[3];
                String market = currentLine[4];
                String operator = currentLine[5];
                String cellID = currentLine[6];
                String duration = currentLine[7];
                String causeCode = currentLine[8];
                String imsi = currentLine[10];
                
                //Checking eventID for any values not of type int
                System.out.println(eventID);
                System.out.println(NumberUtils.isDigits(eventID));
    		
    		} //endwhile
    	} catch (IOException e) {
             e.printStackTrace();
        }
}
	
	//main method
		public static void main(String[] args) {
			new BaseDataIntValidation();
		}
}
            			
	             			