package reseauSocial.dataFormat;

public class Link {
	private final int nbProperties = 10;
	private String linkName;
	private LinkProperty[] properties;
	private SocialNode target;	
	
	public Link() {}
	
	public Link(String linkName, LinkProperty[] properties, SocialNode target) 
	{
		this.properties = new LinkProperty[nbProperties];
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
	public LinkProperty[] getProperties() 
	{
		return properties;
	}
	public void setProperties(LinkProperty[] properties) 
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
