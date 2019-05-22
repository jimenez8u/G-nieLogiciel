package reseauSocial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import JSON.JsonConverter;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;
import reseauSocial.parcours.Parcours;

class ParcoursTest {

	private static String graphString = "{\"linkList\":["
			+ "{\"linkName\":\"l1\",\"properties\":[{\"propertyName\":\"l1\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"1\",\"noeudArrive\":\"2\"},"
			+ "{\"linkName\":\"l2\",\"properties\":[{\"propertyName\":\"l2\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"2\",\"noeudArrive\":\"3\"},"
			+ "{\"linkName\":\"l3\",\"properties\":[{\"propertyName\":\"l3\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"3\",\"noeudArrive\":\"4\"},"
			+ "{\"linkName\":\"l4\",\"properties\":[{\"propertyName\":\"l4\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"4\",\"noeudArrive\":\"2\"},"
			+ "{\"linkName\":\"l5\",\"properties\":[{\"propertyName\":\"l5\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"4\",\"noeudArrive\":\"5\"},"
			+ "{\"linkName\":\"l6\",\"properties\":[{\"propertyName\":\"l6\",\"propertyValue\":\"oui\"}],\"noeudDepart\":\"1\",\"noeudArrive\":\"5\"}]}";

	private List<SocialNode> listTest;
	
	private static int index1;
	private static int index2;
	
	private static SocialNode s1;
	private static SocialNode s2;
	private static SocialNode s3;
	private static SocialNode s4;
	private static SocialNode s5;
	private static SocialNode s8;
	private static SocialNode s9;

	private static File file;
	private static BufferedReader br;
	private static String graph2;
	private  List<SocialNode> result;
	

	private static SocialNetwork sN1;
	private static SocialNetwork sN2;
	
	@BeforeAll
	static void initialize() throws IOException {
		s1 = new SocialNode("1");
		s2 = new SocialNode("2");
		s3 = new SocialNode("3");
		s4 = new SocialNode("4");
		s5 = new SocialNode("5");
		s8 = new SocialNode("8");
		s9 = new SocialNode("9");
		
		file = new File("files/graphetest.txt");
		br = new BufferedReader(new FileReader(file));
		graph2 = br.readLine();
		br.close();
		
		sN1 = JsonConverter.getSocialNetwork(graphString);
		sN2 = JsonConverter.getSocialNetwork(graph2);
		
		index1 = sN1.getSocialNetwork().indexOf(s1);
		index2 = sN2.getSocialNetwork().indexOf(s1);
			
	}

	@Test
	void largeurOrdreTest() throws JsonParseException, JsonMappingException, IOException {
		result = Arrays.asList(s1,s2,s5,s3,s4);
		listTest = Parcours.parcoursLargeur(sN1.getSocialNetwork().get(index1),100);
		assertEquals(result, listTest);
	}

	@Test
	void largeurOrdreTest2() throws JsonParseException, JsonMappingException, IOException {
		result = Arrays.asList(s1,s2,s5,s8,s3,s4,s9);
		listTest = Parcours.parcoursLargeur(sN2.getSocialNetwork().get(index2),100);
		assertEquals(result, listTest);
	}

	@Test
	void longueurOrdreTest() throws JsonParseException, JsonMappingException, IOException {
		result = Arrays.asList(s1, s2, s3, s4, s5);
		listTest = Parcours.parcoursProfondeur(sN1.getSocialNetwork().get(index1),100);
		assertEquals(result, listTest);
	}
	
	@Test
	void longueurOrdreTest2() throws JsonParseException, JsonMappingException, IOException {
		result = Arrays.asList(s1, s2, s3, s4, s5,s8,s9); 
		listTest = Parcours.parcoursProfondeur(sN2.getSocialNetwork().get(index2),100);
		assertEquals(result, listTest);
	}
	
	@Test
	void largeurDabordProfondeurZero () {
		result = Arrays.asList(s1);
		listTest = Parcours.parcoursLargeur(sN2.getSocialNetwork().get(index2),0);
		assertEquals(result, listTest);
	}
	
	@Test
	void largeurDabordProfondeurUn () {
		result = Arrays.asList(s1,s2,s5,s8);
		listTest = Parcours.parcoursLargeur(sN2.getSocialNetwork().get(index2),1);
		assertEquals(result, listTest);
	}

	
	@Test
	void ProfondeurDabordProfondeurZero() {
		result = Arrays.asList(s1);
		listTest = Parcours.parcoursProfondeur(sN2.getSocialNetwork().get(index2),0);
		assertEquals(result, listTest);
	}
	
	@Test
	void ProfondeurDabordProfondeurUn() {
		result = Arrays.asList(s1,s2,s5,s8);
		listTest = Parcours.parcoursProfondeur(sN2.getSocialNetwork().get(index2),1);
		assertEquals(result, listTest);
	}
	
	@Test
	void ProfondeurDabordProfondeurDeux() {
		result = Arrays.asList(s1,s2,s3,s4,s5,s8,s9);
		listTest = Parcours.parcoursProfondeur(sN2.getSocialNetwork().get(index2),2);
		assertEquals(result, listTest);
	}
}
