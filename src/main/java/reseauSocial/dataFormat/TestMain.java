package reseauSocial.dataFormat;

import java.io.IOException;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		LinkProperty propertyList[] = new LinkProperty[1];
		Link linkList[] = new Link[1];
		
		LinkProperty lProperty = new LinkProperty("premier test", "oui");
		propertyList[0] = lProperty;
		SocialNode sNJean = new SocialNode("Jean", null);
		Link sL = new Link("premier lien", propertyList, sNJean);
		linkList[0] = sL;
		SocialNode sNJacques = new SocialNode("Jacques", linkList);
		
		SocialNetwork sN = new SocialNetwork();
		sN.addNode(sNJacques);
		sN.addNode(sNJean);
		
		String json = JsonConverter.convert(sN);
		System.out.println(json);
	}

}
