            import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
            import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

            public class ColumnReaderEventCause {
            	
            	private String isValidDate(String FilesToRead ) {
{
            	List<String[]> csvFileBase = FilesToRead("csvFiles/authors.csv");
                List<String[]> csvFileEventCause = FilesToRead("csvFiles/books.csv");
                
                public static void main(String[] args) {
                	String FilesToRead = new ArrayList<String>();
                	BufferedReader br = null;    
                    String line = "";    
                    String splitBy = ";";    
                    try {    
                	/*try(
                			FileInputStream BaseData = new FileInputStream("Z:/Masters/JEECourse/CellExtraction/Test2Data.csv");
                			FileInputStream EventCauseData = new FileInputStream("Z:/Masters/JEECourse/CellExtraction/Test2DataEventCause.csv");
                			SequenceInputStream is=new SequenceInputStream(BaseData, EventCauseData);
                			  BufferedReader reader=new BufferedReader(new InputStreamReader(is, charset));)
                	{
                		
                	}*/
                	
                   /*String csvFileBase = "Z:/Masters/JEECourse/CellExtraction/Test2Data.csv";
                    String lineBase = "";
                    String cvsSplitByBase = ",";
                    String csvFileEventCause = "Z:/Masters/JEECourse/CellExtraction/Test2DataEventCause.csv";
                    String lineEventCause = "";
                    String cvsSplitByEventCause = ",";*/
                    //try (BufferedReader br = new BufferedReader(new FileReader(FilesToRead))) {
                    	
                    	//iteration variable removes the headings from the data file
                    	
                    	int iteration = 0;
                    	
                        while ((line = br.readLine()) != null) {
                        	
                        	if (iteration == 0){
                        		iteration ++;
                        			continue;
                        	}
                        	
                        	
                        	// use comma as separator to read fields in data line
                            String[] BaseDataSheet = line.split(FilesToRead);
                            
                            //fields to be validated
                            
                            
                            
                        	String operator = BaseDataSheet[5];
                        	String market = BaseDataSheet[4];
                        	String imsi = BaseDataSheet[10];
                        	
                            
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
                            	System.out.println(true);
                            }else{
                            	System.out.println(false);
                            }
                            
                           /*System.out.println(market+operator);
                           System.out.println(imsi.substring(0,6));*/
                        }
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                	  
                }

            }
