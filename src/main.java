import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLParser xp = new XMLParser();
		
		try {
			ArrayList<Element> elements;
				elements = xp.parse("C:/Users/John/Documents/untitled.tmx");


			System.out.println("---------------------------------");
			//for(int i = 0; i < elements.size(); i++){
			//	System.out.println(elements.get(i).getName());
			//}
			
			//System.out.println(elements.get(0).getChildren());
		} catch (XMLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
