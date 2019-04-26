package reseauSocial.dataFormat;

import java.io.IOException;
import java.util.ArrayList;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		ArrayList<LinkProperty> propertyList = new ArrayList<LinkProperty>();
		ArrayList<Link> linkList = new ArrayList<Link>();
		
		LinkProperty lProperty = new LinkProperty("premier test", "oui");
		propertyList.add(lProperty);
		SocialNode sNJean = new SocialNode("Jean");
		Link sL = new Link("premier lien", propertyList, sNJean);
		linkList.add(sL);
		SocialNode sNJacques = new SocialNode("Jacques", linkList);
		
		SocialNetwork sN = new SocialNetwork();
		sN.addNode(sNJacques);
		sN.addNode(sNJean);
		
		String json = JsonConverter.convert(sN);
		System.out.println(json);
	}

}
