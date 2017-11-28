package parser;

public class XMLParserException extends Exception{
	
	public XMLParserException(Exception e){
		System.err.println("Error with XML Parser");
	}
}
