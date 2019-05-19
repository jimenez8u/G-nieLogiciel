package reseauSocial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import JSON.JsonConverter;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;
import reseauSocial.parcours.Parcours;

class ParcoursTest {

	private final String GRAPHSTRING = "{\"linkList\":["
			+ "{\"linkName\":\"l1\",\"properties\":[{\"propertyName\":\"l1\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"1\",\"noeudArrive\":\"2\"},"
			+ "{\"linkName\":\"l2\",\"properties\":[{\"propertyName\":\"l2\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"2\",\"noeudArrive\":\"3\"},"
			+ "{\"linkName\":\"l3\",\"properties\":[{\"propertyName\":\"l3\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"3\",\"noeudArrive\":\"4\"},"
			+ "{\"linkName\":\"l4\",\"properties\":[{\"propertyName\":\"l4\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"4\",\"noeudArrive\":\"2\"},"
			+ "{\"linkName\":\"l5\",\"properties\":[{\"propertyName\":\"l5\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"4\",\"noeudArrive\":\"5\"},"
			+ "{\"linkName\":\"l6\",\"properties\":[{\"propertyName\":\"l6\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"1\",\"noeudArrive\":\"5\"}]}";
	
	private SocialNode s1 = new SocialNode("1");
	private SocialNode s2 = new SocialNode("2");
	private SocialNode s3 = new SocialNode("3");
	private SocialNode s4 = new SocialNode("4");
	private SocialNode s5 = new SocialNode("5");
	
	@Test
	void largeurOrdreTest() throws JsonParseException, JsonMappingException, IOException {
		List<SocialNode> listResult = Arrays.asList(s1,s2,s5,s3,s4);
		List<SocialNode> listTest;
		SocialNetwork sN = JsonConverter.getSocialNetwork(GRAPHSTRING);
		Parcours p = new Parcours();
		int index = sN.getSocialNetwork().indexOf(s1);
		listTest = p.parcoursLargeur(sN.getSocialNetwork().get(index));
		assertEquals(listResult, listTest);
	}

}
