import java.util.ArrayList;
import java.util.HashMap;

import com.sun.xml.internal.ws.util.StringUtils;


public class Element {
	private Element parent;
	private String name;
	private ArrayList<Element> children = new ArrayList<Element>();
	private HashMap<String, String> attributes = new HashMap<String, String>();
	
	public Element(Element parent, String inputString) throws XMLParserException{
		this.parent = parent;
		String[] values = inputString.split(" ");
		if(values == null){
			throw new XMLParserException(new InvalidXMLTagException());
		}
		this.name = values[0];

		System.out.println("<"+ this.name+">");
		for(int i = 1; i<values.length; i++){
			String line = values[i];

			if((line.length() - line.replace("\"", "").length()) == 1){ 
				while(!values[i+1].contains("\"")){
					values[i+1] = values[i].concat(" "+ values[i+1]);
					i++;
				}
				values[i+1] = values[i].concat(" "+ values[i+1]);
				i++;
			}

			String[] attributevalue = values[i].split("=");
			for(int j = 0; j < attributevalue.length; j++){
				attributes.put(attributevalue[j], attributevalue[j+1].replace("\"", ""));
				j++;
			}
		}
		
		//if(!attributes.isEmpty()){
		//	System.out.println(attributes.keySet());
		//	System.out.println(attributes.values());
		//}
	}
	
	public void addChild(Element e){
		children.add(e);
	}
	
	public void addAttribute(String attribute, String value){
		attributes.put(attribute, value);
	}
	
	public Element getParent(){
		return parent;
	}
	
	public ArrayList<Element> getChildren(){
		return children;
	}
	
	public HashMap<String, String> getAttributes(){
		return attributes;
	}
	
	public boolean isRoot(){
		return parent == null;
	}
	
	public boolean hasChildren(){
		return !children.isEmpty();
	}
	
	public int numberOfChildren(){
		return children.size(); 
	}
	
	public int numberOfAttributes(){
		return attributes.size();
	}
	
	public String getName(){
		return name;
	}
}
