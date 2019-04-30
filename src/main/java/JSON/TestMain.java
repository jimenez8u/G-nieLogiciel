package JSON;

import java.io.IOException;
import java.util.ArrayList;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		ArrayList<LinkProperty> propertyList = new ArrayList<LinkProperty>();
		ArrayList<Link> linkList = new ArrayList<Link>();
		ArrayList<JsonLink> jLs= new ArrayList<JsonLink>();
		String sNJean = "Jean";
		String sNJacques = "Jacques";
		
		LinkProperty lProperty = new LinkProperty("premier test", "oui");
		propertyList.add(lProperty);
		
		JsonLink jL = new JsonLink("premier lien", propertyList, sNJean, sNJacques);
		JsonLink jL2 = new JsonLink("premier lien", propertyList, sNJacques, sNJean);
		jLs.add(jL);
		jLs.add(jL2);
		
		String json = JsonConverter.convert(jLs);
		System.out.println(json);
	}
}
