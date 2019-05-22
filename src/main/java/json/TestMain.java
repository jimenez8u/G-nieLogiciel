package json;

import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class TestMain {

	private static Logger logger = Logger.getLogger(TestMain.class.getName());
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("F:\\PC\\Dowloads\\graphetest.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String json = "";
		try
		{
			json = br.readLine();
		}
		catch(Exception e)
		{
			logger.severe("\nErreur de lecture de fichier");
		}
		finally
		{
			br.close();
		}
		
		SocialNetwork sNetwork = JsonConverter.getSocialNetwork(json);
		
		System.out.println(sNetwork.getNbNode());
		System.out.println(sNetwork.toString());
		
		SocialNode sn = sNetwork.getSocialNetwork().get(2);
		SocialNode sn2 = sNetwork.getSocialNetwork().get(0);
		
		System.out.println("\n\n\n" + sn2);
		System.out.println(sn.getLinkList().get(0).getNoeudDepart());
	}
}
