package reseauSocial.dataFormat;

public class SocialNetwork {
	private SocialNode[] socialNetwork;
	private final int networkSize = 100;
	private int nbNode;

	public SocialNetwork() {
		this.socialNetwork = new SocialNode[networkSize];
		this.nbNode = 0;
	}
	
	public void addNode(SocialNode node)
	{
		this.socialNetwork[nbNode] = node;
		this.nbNode++;
	}
	public SocialNode[] getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(SocialNode[] socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	public int getNbNode() {
		return nbNode;
	}
	public void setNbNode(int nbNode) {
		this.nbNode = nbNode;
	}
}
