package reseauSocial.dataFormat;

import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
	private List<SocialNode> socialNodeList;
	private int nbNode;

	public SocialNetwork() {
		this.socialNodeList = new ArrayList<>();
		this.nbNode = 0;
	}
	
	public void addNode(SocialNode node)
	{
		this.socialNodeList.add(node);
		this.nbNode++;
	}
	public List<SocialNode> getSocialNetwork() {
		return socialNodeList;
	}
	public void setSocialNetwork(List<SocialNode> socialNetwork) {
		this.socialNodeList = socialNetwork;
	}
	public int getNbNode() {
		return nbNode;
	}
	public void setNbNode(int nbNode) {
		this.nbNode = nbNode;
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		bld.append("SocialNetwork [socialNetwork=");
		
		for(SocialNode node : socialNodeList)
			bld.append(node.toString() + ", ");
		
		bld.append(", nbNode=" + nbNode + "]");
		return bld.toString();
	
	}
	
	public SocialNode getNodeByName(String name)
	{
		for(SocialNode node : this.socialNodeList)
		{
			if(node.getName().equals(name))
			{
				return node;
			}
		}
		return null;
	}
}
