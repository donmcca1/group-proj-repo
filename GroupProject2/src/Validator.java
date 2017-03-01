
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
 
public class Validator {
	
	private Date maxDate = null;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private ArrayList<Integer> causeCodeList = new ArrayList<Integer>();
	private ArrayList<String> eventIdList = new ArrayList<String>();
	
	Validator(){
	}
	
	
	public boolean isValidDate(String inDate) 
	{	
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
