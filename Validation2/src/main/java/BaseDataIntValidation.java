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
    
    	validateFields();
    }
    
    //validation method
    public void validateFields(){
    	
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
                String cellID = currentLine[6];
                String duration = currentLine[7];
                String imsi = currentLine[10];
                
                //--- CELL ID ---//
                //checks it's a four digit number
                
                if(NumberUtils.isDigits(cellID) && cellID.length()==4){
            		Boolean isValid = true;
            		System.out.println(isValid);
                } else {
                	Boolean isValid = false;
                	System.out.println(isValid);
                };
                
                //--- DURATION ---//
                //checks it's a number
                
                if(NumberUtils.isDigits(duration)){
                	Boolean isValid = true;
                	System.out.println(isValid);
                } else {
                	Boolean isValid = false;
                	System.out.println(isValid);
                };
                
                //--- IMSI ---//
                //checks it's a 15-digit number
                
                if(NumberUtils.isDigits(imsi) && imsi.length()==15){
            		Boolean isValid = true;
            		System.out.println(isValid);
                } else {
                	Boolean isValid = false;
                	System.out.println(isValid);
                };
    		
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
            			
	             			