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
	public void addLink(Link link)
	{
		this.linkList.add(link);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialNode other = (SocialNode) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
		String str = "SocialNode [name=" + name + ", linkList=";
		
		for(Link link : linkList)
			str += link.toString() + ", ";
		
		str +=  "]";
		return str;
	}

}
