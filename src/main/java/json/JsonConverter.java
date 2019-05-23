package json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reseausocial.dataformat.Link;
import reseausocial.dataformat.LinkProperty;
import reseausocial.dataformat.SocialNetwork;
import reseausocial.dataformat.SocialNode;

public class JsonConverter {
	
	private JsonConverter()
	{
		throw new IllegalStateException("Utility class");
	}
	
	public static String convert(Object o) throws JsonProcessingException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(o);
	}
	
	public static SocialNetwork getSocialNetwork(String str) throws IOException
	{
		List<String> jsonNodeList = new ArrayList<>();
		List<SocialNode> nodeList = new ArrayList<>();
		List<Link> linkList = new ArrayList<>();
		
		JsonSocialNetwork jSN2 = JsonConverter.getJsonSocialNetwork(str);
		
		for ( JsonLink link : jSN2.getLinkList())
		{
			SocialNode noeudArrive = new SocialNode(link.getNoeudArrive());
			SocialNode noeudDepart = new SocialNode(link.getNoeudDepart());
			if(!jsonNodeList.contains(link.getNoeudArrive()))
			{
				jsonNodeList.add(link.getNoeudArrive());
				nodeList.add(noeudArrive);
			}
			if(!jsonNodeList.contains(link.getNoeudDepart()))
			{
				jsonNodeList.add(link.getNoeudDepart());
				nodeList.add(noeudDepart);
			}
			linkList.add(new Link(link.getLinkName(), link.getProperties(), noeudDepart, noeudArrive));
		}
		
		//add every links to the right nodes
		for(SocialNode node : nodeList)
		{
			for(Link link : linkList)
			{
				if(link.getNoeudArrive().getName().equals(node.getName())) { 
					link.setNoeudArrive(node);
					node.addLink(link);
					}
					
				else if(link.getNoeudDepart().getName().equals(node.getName())) {
					link.setNoeudDepart(node);
					node.addLink(link);
					}
			}
		}
		
		SocialNetwork sNetwork = new SocialNetwork();
		for(SocialNode node : nodeList)
			sNetwork.addNode(node);
		return sNetwork;
	}
	
	public static SocialNode getSocialNode(String str) throws IOException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, SocialNode.class);
	}
	
	public static LinkProperty getLinkProperty(String str) throws IOException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, LinkProperty.class);
	}
	
	public static JsonLink getJsonLink(String str) throws IOException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, JsonLink.class);
	}
	
	public static Link getLink(String str) throws IOException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, Link.class);
	}
	
	public static JsonSocialNetwork getJsonSocialNetwork(String str) throws IOException
	{
		ObjectMapper obj = new ObjectMapper();
		return obj.readValue(str, JsonSocialNetwork.class);
	}
}
