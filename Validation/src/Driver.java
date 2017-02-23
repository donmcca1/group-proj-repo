
public class Driver {

	public static void main(String[] args) 
	{
		Validator test = new Validator();
		
		System.out.println(test.isValidDate("21-12-2000"));
	    System.out.println(test.isValidDate("31-12-2020"));
	    System.out.println(test.isValidDate("22-02-2017"));
	    System.out.println(test.isValidDate("23-02-2017"));
	}

}
