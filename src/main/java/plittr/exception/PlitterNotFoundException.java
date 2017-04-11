package plittr.exception;


public class PlitterNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915562375279563449L;

	public PlitterNotFoundException() {
	}
	public String getLocalizedMessage(){
		return "Plitter Not Found";
		
	}
	
	

}
