package reseauSocial.dataFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	public String convert(Object o) throws JsonProcessingException
	{
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = Obj.writeValueAsString(o);
		return jsonStr;
	}
}
