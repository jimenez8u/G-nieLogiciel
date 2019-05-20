package JSON;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class JsonConverter {
	public static String convert(Object o) throws JsonProcessingException
	{
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(o);
		return jsonStr;
	}
	
	public static SocialNetwork getSocialNetwork(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ArrayList<String> JsonNodeList = new ArrayList<String>();
		ArrayList<SocialNode> nodeList = new ArrayList<SocialNode>();
		ArrayList<Link> linkList = new ArrayList<Link>();
		
		JsonSocialNetwork jSN2 = JsonConverter.getJsonSocialNetwork(str);
		
		for ( JsonLink link : jSN2.getLinkList())
		{
			SocialNode noeudArrive = new SocialNode(link.getNoeudArrive());
			SocialNode noeudDepart = new SocialNode(link.getNoeudDepart());
			if(!JsonNodeList.contains(link.getNoeudArrive()))
			{
				JsonNodeList.add(link.getNoeudArrive());
				nodeList.add(noeudArrive);
			}
			if(!JsonNodeList.contains(link.getNoeudDepart()))
			{
				JsonNodeList.add(link.getNoeudDepart());
				nodeList.add(noeudDepart);
			}
			linkList.add(new Link(link.getLinkName(), link.getProperties(), noeudDepart, noeudArrive));
		}
		
		//add every links to the right nodes
		for(SocialNode node : nodeList)
		{
			for(Link link : linkList)
			{
				if(link.getNoeudArrive().getName().equals(node.getName()))
					node.addLink(link);
				else if(link.getNoeudDepart().getName().equals(node.getName()))
					node.addLink(link);
			}
		}
		
		SocialNetwork sNetwork = new SocialNetwork();
		for(SocialNode node : nodeList)
			sNetwork.addNode(node);
		return sNetwork;
	}
	
	public static SocialNode getSocialNode(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper Obj = new ObjectMapper();
		SocialNode SocialNo = Obj.readValue(str, SocialNode.class);
		return SocialNo;
	}
	
	public static LinkProperty getLinkProperty(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper Obj = new ObjectMapper();
		LinkProperty linkppt = Obj.readValue(str, LinkProperty.class);
		return linkppt;
	}
	
	public static JsonLink getJsonLink(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper Obj = new ObjectMapper();
		JsonLink link = Obj.readValue(str, JsonLink.class);
		return link;
	}
	
	public static Link getLink(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper Obj = new ObjectMapper();
		Link link = Obj.readValue(str, Link.class);
		return link;
	}
	
	public static JsonSocialNetwork getJsonSocialNetwork(String str) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper Obj = new ObjectMapper();
		JsonSocialNetwork arrayJLink = Obj.readValue(str, JsonSocialNetwork.class);
		return arrayJLink;
	}
}
