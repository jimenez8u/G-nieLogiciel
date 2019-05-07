package reseauSocial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import JSON.JsonConverter;
import JSON.JsonLink;
import reseauSocial.dataFormat.LinkProperty;

public class DataFormatTest 
{
	
	@Test
	public void jsonLinkConvertionTest() throws IOException 
	{
		LinkProperty first = new LinkProperty("premier test", "oui");
		String json = JsonConverter.convert(first);
		LinkProperty second = JsonConverter.getLinkProperty(json);
		
		assertEquals(first.toString(), second.toString());
	}
	
	@Test
	public void jsonLinkPropertyConvertionTest() throws IOException 
	{
		LinkProperty lP = new LinkProperty("premier lien", "oui");
		ArrayList<LinkProperty> lPList = new ArrayList<LinkProperty>();
		lPList.add(lP);
		
		JsonLink first = new JsonLink("Lien", lPList, "un noeud", "un autre noeud");
		String json = JsonConverter.convert(first);
		JsonLink second = JsonConverter.getLink(json);
		
		assertEquals(first.toString(), second.toString());
	}
}
