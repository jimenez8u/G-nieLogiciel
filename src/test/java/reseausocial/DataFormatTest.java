package reseausocial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import json.JsonConverter;
import json.JsonLink;
import reseausocial.dataformat.Link;
import reseausocial.dataformat.LinkProperty;
import reseausocial.dataformat.SocialNetwork;
import reseausocial.dataformat.SocialNode;

public class DataFormatTest 
{
	private SocialNetwork sNw;
	
	@Before
	public void initialize() throws IOException
	{
		File file = new File("files/graphetest.txt");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String json;
		json = br.readLine();
		br.close();
		
		sNw = JsonConverter.getSocialNetwork(json);
	}
	
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
	
	@Test
	public void jsonNoeudDepartTest() throws IOException
	{
		SocialNode sn = sNw.getSocialNetwork().get(2);
		SocialNode sn2 = sNw.getSocialNetwork().get(0);
		
		assertEquals(sn2.toString(), sn.getLinkList().get(0).getNoeudDepart().toString());
	}
	
	@Test
	public void jsonNoeudArriveTest() throws IOException
	{
		SocialNode sn = sNw.getSocialNetwork().get(2);
		
		assertEquals(sn.toString(), sn.getLinkList().get(0).getNoeudArrive().toString());
	}
	
	@Test
	public void getNodeByNameTest() throws IOException
	{
		assertEquals("4", sNw.getNodeByName("4").getName());
	}
}
