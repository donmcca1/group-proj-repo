            import java.io.BufferedReader;
            import java.io.FileReader;
            import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

            public class ColumnReaderEventCause {

                public static void main(String[] args) {
                	
                	String csvFile = "Z:/Masters/JEECourse/CellExtraction/Test2Data.csv";
                    String line = "";
                    String cvsSplitBy = ",";
                    List<String[]> eventCauseList = new ArrayList<String[]>();
                  
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    	//iteration variable removes the headings from the data file
                    	int interation = 0;
                        while ((line = br.readLine()) != null) {
                        	if (interation == 0){
                        		interation ++;
                        			continue;
                        		}
                            // use comma as separator
                            String[] TestDataSheet = line.split(cvsSplitBy);
                            //specify columns being called starting at column 0 = column
                            
                            String[] TestDataSubset = new String[]{TestDataSheet[1],TestDataSheet[8]};
                            
                            eventCauseList.add(TestDataSubset);
                            
                           System.out.println(TestDataSheet[1]+TestDataSheet[8]);
                        }
                                       
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                
            }
                	
            
          