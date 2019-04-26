package reseauSocial.dataFormat;

import java.util.ArrayList;

public class SocialNode {
	private String name;
	private ArrayList<Link> linkList;
	//private Type type; To uncomment if types are implemented
	
	public SocialNode() {}
	
	public SocialNode(String name, ArrayList<Link> linkList) 
	{
		this.name = name;
		this.linkList = linkList;
	}
	
	public SocialNode(String name) 
	{
		this.name = name;
		this.linkList = new ArrayList<Link>();
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	public ArrayList<Link> getLinkList() 
	{
		return linkList;
	}
	public void setLinkList(ArrayList<Link> linkList) 
	{
		this.linkList = linkList;
	}
}
