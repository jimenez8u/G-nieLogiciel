package JSON;

import java.io.IOException;
import java.util.ArrayList;

import reseauSocial.dataFormat.Link;
import reseauSocial.dataFormat.LinkProperty;
import reseauSocial.dataFormat.SocialNetwork;
import reseauSocial.dataFormat.SocialNode;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		ArrayList<LinkProperty> propertyList = new ArrayList<LinkProperty>();
		JsonSocialNetwork jSN= new JsonSocialNetwork();
		String sNJean = "Jean";
		String sNJacques = "Jacques";
		
		LinkProperty lProperty = new LinkProperty("premier test", "oui");
		propertyList.add(lProperty);
		
		JsonLink jL = new JsonLink("premier lien", propertyList, sNJean, sNJacques);
		JsonLink jL2 = new JsonLink("premier lien", propertyList, sNJacques, sNJean);
		jSN.addLink(jL);
		jSN.addLink(jL2);
		
		String json = JsonConverter.convert(jSN);
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
				else if(link.getNoeudArrive().getName().equals(node.getName()))
					node.addLink(link);
			}
		}
		
		SocialNetwork sNetwork = new SocialNetwork();
		for(SocialNode node : nodeList)
			sNetwork.addNode(node);
		System.out.println(sNetwork.getNbNode());
	}
}
