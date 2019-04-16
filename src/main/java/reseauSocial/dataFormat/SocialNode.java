package reseauSocial.dataFormat;

public class SocialNode {
	private String name;
	private Link[] linkList;
	//private Type type; To uncomment if types are implemented
	
	public SocialNode() {}
	
	public SocialNode(String name, Link[] linkList) 
	{
		this.name = name;
		this.linkList = linkList;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	public Link[] getLinkList() 
	{
		return linkList;
	}
	public void setLinkList(Link[] linkList) 
	{
		this.linkList = linkList;
	}
}
