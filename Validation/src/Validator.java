import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
 
public class Validator {
	
	private Date maxDate = null;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	private ArrayList<Integer> causeCodeList = new ArrayList<Integer>();
	private ArrayList<String> eventIdList = new ArrayList<String>();
	
	
	//initialise the same array type
	//read in the array from other class
	
	Validator(){
	}
	//public boolean isValidEventCause(int eventCause){
		//eventCauseList.get(0).
		
		/*ColumnReaderEventCause testEventCause = new ColumnReaderEventCause();
	
		if (TestDataSheet[1]== null){
        	return false;
        }else if(TestDataSheet[8] == null){
        	return false;
        }else{
        	System.out.println(TestDataSheet[1]+TestDataSheet[8]);		
	}*/
	
	public boolean isValidDate(String inDate) 
	{	
		ColumnReaderDate testDate = new ColumnReaderDate();
				
		// If inDate is not Null set it as a Date Object
		if (inDate == null){
			return false;
		} else {
			try {
				maxDate = format.parse(inDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(maxDate);
	    
		// Validate Date Object
		if (inDate.trim().length() != format.toPattern().length() 
				|| maxDate.after(new Date())){
			return false;
		} else {
			// Strictly matches Date Object to Format
			format.setLenient(false);
			return true;
		}
	}
}
