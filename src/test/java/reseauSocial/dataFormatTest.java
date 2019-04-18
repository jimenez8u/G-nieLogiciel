package reseauSocial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;

import reseauSocial.dataFormat.JsonConverter;
import reseauSocial.dataFormat.LinkProperty;

public class DataFormatTest 
{
	@Test
	public void JSONConvertionTest() throws IOException 
	{
		LinkProperty first = new LinkProperty("premier test", "oui");
		String json = JsonConverter.convert(first);
		LinkProperty second = JsonConverter.getLinkProperty(json);
		
		assertEquals(first.getPropertyName()+first.getPropertyValue(), second.getPropertyName()+second.getPropertyValue());
	}
}
