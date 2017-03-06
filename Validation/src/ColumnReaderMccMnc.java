            import java.io.BufferedReader;
            import java.io.FileReader;
            import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

            public class ColumnReaderMccMnc {

                public static void main(String[] args) {

                    String csvFile = "Z:/Masters/JEECourse/CellExtraction/Test2Data.csv";
                    String line = "";
                    String cvsSplitBy = ",";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    	
                    	//iteration variable removes the headings from the data file
                    	
                    	int iteration = 0;
                    	
                        while ((line = br.readLine()) != null) {
                        	
                        	if (iteration == 0){
                        		iteration ++;
                        			continue;
                        	}
                        	
                        	
                        	// use comma as separator to read fields in data line
                            String[] TestDataSheet = line.split(cvsSplitBy);
                            
                            //fields to be validated
                        	String operator = TestDataSheet[5];
                        	String market = TestDataSheet[4];
                        	String imsi = TestDataSheet[10];
                        	
                            
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
