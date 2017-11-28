package parser;

public class InvalidXMLTagException extends Exception{

	public InvalidXMLTagException(){
		System.err.println("Invalid XML tag; Tried to create new element without name");
		this.printStackTrace();
	}
}
