import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class XMLParser {

	
	public XMLParser(){
		
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public ArrayList<Element> parse(String filename) throws XMLParserException{
		//BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
		
		ArrayList<Element> elementList = new ArrayList<Element>();
		BufferedReader in;
		int count = 0;
		try {
			in = new BufferedReader(new FileReader(filename));
			String inputLine;
			Element currentElement = null;

			while(( inputLine = in.readLine()) != null){
				//count++;
				if(count >= 51){
					break;
				}
				inputLine = inputLine.trim();
				if(inputLine.charAt(0) == "<".charAt(0)){
					if(inputLine.contains("<?")){
						continue;
					}
					if(inputLine.contains("</")){
						if(currentElement != null){
							System.out.println("</"+currentElement.getName()+">");
							currentElement = currentElement.getParent();
						} else {
							throw new XMLParserException(new CorruptedXMLSyntaxException(filename, "Closed tag on root element"));
						}
					} else {
						
						if(inputLine.contains("/>")){ //self closing tag
							
							Element child = new Element(currentElement, inputLine.substring(1, inputLine.length()-2));
							if(currentElement != null){
								currentElement.addChild(child);
							}
							elementList.add(child);
							currentElement = child;
							
							if(currentElement != null){
								currentElement = currentElement.getParent();
							} else {
								throw new XMLParserException(new CorruptedXMLSyntaxException(filename, "Closed tag on root element"));
							}
						} else {
							
							Element child = new Element(currentElement, inputLine.substring(1, inputLine.length()-1));
							if(currentElement != null){
								currentElement.addChild(child);
							}
							elementList.add(child);
							currentElement = child;
						}
					}
				}
			}
			if(currentElement != null && count < 51){
				throw new XMLParserException(new CorruptedXMLSyntaxException(filename, "Failed to return to root element; Unclosed tag in XML file"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elementList;
	}
}
