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
		ObjectMapper Obj = new ObjectMapper();
		SocialNetwork socialNe = Obj.readValue(str, SocialNetwork.class);
		return socialNe;
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
