package JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		/*ArrayList<LinkProperty> propertyList = new ArrayList<LinkProperty>();
		JsonSocialNetwork jSN= new JsonSocialNetwork();
		String sNJean = "Jean";
		String sNJacques = "Jacques";
		
		LinkProperty lProperty = new LinkProperty("premier test", "oui");
		propertyList.add(lProperty);
		
		JsonLink jL = new JsonLink("premier lien", propertyList, sNJean, sNJacques);
		JsonLink jL2 = new JsonLink("premier lien", propertyList, sNJacques, sNJean);
		jSN.addLink(jL);
		jSN.addLink(jL2);
		
		String json = JsonConverter.convert(jSN);*/
		
		/*File file = new File("F:\\PC\\Dowloads\\graphetest.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String json ="";
		json = br.readLine();
		br.close();
		
		System.out.println(json);
		JsonSocialNetwork jSN2 = JsonConverter.getJsonSocialNetwork(json);
		
		ArrayList<String> JsonNodeList = new ArrayList<String>();
		ArrayList<SocialNode> nodeList = new ArrayList<SocialNode>();
		ArrayList<Link> linkList = new ArrayList<Link>();
		
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
			sNetwork.addNode(node);*/


		File file = new File("F:\\PC\\Dowloads\\graphetest.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String json ="";
		json = br.readLine();
		br.close();
		
		SocialNetwork sNetwork = JsonConverter.getSocialNetwork(json);
		
		System.out.println(sNetwork.getNbNode());
		System.out.println(sNetwork.toString());
		
		SocialNode sn = sNetwork.getSocialNetwork().get(2);
		SocialNode sn2 = sNetwork.getSocialNetwork().get(0);
		
		System.out.println("\n\n\n"+sn2);
		System.out.println(sn.getLinkList().get(0).getNoeudDepart());
	}
}
