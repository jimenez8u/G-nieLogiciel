package reseauSocial.dataFormat;

import java.util.ArrayList;

public class Link {
	private String linkName;
	private ArrayList<LinkProperty> properties;
	private SocialNode target;	
	
	public Link() {}
	
	public Link(String linkName, ArrayList<LinkProperty> properties, SocialNode target) 
	{
		this.properties = new ArrayList<LinkProperty>();
		this.linkName = linkName;
		this.properties = properties;
		this.target = target;
	}
	
	public String getLinkName() 
	{
		return linkName;
	}
	public void setLinkName(String linkName) 
	{
		this.linkName = linkName;
	}
	public ArrayList<LinkProperty> getProperties() 
	{
		return properties;
	}
	public void setProperties(ArrayList<LinkProperty> properties) 
	{
		this.properties = properties;
	}
	public SocialNode getTarget() 
	{
		return target;
	}
	public void setTarget(SocialNode target) 
	{
		this.target = target;
	}
}
