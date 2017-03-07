import java.io.BufferedReader;
import java.io.FileReader;
import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class BaseDataIntValidation {
	//importing file"s"(as needed)
	String baseDataFile = "Z:/Masters/JEECourse/CellExtraction/Test2Data.csv";
	String line = "";
    String splitBy = ";";
	

	public BaseDataIntValidation() {
		
		public  void intValidation(){
			try (BufferedReader br = new BufferedReader(new FileReader(baseDataFile))) {
	         	
				 //iteration variable removes the headings from the data file
				int iteration = 0;
	         	
				while ((line = br.readLine()) != null) {
					
	             	if (iteration == 0){
	             		iteration ++;
	             			continue;
	             	}
	             // use comma as separator to read fields in data line
                    String[] BaseDataSheet = line.split(splitBy);
                    //fields to be validated
                    String eventID = BaseDataSheet[1];
                    String failureClass = BaseDataSheet[2];
                    String ueType = BaseDataSheet[3];
                    String market = BaseDataSheet[4];
                    String operator = BaseDataSheet[5];
                    String cellID = BaseDataSheet[6];
                    String duration = BaseDataSheet[7];
                    String causeCode = BaseDataSheet[8];
                    String imsi = BaseDataSheet[10];
                    
                    //Checking eventID for any values not of type int
                    NumberUtils.isCreatable(eventID);
				
                    {
                       // Number is integer
                    }
				}
			} 			
		}
	}
            			
	             			