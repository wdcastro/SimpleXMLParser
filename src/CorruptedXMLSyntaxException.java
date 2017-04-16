
public class CorruptedXMLSyntaxException extends Exception{
	
	public CorruptedXMLSyntaxException(String filename, String additionalInfo){
		System.err.println("Suspected corrupted syntax in XML file: " + filename);
		System.err.println(additionalInfo);
		this.printStackTrace();
	}

}
