package reseauSocial.dataFormat;

import java.util.ArrayList;

public class SocialNetwork {
	private ArrayList<SocialNode> socialNetwork;
	private int nbNode;

	public SocialNetwork() {
		this.socialNetwork = new ArrayList<SocialNode>();
		this.nbNode = 0;
	}
	
	public void addNode(SocialNode node)
	{
		this.socialNetwork.add(node);
		this.nbNode++;
	}
	public ArrayList<SocialNode> getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(ArrayList<SocialNode> socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	public int getNbNode() {
		return nbNode;
	}
	public void setNbNode(int nbNode) {
		this.nbNode = nbNode;
	}
}
