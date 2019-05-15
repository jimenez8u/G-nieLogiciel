package reseauSocial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import JSON.JsonConverter;
import JSON.JsonLink;
import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class DataFormatTest 
{
	//Tests about java objects
	//LinkProperty
	@Test
	public void linkPropertyConvertionTest() throws IOException 
	{
		LinkProperty first = new LinkProperty("premier test", "oui");
		String json = JsonConverter.convert(first);
		LinkProperty second = JsonConverter.getLinkProperty(json);
		
		assertEquals(first.toString(), second.toString());
	}
	
	//Link
	@Test
	public void linkConvertionTest() throws IOException 
	{
		ArrayList<LinkProperty> alLinkProp = new ArrayList<LinkProperty>();
		SocialNode startingNode = new SocialNode("Départ");
		SocialNode endingNode = new SocialNode("Arrivé");
		Link first = new Link("premier lien", alLinkProp, startingNode, endingNode);
		
		String json = JsonConverter.convert(first);
		
		Link second = JsonConverter.getLink(json);
		
		assertEquals(first.toString(), second.toString());
	}
	
	//SocialNode
	@Test
	public void socialNodeConvertionTest() throws IOException 
	{
		SocialNode first = new SocialNode("premier noeud");
		
		String json = JsonConverter.convert(first);
		
		SocialNode second = JsonConverter.getSocialNode(json);
		
		assertEquals(first.toString(), second.toString());
	}
	
	/* /!\ Do no implement infinite recursion when converted /!\ 
	//SocialNetwork
	@Test
	public void socialNetworkConvertionTest() throws IOException 
	{
		SocialNetwork first = new SocialNetwork();
		
		ArrayList<LinkProperty> alLinkProp = new ArrayList<LinkProperty>();
		SocialNode startingNode = new SocialNode("Départ");
		SocialNode endingNode = new SocialNode("Arrivé");
		Link link = new Link("premier lien", alLinkProp, startingNode, endingNode);
		startingNode.addLink(link);
		endingNode.addLink(link);
		first.addNode(startingNode);
		first.addNode(endingNode);
		
		//String json = JsonConverter.convert(first);
		
		//SocialNetwork second = JsonConverter.getSocialNetwork(json);
		
		assertEquals(1,1);
	}*/
	
	//Tests about JSON objects
	@Test
	public void jsonLinkPropertyConvertionTest() throws IOException 
	{
		LinkProperty lP = new LinkProperty("premier lien", "oui");
		ArrayList<LinkProperty> lPList = new ArrayList<LinkProperty>();
		lPList.add(lP);
		
		JsonLink first = new JsonLink("Lien", lPList, "un noeud", "un autre noeud");
		String json = JsonConverter.convert(first);
		JsonLink second = JsonConverter.getJsonLink(json);
		
		assertEquals(first.toString(), second.toString());
	}
}
